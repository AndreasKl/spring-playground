package net.andreaskluth;

import java.io.Serializable;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class InternalSomeGateway implements SomeGateway {

    @Override
    public <TRes extends Serializable, TReq extends Serializable> Response<TRes> execute(Message<TReq> request, Class<TRes> clazz)
            throws Exception {
        return Response.of(clazz.newInstance());
    }

}
