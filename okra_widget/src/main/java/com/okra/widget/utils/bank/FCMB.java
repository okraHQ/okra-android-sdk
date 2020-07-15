package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

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
        return new HoverStrategy(
                "39bd69bb",
                "FCMB",
                "Fetching account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() {
        return new HoverStrategy(
                "a755e3ec",
                "FCMB",
                "Fetching account transaction",
                0
        );
    }
}
