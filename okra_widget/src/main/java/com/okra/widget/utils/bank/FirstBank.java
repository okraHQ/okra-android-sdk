package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

public class FirstBank implements BankServices {
    @Override
    public HoverStrategy getBvn() throws Exception {
        return new HoverStrategy(
                "c034108e",
                "First Bank",
                "Fetching BVN",
                0
        );
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        return new HoverStrategy(
                "87276a42",
                "First Bank",
                "Fetching account(s)",
                0
        );
    }

    @Override
    public HoverStrategy getAccountBalance() {
        return new HoverStrategy(
                "dd25c8eb",
                "First Bank",
                "Fetching account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        return new HoverStrategy(
                "eb9915fc",
                "First Bank",
                "Fetching transaction(s)",
                0
        );
    }
}
