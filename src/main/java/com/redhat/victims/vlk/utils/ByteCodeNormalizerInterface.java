package com.redhat.victims.vlk.utils;

import java.io.IOException;


public interface ByteCodeNormalizerInterface {

    /**
     * The driving function that normalizes given byte code.
     *
     * @param bytes
     *            The input class as a byte array.
     * @param fileName
     *            The name of the file.
     * @return The normalized bytecode as a byte array.
     * @throws IOException
     */
    public byte[] normalize(byte[] bytes, String fileName)
        throws IOException ;
}
