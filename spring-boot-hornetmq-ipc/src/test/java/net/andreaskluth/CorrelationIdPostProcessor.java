package net.andreaskluth;

import javax.jms.JMSException;

import org.springframework.jms.core.MessagePostProcessor;

final class CorrelationIdPostProcessor implements MessagePostProcessor {

    private final String correlationId;

    public CorrelationIdPostProcessor(final String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public javax.jms.Message postProcessMessage(javax.jms.Message message) throws JMSException {
        message.setJMSCorrelationID(correlationId);
        return message;
    }
}