package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

public class SterlingBank implements BankServices {

    @Override
    public HoverStrategy getBvn() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccounts() {
        return new HoverStrategy(
                "b46ceac3",
                "Sterling Bank",
                "Fetching Accounts",
                0
        );
    }

    @Override
    public HoverStrategy getAccountBalance() {
        return new HoverStrategy(
                "79a457c7",
                "Sterling Bank",
                "Fetching Account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
