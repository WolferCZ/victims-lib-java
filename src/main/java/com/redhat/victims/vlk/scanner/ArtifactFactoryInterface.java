package com.redhat.victims.vlk.scanner;

import com.redhat.victims.vlk.entity.Artifact;

import java.nio.file.Path;


public interface ArtifactFactoryInterface {


    Artifact generateArtifact(Path path);

}
