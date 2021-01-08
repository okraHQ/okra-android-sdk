package com.okra.widget.utils.bank;

import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.Balance;
import com.okra.widget.models.request.BankRequest;

import org.json.JSONException;

public class WemaBank extends BaseBank implements BankServices {

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
    public HoverStrategy getActionByIndex(int index) throws Exception {
        switch (index){
            case 1:
                return getAccountBalance();
            case 2:
                return getBvn();
            default:
                return getAccountBalance();
        }
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
    public HoverStrategy getBvn() throws Exception {
        return  this.getBvn("Wema Bank");
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "9d5a306b",
                "Wema Bank",
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
}
