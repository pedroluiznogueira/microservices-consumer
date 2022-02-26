package com.github.pedroluiznogueira.microservicesconsumer.model;

import com.github.pedroluiznogueira.microservicesconsumer.enums.EventType;

public class Envelope {
    private EventType eventType;
    private String data;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
