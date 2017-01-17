package com.epam.jobmatch.service.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Encryption {

    private static final String ALGORITHM = "MD5";
    private static final String CHARSET = "UTF-8";

    public static char[] encryptedValue(char[] valueToEncrypt) throws NoSuchAlgorithmException {
        byte[] byteValue = toBytes(valueToEncrypt);
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        messageDigest.update(byteValue);
        char[] hashedValue = new BigInteger(1, messageDigest.digest()).toString(16).toCharArray();

        Arrays.fill(byteValue, (byte) 0);
        return hashedValue;
    }

    private static byte[] toBytes(char[] charArray) {
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        ByteBuffer byteBuffer = Charset.forName(CHARSET).encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000');
        Arrays.fill(byteBuffer.array(), (byte) 0);
        return bytes;
    }

}
