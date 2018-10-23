package com.redhat.victims.vlk.mock.config;


import com.redhat.victims.vlk.config.ScannerConfigInterface;
import com.redhat.victims.vlk.entity.HashAlgorithm;

import java.nio.charset.Charset;
import java.util.List;

public class ScannerConfig implements ScannerConfigInterface {

    private List<HashAlgorithm> hashAlgorithmsForProcessing;
    private Charset charset;
    private boolean knownTypesOnly;


    public ScannerConfig(List<HashAlgorithm> hashAlgorithmsForProcessing, Charset charset, boolean knownTypesOnly) {
        this.hashAlgorithmsForProcessing = hashAlgorithmsForProcessing;
        this.charset = charset;
        this.knownTypesOnly = knownTypesOnly;
    }

    @Override
    public List<HashAlgorithm> getHashAlgorithmsForProcessing() {
        return this.hashAlgorithmsForProcessing;
    }

    @Override
    public Charset getCharset() {
        return this.charset;
    }

    @Override
    public boolean getKnownTypesOnly() {
        return this.knownTypesOnly;
    }
}
