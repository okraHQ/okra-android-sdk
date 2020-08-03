package com.okra.widget.utils.bank;

import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.Balance;
import com.okra.widget.models.request.BankRequest;
import com.okra.widget.models.request.Identity;

import org.json.JSONException;

public class PolarisBank extends BaseBank implements BankServices {

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
                return getBvn();
            case 2:
                return getAccountBalance();
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
        return  this.getBvn("Polaris Bank");
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "0c493784",
                "polaris Bank",
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
        try {
            Identity identity = new Identity();
            identity.setBvn(transaction.parsed_variables.getString("bvn"));
            bankRequest.setIdentity(identity);
        } catch (JSONException ignored) {}
        return bankRequest;
    }

    @Override
    public BankRequest handleGetAccounts(Transaction transaction, BankRequest bankRequest) {
        return bankRequest;
    }

    @Override
    public BankRequest handleGetAccountBalance(Transaction transaction, BankRequest bankRequest) {
        try {
            Balance balance = new Balance();
            balance.setAccountNumber(transaction.parsed_variables.getString("accountNumber"));
            double foundBalance = transaction.parsed_variables.getDouble("accountBalance");
            balance.setAvailableBalance(foundBalance);
            balance.setCurrentBalance(foundBalance);
            bankRequest.getBalance().add(balance);
        } catch (JSONException ignored) {}
        return bankRequest;
    }

    @Override
    public BankRequest handleGetTransactions(Transaction transaction, BankRequest bankRequest) {
        return bankRequest;
    }
}
