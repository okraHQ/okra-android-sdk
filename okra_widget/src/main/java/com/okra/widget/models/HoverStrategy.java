package com.okra.widget.models;

public class HoverStrategy {

    public HoverStrategy(String actionId, String header, String processingMessage, int displayTime) {
        this.actionId = actionId;
        this.header = header;
        this.processingMessage = processingMessage;
        this.displayTime = displayTime;
    }

    private String actionId;
    private String header;
    private String processingMessage;
    private int displayTime;

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getProcessingMessage() {
        return processingMessage;
    }

    public void setProcessingMessage(String processingMessage) {
        this.processingMessage = processingMessage;
    }

    public int getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(int displayTime) {
        this.displayTime = displayTime;
    }
}
