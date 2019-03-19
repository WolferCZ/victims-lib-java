package com.redhat.victims.vlk.scanner.normalization;

/*
 * #%L
 * vlk-lib
 * %%
 * Copyright (C) 2019
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import com.redhat.victims.vlk.config.ScannerConfigInterface;
import com.redhat.victims.vlk.entity.Artifact;
import com.redhat.victims.vlk.scanner.FileScannerInterface;
import com.redhat.victims.vlk.utils.FingerprintHashingUtil;
import com.redhat.victims.vlk.utils.FingerprintHashingUtilInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

/**
 * Scanner for processing `application/x-java-archive` files.
 * <p>
 * This scanner is responsible for generating most of the content of {@link com.redhat.victims.vlk.entity.Artifact}.
 * It is responsible for parsing metadata and truncating the archive contents.
 */
class JarScanner implements FileScannerInterface {


    public static Artifact process(byte[] bytes, String fileName, ScannerConfigInterface config) throws IOException {
        JarInputStream jis = new JarInputStream(new ByteArrayInputStream(bytes));

        Artifact artifact = new Artifact();
        // process contents
        Content file;
        ExecutorService executor = Executors.newCachedThreadPool();
        while ((file = getNextFile(jis, config)) != null) {
            submitJob(executor, file);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // probably a bad idea wrap as an IO Exception for now
            throw new IOException(
                "There was an issue while waiting for the ExecutorService "
                    + "to terminate.", e);
        }

        // Process the metadata from the manifest if available
        Manifest mf = jis.getManifest();
        if (mf != null) {
            artifact.getMetadata().put("MANIFEST.MF", Metadata.fromManifest(mf)); //TODO metadata mapper
        }

        FingerprintHashingUtilInterface fingerprintHashingUtil = new FingerprintHashingUtil(config);
        artifact.setFingerprint(fingerprintHashingUtil.fingerprint(bytes));
        jis.close();

        return artifact;

    }


    /**
     * Helper method to sumit a new threaded tast to a given executor.
     *
     * @param executor
     * @param file
     */
    protected void submitJob(ExecutorService executor, Content file) {
        // lifted from http://stackoverflow.com/a/5853198/1874604
        class OneShotTask implements Runnable {
            Content file;

            OneShotTask(Content file) {
                this.file = file;
            }

            public void run() {
                processContent(file);
            }
        }
        // we do not care about Future
        executor.submit(new OneShotTask(file));
    }


    /**
     * Synchronized method of adding metadata
     *
     * @param filename
     * @param md
     */
    protected synchronized void putMetadata(String filename, Metadata md) {
        metadata.put(filename, md);
    }

    /**
     * Synchronized method for adding a new conent
     *
     * @param record
     * @param filename
     */
    protected synchronized void addContent(Artifact record, String filename) {
        if (record != null) {
            if (filename.endsWith(".jar")) {
                // this is an embedded archive
                embedded.add(record);
            } else {
                contents.add(record);
            }
        }
    }

    /**
     * Threadable task for processing a given {@link Content} file.
     *
     * @param file
     */
    protected void processContent(Content file) {
        // Handle metadata/special cases
        String lowerCaseFileName = file.name.toLowerCase();
        if (lowerCaseFileName.endsWith("pom.properties")) {
            // handle pom properties files
            InputStream is = new ByteArrayInputStream(file.bytes);
            Metadata md = Metadata.fromPomProperties(is);
            putMetadata(file.name, md);
        }

        // This is separate as we may or may not want to fingerprint
        // all files.
        if (RECURSIVE) {
            Artifact record = Processor.process(file.bytes, file.name, true);
            addContent(record, file.name);
        }
    }


    /**
     * Get the next file as a {@link Content} in this archive.
     *
     * @return
     * @throws IOException
     */
    public static Content getNextFile(JarInputStream jis, ScannerConfigInterface config) throws IOException {
        JarEntry entry;
        if ((entry = jis.getNextJarEntry()) != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] data = new byte[config.getBufferSize()];
            int size;
            while ((size = jis.read(data, 0, data.length)) != -1) {
                bos.write(data, 0, size);
            }
            Content file = new Content(entry.getName(), bos.toByteArray());
            bos.close();
            return file;
        }
        return null;
    }


    /**
     * Content -- Inner class for use by {@link ArchiveFile}. This is used to
     * group name of file extracted in memory and the corresponding bytes that
     * were read.
     *
     * @author abn
     */
    protected static class Content {
        public String name;
        public byte[] bytes;

        public Content(String name, byte[] bytes) {
            this.name = name;
            this.bytes = bytes;
        }
    }

}
