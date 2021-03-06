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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;


/**
 * Utility for parsing metadata from metadata files.
 */
public class MetadataUtils {

    /**
     * Attempts to parse a pom.xml file.
     *
     * @param is An input stream containing the extracted POM file.
     */
    public static Map<String, String> fromPomProperties(InputStream is) {
        Map<String, String> metadata = new HashMap<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = input.readLine()) != null) {
                if (line.startsWith("#"))
                    continue;
                String[] property = line.trim().split("=");
                if (property.length == 2)
                    metadata.put(property[0], property[1]);
            }
        } catch (IOException e) {
            // Problems? Too bad!
        }
        return metadata;
    }

    /**
     * Attempts to parse a MANIFEST.MF file from an input stream.
     *
     * @param is An input stream containing the extracted manifest file.
     * @return HashMap of the type {atribute name : attribute value}.
     */
    public static Map<String, String> fromManifest(InputStream is) {
        try {
            Manifest mf = new Manifest(is);
            return fromManifest(mf);

        } catch (IOException e) {
            // Problems? Too bad!
        }
        return new HashMap<>();
    }

    /**
     * Extracts required attributes and their values from a {@link Manifest}
     * object.
     *
     * @param mf A Manifest file.
     * @return HashMap of the type {atribute name : attribute value}.
     */
    public static Map<String, String> fromManifest(Manifest mf) {
        Map<String, String> metadata = new HashMap<>();
        final Attributes.Name[] attribs = {Attributes.Name.MANIFEST_VERSION,
            Attributes.Name.IMPLEMENTATION_TITLE,
            Attributes.Name.IMPLEMENTATION_URL,         //TODO deprecations will have to be fixed
            Attributes.Name.IMPLEMENTATION_VENDOR,
            Attributes.Name.IMPLEMENTATION_VENDOR_ID,   //TODO deprecations will have to be fixed
            Attributes.Name.IMPLEMENTATION_VERSION,
            Attributes.Name.MAIN_CLASS};
        for (Attributes.Name attrib : attribs) {
            Object o = mf.getMainAttributes().get(attrib);
            if (o != null) {
                metadata.put(attrib.toString(), o.toString());
            }
        }
        return metadata;
    }
}
