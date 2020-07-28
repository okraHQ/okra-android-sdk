package com.okra.widget.interfaces;

import com.hover.sdk.transactions.Transaction;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.BankRequest;

public interface BankServices {
    int getActionCount();
    int getIndex();
    int setIndex(int index);
    HoverStrategy getNextAction() throws Exception;
    boolean hasNext();
    HoverStrategy getActionByIndex(int index) throws Exception;
    HoverStrategy getBvn() throws Exception;
    HoverStrategy getAccounts() throws Exception;
    HoverStrategy getAccountBalance();
    HoverStrategy getTransactions() throws Exception;
    BankRequest handleGetBvn(Transaction transaction, BankRequest bankRequest);
    BankRequest handleGetAccounts(Transaction transaction, BankRequest bankRequest);
    BankRequest handleGetAccountBalance(Transaction transaction, BankRequest bankRequest);
    BankRequest handleGetTransactions(Transaction transaction, BankRequest bankRequest);
}
