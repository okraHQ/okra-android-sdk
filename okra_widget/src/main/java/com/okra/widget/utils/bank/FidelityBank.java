package com.okra.widget.utils.bank;

import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.Balance;
import com.okra.widget.models.request.BankRequest;
import com.okra.widget.models.request.Identity;
import com.okra.widget.utils.BankUtils;

import org.json.JSONException;

public class FidelityBank implements BankServices {

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
                return getBvn();
            case 2:
                return getAccounts();
            case 3:
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
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "d85c09f7",
                "Fidelity Bank",
                "Fetching BVN",
                0
        );
        hoverStrategy.setId("bvn");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "d85c09f7",
                "Fidelity Bank",
                "Fetching accounts",
                0
        );
        hoverStrategy.setId("accounts");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "f6ccd22e",
                "Fidelity Bank",
                "Fetching account balance",
                0
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

        Balance balance = new Balance();
        try {
            balance.setAccountType(transaction.parsed_variables.getString("accountType"));
            balance.setAccountNumber(transaction.parsed_variables.getString("accountNumber"));
            bankRequest.getBalance().add(balance);
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
            double accountBalance = transaction.parsed_variables.getDouble("accountBalance");
            String accountNumber = transaction.parsed_variables.getString("accountNumber");
            if (!bankRequest.getBalance().isEmpty() && BankUtils.hasAccountNumber(bankRequest.getBalance(), accountNumber)){
               BankUtils.findBalanceAndUpdateAccountBalance(bankRequest.getBalance(), accountNumber, accountBalance);
            }else{
                balance.setAvailableBalance(accountBalance);
                balance.setCurrentBalance(accountBalance);
                balance.setAccountNumber(accountNumber);
                bankRequest.getBalance().add(balance);
            }
        } catch (JSONException ignored) {}
        return bankRequest;
    }

    @Override
    public BankRequest handleGetTransactions(Transaction transaction, BankRequest bankRequest) {
        return bankRequest;
    }
}