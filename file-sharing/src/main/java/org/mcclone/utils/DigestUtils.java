package org.mcclone.utils;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;

import java.security.SecureRandom;

/**
 * Created by mcclone on 17-1-14.
 */
public class DigestUtils {

    public static String generateSalt(int numByte, boolean encodeHashAsBase64) {
        SecureRandom random = new SecureRandom();
        byte[] digest = new byte[numByte];
        random.nextBytes(digest);
        return encodeHashAsBase64 ? Utf8.decode(Base64.encode(digest)) : new String(Hex.encode(digest));
    }
}
