package com.okra.widget.models;

public class IntentData {

    public IntentData(String bankSlug, String recordId, String pin, String nuban, String extra, String bgColor, String accentColor, String buttonColor, String payment) {
        this.bankSlug = bankSlug;
        this.recordId = recordId;
        this.pin = pin;
        this.nuban = nuban;
        this.extra = extra;
        this.bgColor = bgColor;
        this.accentColor = accentColor;
        this.buttonColor = buttonColor;
        this.payment = payment;
        this.paymentCreditAccount = "";
        this.paymentAmount = "";
        this.paymentCreditBankName = "";
    }
    public IntentData(String bankSlug, String recordId, String pin, String nuban, String extra, String bgColor, String accentColor, String buttonColor, String payment, String paymentCreditAccount, String paymentAmount, String paymentCreditBankName, String isSameBank) {
        this.bankSlug = bankSlug;
        this.recordId = recordId;
        this.pin = pin;
        this.nuban = nuban;
        this.extra = extra;
        this.bgColor = bgColor;
        this.accentColor = accentColor;
        this.buttonColor = buttonColor;
        this.payment = payment;
        this.paymentCreditAccount = paymentCreditAccount;
        this.paymentAmount = paymentAmount;
        this.paymentCreditBankName = paymentCreditBankName;
        this.isSameBank = isSameBank;
    }



    private String bankSlug;
    private String recordId;
    private  String pin;
    private  String nuban;
    private String extra;
    private String bgColor;
    private String accentColor;
    private String buttonColor;
    private String payment;
    private String paymentCreditAccount;
    private String paymentAmount;
    private String paymentCreditBankName;
    private String isSameBank;

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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentCreditAccount() {
        return paymentCreditAccount;
    }

    public void setPaymentCreditAccount(String paymentCreditAccount) {
        this.paymentCreditAccount = paymentCreditAccount;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentCreditBankName() {
        return paymentCreditBankName;
    }

    public void setPaymentCreditBankName(String paymentCreditBankName) {
        this.paymentCreditBankName = paymentCreditBankName;
    }

    public String getIsSameBank() {
        return isSameBank;
    }

    public void setIsSameBank(String isSameBank) {
        this.isSameBank = isSameBank;
    }
}
