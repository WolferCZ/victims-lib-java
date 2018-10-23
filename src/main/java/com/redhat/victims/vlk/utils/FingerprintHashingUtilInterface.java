package com.redhat.victims.vlk.utils;

import com.redhat.victims.vlk.entity.HashAlgorithm;

import java.util.Map;

/**
 * Created by vlk on 5/1/18.
 */
public interface FingerprintHashingUtilInterface {

    /**
     * Generate a hashmap of fingerprints for a give byte array using all
     * configured algorithms. Default: SHA1, SHA-512, MD5.
     *
     * @param bytes A byte array whose content is to be fingerprinted.
     * @return Hashmap of the form {algorithm:hash}
     */
    public Map<HashAlgorithm, String> fingerprint(byte[] bytes);
}
