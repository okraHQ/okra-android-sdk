package com.okra.widget.utils.bank;

import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.AccountTransaction;
import com.okra.widget.models.request.Balance;
import com.okra.widget.models.request.BankRequest;

import org.json.JSONException;

public class FCMB extends BaseBank implements BankServices {

    private static int index = 1;

    @Override
    public int getActionCount() {
        return 3;
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
                return getTransactions();
            case 3:
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
        return this.getBvn("FCMB");
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "39bd69bb",
                "FCMB",
                "Fetching account balance",
                5000
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
        hoverStrategy.setRequiresPin(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "a755e3ec",
                "FCMB",
                "Fetching account transaction",
                10000
        );
        hoverStrategy.setId("transactions");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setRequiresPin(true);
        return hoverStrategy;
    }
}