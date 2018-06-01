package com.redhat.victims.vlk.entity;

import java.util.List;
import java.util.Map;

/**
 * The main container for data structure used to store victims record
 * information.
 */
public interface ArtifactInterface {

    //TODO javadoc description

    public List<Artifact> getEmbedded();

    public FileType getFileType();

    public Map<String, String> getFingerprint();

    public List<Artifact> getContent();

    public String getFilename();

    public Map<String, Map<String, String>> getMetadata();
}
