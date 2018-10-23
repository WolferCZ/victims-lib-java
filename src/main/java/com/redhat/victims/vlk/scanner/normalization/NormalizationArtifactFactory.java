package com.redhat.victims.vlk.scanner.normalization;

import com.redhat.victims.vlk.config.ScannerConfigInterface;
import com.redhat.victims.vlk.entity.Artifact;
import com.redhat.victims.vlk.scanner.ArtifactFactoryInterface;
import com.redhat.victims.vlk.scanner.FileScannerInterface;

import java.nio.file.Path;

public class NormalizationArtifactFactory implements ArtifactFactoryInterface {

    private ScannerConfigInterface config;

    public NormalizationArtifactFactory(ScannerConfigInterface config) {
        this.config = config;
    }

    @Override
    public Artifact generateArtifact(Path path) {
        return null;
    }

    static FileScannerInterface getClassScanner(ScannerConfigInterface config) {
        return new ClassScanner();
    }

    static FileScannerInterface getFileScanner(ScannerConfigInterface config) {
        return new FileScanner();
    }

    static FileScannerInterface getJarScanner(ScannerConfigInterface config) {
        return new JarScanner();
    }


}
