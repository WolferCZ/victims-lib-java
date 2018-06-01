package com.redhat.victims.vlk.utils;

import com.redhat.victims.vlk.config.ScannerConfigInterface;
import com.redhat.victims.vlk.scanner.Algorithm;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class FingerprintHashingUtil implements FingerprintHashingUtilInterface{


    private ScannerConfigInterface config;

    public FingerprintHashingUtil(ScannerConfigInterface config) {
        this.config = config;
    }

    /**
     * Generate a hashmap of fingerprints for a give byte array using all
     * configured algorithms. Default: SHA1, SHA-512, MD5.
     *
     * @param bytes A byte array whose content is to be fingerprinted.
     * @return Map of the form {algorithm:hash}
     */
    public Map<Algorithm, String> fingerprint(byte[] bytes) {
        Map<Algorithm, String> fingerprint = new HashMap<>();
        for (Algorithm algorithm : config.getHashAlgorithmsForProcessing()) {
            try {
                MessageDigest md = MessageDigest.getInstance(algorithm
                    .toString().toUpperCase());
                fingerprint.put(algorithm,
                    new String(Hex.encodeHex(md.digest(bytes))));
            } catch (NoSuchAlgorithmException e) {
                // Do nothing just skip
            }
        }
        return fingerprint;
    }

}
