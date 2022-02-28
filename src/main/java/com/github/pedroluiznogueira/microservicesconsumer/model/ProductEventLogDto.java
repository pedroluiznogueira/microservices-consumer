package com.github.pedroluiznogueira.microservicesconsumer.model;

import com.github.pedroluiznogueira.microservicesconsumer.enums.EventType;

public class ProductEventLogDto {

    private final String code;
    private final EventType eventType;
    private final String messageId;
    private final long productId;
    private final String username;
    private final long timestamp;

    public ProductEventLogDto(ProductEventLog productEventLog) {
        this.code = productEventLog.getPk();
        this.eventType = productEventLog.getEventType();
        this.messageId = productEventLog.getMessageId();
        this.productId = productEventLog.getProductId();
        this.username = productEventLog.getUsername();
        this.timestamp = productEventLog.getTimestamp();
    }

    public String getCode() {
        return code;
    }

    public EventType getEventType() {
        return eventType;
    }

    public long getProductId() {
        return productId;
    }

    public String getUsername() {
        return username;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
