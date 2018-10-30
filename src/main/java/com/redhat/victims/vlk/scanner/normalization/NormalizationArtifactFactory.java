package com.redhat.victims.vlk.scanner.normalization;

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
        return new ClassScanner();
    }

    /**
     * Get {@link FileScanner} for processing regular (undefined) files.
     *
     * @param config scanning configuration
     * @return Scanner for processing regular (undefined) files.
     */
    static FileScannerInterface getFileScanner(ScannerConfigInterface config) {
        return new FileScanner();
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
