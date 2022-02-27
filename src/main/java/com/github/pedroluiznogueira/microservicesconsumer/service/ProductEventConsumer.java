package com.github.pedroluiznogueira.microservicesconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pedroluiznogueira.microservicesconsumer.model.Envelope;
import com.github.pedroluiznogueira.microservicesconsumer.model.ProductEvent;
import com.github.pedroluiznogueira.microservicesconsumer.model.ProductEventLog;
import com.github.pedroluiznogueira.microservicesconsumer.model.SnsMessage;
import com.github.pedroluiznogueira.microservicesconsumer.repository.ProductEventLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Service
public class ProductEventConsumer {
    private static final Logger log = LoggerFactory.getLogger(
            ProductEventConsumer.class);

    private ObjectMapper objectMapper;
    private ProductEventLogRepository productEventLogRepository;

    @Autowired
    public ProductEventConsumer(ObjectMapper objectMapper, ProductEventLogRepository productEventLogRepository) {
        this.objectMapper = objectMapper;
        this.productEventLogRepository = productEventLogRepository;
    }

    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receiveProductEvent(TextMessage textMessage) throws JMSException, IOException {

        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(),
                SnsMessage.class);

        Envelope envelope = objectMapper.readValue(snsMessage.getMessage(),
                Envelope.class);

        ProductEvent productEvent = objectMapper.readValue(
                envelope.getData(), ProductEvent.class);

        log.info("Product event received - Event: {} - ProductId: {} - " +
                        "MessageId: {}", envelope.getEventType(),
                productEvent.getProductId(), snsMessage.getMessageId());

        ProductEventLog productEventLog = buildProductEventLog(envelope,
                productEvent);
        productEventLogRepository.save(productEventLog);

        log.info("created");
    }

    private ProductEventLog buildProductEventLog(Envelope envelope,
                                                 ProductEvent productEvent) {
        long timestamp = Instant.now().toEpochMilli();

        ProductEventLog productEventLog = new ProductEventLog();
        productEventLog.setPk(productEvent.getCode());
        productEventLog.setSk(envelope.getEventType() + "_" + timestamp);
        productEventLog.setEventType(envelope.getEventType());
        productEventLog.setProductId(productEvent.getProductId());
        productEventLog.setUsername(productEvent.getUsername());
        productEventLog.setTimestamp(timestamp);
        productEventLog.setTtl(Instant.now().plus(
                Duration.ofMinutes(10)).getEpochSecond());

        return productEventLog;
    }
}