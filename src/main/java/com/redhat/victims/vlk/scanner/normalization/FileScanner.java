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
import com.redhat.victims.vlk.entity.FileType;
import com.redhat.victims.vlk.scanner.FileScannerInterface;
import com.redhat.victims.vlk.utils.FingerprintHashingUtil;
import com.redhat.victims.vlk.utils.FingerprintHashingUtilInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Scanner for processing regular (undefined) files.
 * <p>
 * Files are only reduced to hash fingerprint without any modification to their content.
 */
class FileScanner implements FileScannerInterface {


    private FingerprintHashingUtilInterface fingerprintHashingUtil;

    public FileScanner(ScannerConfigInterface config) {
        fingerprintHashingUtil = new FingerprintHashingUtil(config);
    }

    public Artifact generateArtifact(Path path) throws IOException {
        Artifact artifact = new Artifact();
        artifact.setFilename(path.getFileName().toString());
        artifact.setFileType(FileType.UNKNOWN); // TODO should be resolved/tested
        artifact.setFingerprint(fingerprintHashingUtil.fingerprint(Files.readAllBytes(path)));

        return artifact;
    }


}

