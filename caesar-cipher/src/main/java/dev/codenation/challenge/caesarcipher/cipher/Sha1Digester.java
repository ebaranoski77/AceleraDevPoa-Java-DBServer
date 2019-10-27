package dev.codenation.challenge.caesarcipher.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Digester {

    private final MessageDigest messageDigest;

    public Sha1Digester() {
        try {
            this.messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public String getAsHexString(String string) {
        return bytesToHex(messageDigest.digest(string.getBytes()));
    }


    private String bytesToHex(byte[] bytes) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            final String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }

}
