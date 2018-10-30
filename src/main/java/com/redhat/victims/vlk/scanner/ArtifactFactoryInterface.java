package com.redhat.victims.vlk.scanner;

import com.redhat.victims.vlk.entity.Artifact;

import java.nio.file.Path;

/**
 * Interface for generating {@link Artifact}.
 */
public interface ArtifactFactoryInterface {

    /**
     * Generate comparable {@link Artifact}.
     *
     * @param path Path to file to process
     * @return Unified {@link Artifact} describing the file
     */
    Artifact generateArtifact(Path path);

}
