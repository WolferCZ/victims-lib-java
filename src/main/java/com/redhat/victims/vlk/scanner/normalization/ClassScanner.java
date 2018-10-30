package com.redhat.victims.vlk.scanner.normalization;

import com.redhat.victims.vlk.scanner.FileScannerInterface;

/**
 * Scanner for processing `application/x-java` files.
 * <p>
 * Java code is normalized (see {@link com.redhat.victims.vlk.utils.ByteCodeNormalizerInterface } prior to generating hash fingerprint.
 */
class ClassScanner implements FileScannerInterface {
}
