package com.okra.widget.models;

public class HoverStrategy {

    public HoverStrategy(String actionId, String header, String processingMessage, int displayTime) {
        this.actionId = actionId;
        this.header = header;
        this.processingMessage = processingMessage;
        this.displayTime = displayTime;
    }

    public HoverStrategy(String actionId, String id, String header, String processingMessage, int displayTime) {
        this.actionId = actionId;
        this.id = id;
        this.header = header;
        this.processingMessage = processingMessage;
        this.displayTime = displayTime;
    }

    private String actionId;
    private String id;
    private Boolean firstAction = false;
    private Boolean lastAction = false;
    private Enums.BankResponseMethod bankResponseMethod;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Enums.BankResponseMethod getBankResponseMethod() {
        return bankResponseMethod;
    }

    public void setBankResponseMethod(Enums.BankResponseMethod bankResponseMethod) {
        this.bankResponseMethod = bankResponseMethod;
    }

    public Boolean isFirstAction() {
        return firstAction;
    }

    public void setFirstAction(Boolean firstAction) {
        this.firstAction = firstAction;
    }

    public Boolean isLastAction() {
        return lastAction;
    }

    public void setLastAction(Boolean lastAction) {
        this.lastAction = lastAction;
    }
}
