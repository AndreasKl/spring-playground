package net.andreaskluth;

import java.io.Serializable;

public class Message<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final T value;

    private Message(T value) {
        this.value = value;
    }

    public static <T extends Serializable> Message<T> of(T value) {
        return new Message<T>(value);
    }

    public T getValue() {
        return value;
    }

}
