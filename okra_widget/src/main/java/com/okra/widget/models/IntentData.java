package com.okra.widget.models;

public class IntentData {

    public IntentData(String bankSlug, String recordId, String pin, String extra) {
        this.bankSlug = bankSlug;
        this.recordId = recordId;
        this.pin = pin;
        this.extra = extra;
    }

    private String bankSlug;
    private String recordId;
    private  String pin;
    private String extra;

    public String getBankSlug() {
        return bankSlug;
    }

    public void setBankSlug(String bankSlug) {
        this.bankSlug = bankSlug;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
