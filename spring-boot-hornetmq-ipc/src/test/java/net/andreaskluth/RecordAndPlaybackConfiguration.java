package net.andreaskluth;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class RecordAndPlaybackConfiguration {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private JmsTemplate template;

    @Bean
    public DefaultMessageListenerContainer messageListener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("testQueue.request");
        container.setMessageListener(new MessageListenerImplementation());
        return container;
    }

    private final class MessageListenerImplementation implements MessageListener {
        @Override
        public void onMessage(Message message) {
            String jmsCorrelationID = getCorrelationId(message);
            try {
                message.getBody(net.andreaskluth.Message.class).getValue();
                template.convertAndSend("testQueue.response", Gateway.obtainNextResult(),
                        new CorrelationIdPostProcessor(jmsCorrelationID));
            }
            catch (JMSException ex) {
                throw new IllegalStateException(ex);
            }
        }

        private String getCorrelationId(Message message) {
            String jmsCorrelationID = "";
            try {
                jmsCorrelationID = message.getJMSCorrelationID();
            }
            catch (JMSException ex) {
                throw new IllegalStateException(ex);
            }
            return jmsCorrelationID;
        }
    }

}
