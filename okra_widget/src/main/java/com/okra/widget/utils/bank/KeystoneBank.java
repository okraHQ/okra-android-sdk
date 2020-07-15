package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

public class KeystoneBank implements BankServices {
    @Override
    public HoverStrategy getBvn() throws Exception {
        return new HoverStrategy(
                "2800326a",
                "Keystone Bank",
                "Fetching BVN",
                0
        );
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccountBalance() {
        return new HoverStrategy(
                "0cdcc20f",
                "Keystone Bank",
                "Fetching account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
