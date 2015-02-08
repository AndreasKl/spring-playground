package net.andreaskluth;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class HornetqSomeGateway implements SomeGateway {

    private JmsTemplate template;

    @Autowired
    public HornetqSomeGateway(JmsTemplate template) {
        this.template = template;
    }

    @Override
    public <TRes extends Serializable, TReq extends Serializable> Response<TRes> execute(Message<TReq> request,
            Class<TRes> clazz) throws Exception {

        final String correlationId = UUID.randomUUID().toString();
        template.convertAndSend("testQueue.request", request, new CorrelationIdPostProcessor(correlationId));
        
        javax.jms.Message message = template.receiveSelected("testQueue.response", "JMSCorrelationID='" + correlationId
                + "'");

        return Response.of(message.getBody(clazz));
    }

}
