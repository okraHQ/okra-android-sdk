package com.okra.widget.utils.bank;

import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.Balance;
import com.okra.widget.models.request.BankRequest;
import com.okra.widget.utils.BankUtils;

import org.json.JSONException;

public class SterlingBank implements BankServices {

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
        SterlingBank.index = index;
        return SterlingBank.index;
    }

    @Override
    public HoverStrategy getActionByIndex(int index) {
        switch (index){
            case 1:
                return getAccounts();
            case 2:
                return getAccountBalance();
            default:
                return getAccounts();
        }
    }

    @Override
    public HoverStrategy getNextAction() throws Exception {
        if(index >= getActionCount()){index = 0;}
        return getActionByIndex(index + 1);
    }

    @Override
    public boolean hasNext() {
        return index < getActionCount();
    }

    @Override
    public HoverStrategy getBvn() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccounts() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "b46ceac3",
                "Sterling Bank",
                "Fetching Accounts",
                0
        );
        hoverStrategy.setId("accounts");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "79a457c7",
                "Sterling Bank",
                "Fetching Account balance",
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
        return bankRequest;
    }

    @Override
    public BankRequest handleGetAccounts(Transaction transaction, BankRequest bankRequest) {
        Balance balance = new Balance();
        try {
            balance.setAccountNumber(transaction.parsed_variables.getString("accountNumberOne"));
            balance.setAccountType(transaction.parsed_variables.getString("accountTypeOne"));
            bankRequest.getBalance().add(balance);
        } catch (JSONException ignored) {}
        try {
            balance.setAccountNumber(transaction.parsed_variables.getString("accountNumberTwo"));
            balance.setAccountType(transaction.parsed_variables.getString("accountTypeTwo"));
            bankRequest.getBalance().add(balance);
        } catch (JSONException ignored) {}
        return bankRequest;
    }

    @Override
    public BankRequest handleGetAccountBalance(Transaction transaction, BankRequest bankRequest) {
        try {
            String accountType = transaction.parsed_variables.getString("accountTypeOne");
            String accountNumber = transaction.parsed_variables.getString("accountNumberOne");
            double accountBalance = transaction.parsed_variables.getDouble("accountBalanceOne");

            Balance balance = new Balance();
            if(!bankRequest.getBalance().isEmpty()){
                boolean hasAccountNumber = BankUtils.hasAccountNumber(bankRequest.getBalance(), accountNumber);
                if (hasAccountNumber) BankUtils.findBalanceAndUpdateAccountBalance(bankRequest.getBalance(), accountNumber, accountBalance);
            }else {
                balance.setAccountNumber(accountNumber);
                balance.setAccountType(accountType);
                balance.setAvailableBalance(accountBalance);
                balance.setCurrentBalance(accountBalance);
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
