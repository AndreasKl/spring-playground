package net.andreaskluth;

import java.io.Serializable;


public interface SomeGateway {

    <TRes extends Serializable, TReq extends Serializable> Response<TRes> execute(Message<TReq> request, Class<TRes> clazz) throws Exception;
    
}
