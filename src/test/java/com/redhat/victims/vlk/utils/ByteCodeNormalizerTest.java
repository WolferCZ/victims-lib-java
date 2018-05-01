package com.redhat.victims.vlk.utils;

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
