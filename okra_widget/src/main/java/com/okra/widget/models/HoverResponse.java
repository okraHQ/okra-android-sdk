package com.okra.widget.models;

public class HoverResponse {
    private String[] sessionMessages;
    private String uuid;

    public String[] getSessionMessages() {
        return sessionMessages;
    }

    public void setSessionMessages(String[] sessionMessages) {
        this.sessionMessages = sessionMessages;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
