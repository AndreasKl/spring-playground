package net.andreaskluth;

import java.io.Serializable;

public class Response<T extends Serializable> {

    private final T payload;

    private Response(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public static <TRes extends Serializable> Response<TRes> of(TRes body) {
        return new Response<TRes>(body);
    }

}
