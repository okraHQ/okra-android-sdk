package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

public class FidelityBank implements BankServices {
    @Override
    public HoverStrategy getBvn() throws Exception {
        return new HoverStrategy(
                "d85c09f7",
                "Fidelity Bank",
                "Fetching BVN",
                0
        );
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        return new HoverStrategy(
                "d85c09f7",
                "Fidelity Bank",
                "Fetching accounts",
                0
        );
    }

    @Override
    public HoverStrategy getAccountBalance() {
        return new HoverStrategy(
                "f6ccd22e",
                "Fidelity Bank",
                "Fetching account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
