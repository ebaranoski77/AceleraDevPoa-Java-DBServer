package dev.codenation.challenge.caesarcipher.cipher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarCipherTest {

    @Test
    public void shouldCipherToExpectedOutput() {
        final CaesarCipher caesarCipher = new CaesarCipher(3);

        String input = "a ligeira raposa marrom saltou sobre o cachorro cansado";
        String expected = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";

        final String output = caesarCipher.cipher(input);

        assertEquals(expected, output);
    }

    @Test
    public void shouldCipherUppercaseTextToLowercase() {
        final CaesarCipher caesarCipher = new CaesarCipher(3);

        String input = "A LIGEIRA RAPOSA MARROM SALTOU SOBRE O CACHORRO CANSADO";
        String expected = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";

        final String output = caesarCipher.cipher(input);

        assertEquals(expected, output);
    }

    @Test
    public void shouldDecipherTextToExpectedOutput() {
        final CaesarCipher caesarCipher = new CaesarCipher(3);

        String input = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";
        String expected = "a ligeira raposa marrom saltou sobre o cachorro cansado";

        final String output = caesarCipher.decipher(input);

        assertEquals(expected, output);
    }

    @Test
    public void shouldKeepSpecialCharactersAndNumbersUntouched() {
        final CaesarCipher caesarCipher = new CaesarCipher(3);

        String input = "1, 2, 3, quatro, cinco, seis";
        String expectedCiphered = "1, 2, 3, txdwur, flqfr, vhlv";

        final String ciphered = caesarCipher.cipher(input);
        final String deciphered = caesarCipher.decipher(ciphered);

        assertEquals(expectedCiphered, ciphered);
        assertEquals(input, deciphered);
    }

    @Test
    public void shouldRotateIfOffsetIsTooLarge() {
        final CaesarCipher caesarCipher = new CaesarCipher(10);

        String input = "1, 2, 3, quatro, cinco, seis";
        String expectedCiphered = "1, 2, 3, aekdby, msxmy, cosc";

        final String ciphered = caesarCipher.cipher(input);
        final String deciphered = caesarCipher.decipher(ciphered);

        assertEquals(expectedCiphered, ciphered);
        assertEquals(input, deciphered);
    }

}