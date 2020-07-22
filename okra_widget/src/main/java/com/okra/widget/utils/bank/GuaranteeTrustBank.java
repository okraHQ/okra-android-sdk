package com.okra.widget.utils.bank;
import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.Balance;
import com.okra.widget.models.request.BankRequest;
import com.okra.widget.models.request.Identity;

import org.json.JSONException;

public class GuaranteeTrustBank implements BankServices {
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
        HoverStrategy hoverStrategy = new HoverStrategy(
                "ad6c3e9c",
                "balance",
                "Guarantee Trust Bank",
                "Fetching account balance",
                1
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
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
        return bankRequest;
    }

    @Override
    public BankRequest handleGetAccountBalance(Transaction transaction, BankRequest bankRequest) {
        //((?<>[\w\.]+) - NGN(?<accountBalance>[\d\.\,]+)) NAME: (?<>[\D\s]+) BVN: (?<bvn>[\w\.]+).*

        try{
            Identity identity = new Identity();
            identity.setBvn(transaction.parsed_variables.getString("bvn"));
            bankRequest.setIdentity(identity);
        }catch (JSONException ignored) {}

        try {
            Balance balance = new Balance();
            balance.setAccountName(transaction.parsed_variables.getString("accountName"));
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
