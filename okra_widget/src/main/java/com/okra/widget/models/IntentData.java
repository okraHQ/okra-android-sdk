package com.okra.widget.models;

public class IntentData {

    public IntentData(String bankSlug, String recordId, String pin, String nuban, String extra,  String bgColor, String accentColor, String buttonColor) {
        this.bankSlug = bankSlug;
        this.recordId = recordId;
        this.pin = pin;
        this.nuban = nuban;
        this.extra = extra;
        this.bgColor = bgColor;
        this.accentColor = accentColor;
        this.buttonColor = buttonColor;
    }

    private String bankSlug;
    private String recordId;
    private  String pin;
    private  String nuban;
    private String extra;
    private String bgColor;
    private String accentColor;
    private String buttonColor;

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

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    public String getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
    }

    public String getNuban() {
        return nuban;
    }

    public void setNuban(String nuban) {
        this.nuban = nuban;
    }
}
