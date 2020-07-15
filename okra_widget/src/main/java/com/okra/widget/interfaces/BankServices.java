package com.okra.widget.interfaces;

import com.okra.widget.models.HoverStrategy;

public interface BankServices {
    HoverStrategy getBvn() throws Exception;
    HoverStrategy getAccounts() throws Exception;
    HoverStrategy getAccountBalance();
    HoverStrategy getTransactions() throws Exception;
}
