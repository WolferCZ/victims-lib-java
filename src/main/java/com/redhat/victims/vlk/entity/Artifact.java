package com.redhat.victims.vlk.entity;

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

import java.util.List;
import java.util.Map;

/**
 * The main container for data structure used to store victims record
 * information.
 *
 * @deprecated Informations should be mapped directly into {@link VictimsRecord}.
 *
 */
@Deprecated
public class Artifact implements ArtifactInterface{

    private List<Artifact> embedded;
    private FileType fileType;
    private Map<HashAlgorithm, String> fingerprint;
    private List<Artifact> content;
    private String filename;
    private Map<String, Map<String, String>> metadata;

    public Artifact() {
    }

    @Override
    public List<Artifact> getEmbedded() {
        return embedded;
    }

    public void setEmbedded(List<Artifact> embedded) {
        this.embedded = embedded;
    }

    @Override
    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public Map<HashAlgorithm, String> getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(Map<HashAlgorithm, String> fingerprint) {
        this.fingerprint = fingerprint;
    }

    @Override
    public List<Artifact> getContent() {
        return content;
    }

    public void setContent(List<Artifact> content) {
        this.content = content;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public Map<String, Map<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Map<String, String>> metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artifact artifact = (Artifact) o;

        if (getEmbedded() != null ? !getEmbedded().equals(artifact.getEmbedded()) : artifact.getEmbedded() != null)
            return false;
        if (getFileType() != artifact.getFileType()) return false;
        if (getFingerprint() != null ? !getFingerprint().equals(artifact.getFingerprint()) : artifact.getFingerprint() != null)
            return false;
        if (getContent() != null ? !getContent().equals(artifact.getContent()) : artifact.getContent() != null)
            return false;
        if (getFilename() != null ? !getFilename().equals(artifact.getFilename()) : artifact.getFilename() != null)
            return false;
        return getMetadata() != null ? getMetadata().equals(artifact.getMetadata()) : artifact.getMetadata() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmbedded() != null ? getEmbedded().hashCode() : 0;
        result = 31 * result + (getFileType() != null ? getFileType().hashCode() : 0);
        result = 31 * result + (getFingerprint() != null ? getFingerprint().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getFilename() != null ? getFilename().hashCode() : 0);
        result = 31 * result + (getMetadata() != null ? getMetadata().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Artifact{" +
            "embedded=" + embedded +
            ", fileType=" + fileType +
            ", fingerprint=" + fingerprint +
            ", content=" + content +
            ", filename='" + filename + '\'' +
            ", metadata=" + metadata +
            '}';
    }
}
