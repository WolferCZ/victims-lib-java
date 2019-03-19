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
import com.redhat.victims.vlk.scanner.ArtifactFactoryInterface;
import com.redhat.victims.vlk.scanner.FileScannerInterface;

import java.nio.file.Path;

/**
 * Factory responsible for generating comparable {@link Artifact}s from physical files.
 * All Java code is normalized (see {@link com.redhat.victims.vlk.utils.ByteCodeNormalizer}) before generating hash fingerprints.
 */
public class NormalizationArtifactFactory implements ArtifactFactoryInterface {


    /**
     * Scanning configuration.
     */
    private ScannerConfigInterface config;

    /**
     * Create {@linkplain NormalizationArtifactFactory}.
     *
     * @param config scanning configuration
     */
    public NormalizationArtifactFactory(ScannerConfigInterface config) {
        this.config = config;
    }

    @Override
    public Artifact generateArtifact(Path path) {
        return null;
    }

    /**
     * Get {@link ClassScanner} for processing `application/x-java` files.
     *
     * @param config scanning configuration
     * @return Scanner for processing `application/x-java` files.
     */
    static FileScannerInterface getClassScanner(ScannerConfigInterface config) {
        return new ClassScanner(config);
    }

    /**
     * Get {@link FileScanner} for processing regular (undefined) files.
     *
     * @param config scanning configuration
     * @return Scanner for processing regular (undefined) files.
     */
    static FileScannerInterface getFileScanner(ScannerConfigInterface config) {
        return new FileScanner(config);
    }

    /**
     * Get {@link JarScanner} for processing `application/x-java-archive` files.
     *
     * @param config scanning configuration
     * @return Scanner for processing `application/x-java-archive` files.
     */
    static FileScannerInterface getJarScanner(ScannerConfigInterface config) {
        return new JarScanner();
    }


}
