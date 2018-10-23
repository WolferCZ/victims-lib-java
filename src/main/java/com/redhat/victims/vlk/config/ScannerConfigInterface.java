package com.redhat.victims.vlk.config;


import com.redhat.victims.vlk.entity.HashAlgorithm;

import java.nio.charset.Charset;
import java.util.List;

public interface ScannerConfigInterface {

    List<HashAlgorithm> getHashAlgorithmsForProcessing();

    Charset getCharset();

    /**
     * If scanner should process only well known types (i.e. {@link com.redhat.victims.vlk.entity.FileType}
     * Unknown files are processed as simplest types of files.
     *
     * @return if scanner should process only well known types (i.e. {@link com.redhat.victims.vlk.entity.FileType}
     */
    boolean getKnownTypesOnly();

}
