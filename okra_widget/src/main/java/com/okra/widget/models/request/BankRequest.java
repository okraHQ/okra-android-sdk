package com.okra.widget.models.request;

import java.util.ArrayList;

public class BankRequest {
    private String recordId;
    private Auth auth;
    private ArrayList<Balance> balance = new ArrayList<>();
    private Identity identity;
    private ArrayList<Transaction> transactions = new ArrayList<>();


    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public ArrayList<Balance> getBalance() {
        return balance;
    }

    public void setBalance(ArrayList<Balance> balance) {
        this.balance = balance;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
