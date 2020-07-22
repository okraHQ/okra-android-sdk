package com.okra.widget.utils.bank;

import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.AccountTransaction;
import com.okra.widget.models.request.Balance;
import com.okra.widget.models.request.BankRequest;

import org.json.JSONException;

public class FCMB implements BankServices {

    @Override
    public HoverStrategy getBvn() throws Exception {
        throw new Exception("Not implemented");
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
                0
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "a755e3ec",
                "FCMB",
                "Fetching account transaction",
                0
        );
        hoverStrategy.setId("transactions");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }

    @Override
    public BankRequest handleGetBvn(Transaction transaction, BankRequest bankRequest) {
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
            double foundBalance = transaction.parsed_variables.getDouble("accountBalance");
            balance.setAvailableBalance(foundBalance);
            balance.setCurrentBalance(foundBalance);
            bankRequest.getBalance().add(balance);
        } catch (JSONException ignored) {}
        return bankRequest;
    }

    @Override
    public BankRequest handleGetTransactions(Transaction transaction, BankRequest bankRequest) {
        AccountTransaction accountTransaction = new AccountTransaction();
        if(transaction.parsed_variables.has("transactionDateOne")){
            try {
                accountTransaction.setTransactionType(transaction.parsed_variables.getString("transactionTypeOne"));
                accountTransaction.setDate(transaction.parsed_variables.getString("transactionDateOne"));
                accountTransaction.setAmount(transaction.parsed_variables.getDouble("transactionAmountOne"));
                bankRequest.getAccountTransactions().add(accountTransaction);
            } catch (JSONException ignored) {}
        }

        if(transaction.parsed_variables.has("transactionDateTwo")){
            try {
                accountTransaction.setTransactionType(transaction.parsed_variables.getString("transactionTypeTwo"));
                accountTransaction.setDate(transaction.parsed_variables.getString("transactionDateTwo"));
                accountTransaction.setAmount(transaction.parsed_variables.getDouble("transactionAmountTwo"));
                bankRequest.getAccountTransactions().add(accountTransaction);
            } catch (JSONException ignored) {}
        }

        if(transaction.parsed_variables.has("transactionDateThree")){
            try {
                accountTransaction.setTransactionType(transaction.parsed_variables.getString("transactionTypeThree"));
                accountTransaction.setDate(transaction.parsed_variables.getString("transactionDateThree"));
                accountTransaction.setAmount(transaction.parsed_variables.getDouble("transactionAmountThree"));
                bankRequest.getAccountTransactions().add(accountTransaction);
            } catch (JSONException ignored) {}
        }

        if(transaction.parsed_variables.has("transactionDateFour")){
            try {
                accountTransaction.setTransactionType(transaction.parsed_variables.getString("transactionTypeFour"));
                accountTransaction.setDate(transaction.parsed_variables.getString("transactionDateFour"));
                accountTransaction.setAmount(transaction.parsed_variables.getDouble("transactionAmountFour"));
                bankRequest.getAccountTransactions().add(accountTransaction);
            } catch (JSONException ignored) {}
        }
        return bankRequest;
    }
}
