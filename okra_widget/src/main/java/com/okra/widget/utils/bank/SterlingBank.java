package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;

public class SterlingBank implements BankServices {

    @Override
    public HoverStrategy getBvn() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public HoverStrategy getAccounts() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "b46ceac3",
                "Sterling Bank",
                "Fetching Accounts",
                0
        );
        hoverStrategy.setId("accounts");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "79a457c7",
                "Sterling Bank",
                "Fetching Account balance",
                0
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
