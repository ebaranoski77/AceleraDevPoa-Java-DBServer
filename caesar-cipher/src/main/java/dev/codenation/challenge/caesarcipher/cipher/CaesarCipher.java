package dev.codenation.challenge.caesarcipher.cipher;

import java.io.StringWriter;

public class CaesarCipher implements CipherMethod<String, String> {

    private static final int A_CODE = 97;
    private static final int Z_CODE = 122;

    private final int offset;

    public CaesarCipher(int offset) {
        this.offset = offset;
    }
    @Override
    public String cipher(String input) {
        final StringWriter stringWriter = new StringWriter();
        input.toLowerCase().chars()
                .map(this::applyCaesarCipher)
                .forEach(stringWriter::write);
        return stringWriter.toString();
    }

    @Override
    public String decipher(String input) {
        final StringWriter stringWriter = new StringWriter();
        input.toLowerCase().chars()
                .map(this::removeCaesarCipher)
                .forEach(stringWriter::write);
        return stringWriter.toString();
    }

    private int applyCaesarCipher(int i) {

        if (i >= A_CODE && i <= Z_CODE) {

            if ((i + offset) > Z_CODE) {
                return A_CODE + ((i + offset) - Z_CODE) - 1;
            }

            return i + offset;
        }

        return i;
    }

    private int removeCaesarCipher(int i) {

        if (i >= A_CODE && i <= Z_CODE) {

            if ((i - offset) < A_CODE) {
                return Z_CODE - (A_CODE - (i - offset)) + 1;
            }

            return i - offset;
        }

        return i;
    }

}
