package dev.codenation.challenge.caesarcipher.cipher;

public interface CipherMethod<T, E> {

    T decipher(E input);

    E cipher(T input);

}
