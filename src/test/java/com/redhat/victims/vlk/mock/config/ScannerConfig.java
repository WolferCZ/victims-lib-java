package com.redhat.victims.vlk.mock.config;


import com.redhat.victims.vlk.config.ScannerConfigInterface;
import com.redhat.victims.vlk.scanner.Algorithm;

import java.nio.charset.Charset;
import java.util.List;

public class ScannerConfig implements ScannerConfigInterface {

    private List<Algorithm> hashAlgorithmsForProcessing;
    private Charset charset;


    public ScannerConfig(List<Algorithm> hashAlgorithmsForProcessing, Charset charset) {
        this.hashAlgorithmsForProcessing = hashAlgorithmsForProcessing;
        this.charset = charset;
    }

    @Override
    public List<Algorithm> getHashAlgorithmsForProcessing() {
        return this.hashAlgorithmsForProcessing;
    }

    @Override
    public Charset getCharset() {
        return this.charset;
    }
}
