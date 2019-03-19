package com.redhat.victims.vlk.utils;

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
