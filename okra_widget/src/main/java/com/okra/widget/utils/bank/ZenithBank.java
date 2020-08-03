package com.okra.widget.utils.bank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.BankRequest;

public class ZenithBank extends BaseBank implements BankServices {

    private static int index = 1;

    @Override
    public int getActionCount() {
        return 2;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int setIndex(int index) {
        this.index = index;
        return this.index;
    }

    @Override
    public HoverStrategy getNextAction() throws Exception {
        if(index >= getActionCount()){this.index = 0;}
        return getActionByIndex(index + 1);
    }

    @Override
    public boolean hasNext() {
        return this.index < getActionCount();
    }

    @Override
    public HoverStrategy getActionByIndex(int index) throws Exception {
        switch (index){
            case 1:
                return getBvn();
            case 2:
                return getAccountBalance();
            default:
                return getAccountBalance();
        }
    }

    @Override
    public HoverStrategy getBvn() throws Exception {
        return this.getBvn("Zenith Bank");
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "11229370",
                "Zenith Bank",
                "Fetching account balance",
                10000
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public BankRequest handleGetBvn(Transaction transaction, BankRequest bankRequest) {
        return null;
    }

    @Override
    public BankRequest handleGetAccounts(Transaction transaction, BankRequest bankRequest) {
        return null;
    }

    @Override
    public BankRequest handleGetAccountBalance(Transaction transaction, BankRequest bankRequest) {
        return null;
    }

    @Override
    public BankRequest handleGetTransactions(Transaction transaction, BankRequest bankRequest) {
        return null;
    }
}
