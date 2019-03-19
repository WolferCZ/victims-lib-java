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

import com.redhat.victims.vlk.config.ScannerConfigInterface;
import com.redhat.victims.vlk.mock.config.ScannerConfig;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class ByteCodeNormalizerTest {

    public static final String TEST_CLASS_FILE = "/utils/normalization/CSVParserBuilder.class";
    public static final String TEST_NORMALIZED_CLASS_FILE = "/utils/normalization/CSVParserBuilder-normalized.class";

    private ByteCodeNormalizerInterface byteCodeNormalizer;

    @Before
    public void setUp() throws Exception {
        ScannerConfigInterface config = new ScannerConfig(new ArrayList<>(), Charset.forName("UTF-8"));
        byteCodeNormalizer = new ByteCodeNormalizer(config);
    }

    @Test
    public void normalize() throws Exception {
        byte[] normalizedBytes = byteCodeNormalizer.normalize(IOUtils.toByteArray(this.getClass().getResourceAsStream(TEST_CLASS_FILE)), TEST_CLASS_FILE);
        Assert.assertArrayEquals(IOUtils.toByteArray(this.getClass().getResourceAsStream(TEST_NORMALIZED_CLASS_FILE)), normalizedBytes);
    }

}
