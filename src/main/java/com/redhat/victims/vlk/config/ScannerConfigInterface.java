package com.redhat.victims.vlk.config;


import com.redhat.victims.vlk.scanner.Algorithm;

import java.nio.charset.Charset;
import java.util.List;

public interface ScannerConfigInterface {

    List<Algorithm> getHashAlgorithmsForProcessing();

    Charset getCharset();

}
