package com.github.pedroluiznogueira.microservicesconsumer.model;

import com.github.pedroluiznogueira.microservicesconsumer.enums.EventType;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "product-events")
public class ProductEventLog {

    public ProductEventLog() {}

    @Id
    private ProductEventKey productEventKey;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "eventType")
    private EventType eventType;

    @DynamoDBAttribute(attributeName = "messageId")
    private String messageId;

    @DynamoDBAttribute(attributeName = "productId")
    private long productId;

    @DynamoDBAttribute(attributeName = "username")
    private String username;

    @DynamoDBAttribute(attributeName = "timestamp")
    private long timestamp;

    @DynamoDBAttribute(attributeName = "ttl")
    private long ttl;

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return this.productEventKey != null ? this.productEventKey.getPk() : null;
    }

    public void setPk(String pk) {
        if (this.productEventKey == null) {
            this.productEventKey = new ProductEventKey();
        }

        this.productEventKey.setPk(pk);
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return this.productEventKey != null ? this.productEventKey.getSk() : null;
    }

    public void setSk(String sk) {
        if (this.productEventKey == null) {
            this.productEventKey = new ProductEventKey();
        }

        this.productEventKey.setSk(sk);
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "ProductEventLog{" +
                "productEventKey=" + productEventKey +
                ", eventType=" + eventType +
                ", productId=" + productId +
                ", username='" + username + '\'' +
                ", timestamp=" + timestamp +
                ", ttl=" + ttl +
                '}';
    }
}

