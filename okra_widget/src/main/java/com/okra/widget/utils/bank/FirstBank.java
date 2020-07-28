package com.okra.widget.utils.bank;

import com.hover.sdk.api.Hover;
import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.BankRequest;

public class FirstBank implements BankServices {

    private static int index = 1;

    @Override
    public int getActionCount() {
        return 4;
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
                return getBvn();
            case 2:
                return getAccounts();
            case 3:
                return getAccountBalance();
            case 4:
                return getTransactions();
            default:
                return getBvn();
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
        HoverStrategy hoverStrategy = new HoverStrategy(
                "c034108e",
                "First Bank",
                "Fetching BVN",
                0
        );
        hoverStrategy.setId("bvn");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        hoverStrategy.setFirstAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "87276a42",
                "First Bank",
                "Fetching account(s)",
                0
        );
        hoverStrategy.setId("accounts");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "dd25c8eb",
                "First Bank",
                "Fetching account balance",
                0
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "eb9915fc",
                "First Bank",
                "Fetching transaction(s)",
                0
        );
        hoverStrategy.setId("transactions");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
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
