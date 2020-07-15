package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

public class UnityBank implements BankServices {
    @Override
    public HoverStrategy getBvn() throws Exception {
        return new HoverStrategy(
                "f641760d",
                "Unity Bank",
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
                "2761e90d",
                "Unity Bank",
                "Fetching account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
