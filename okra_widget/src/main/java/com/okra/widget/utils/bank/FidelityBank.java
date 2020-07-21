package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;

public class FidelityBank implements BankServices {
    @Override
    public HoverStrategy getBvn() throws Exception {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "d85c09f7",
                "Fidelity Bank",
                "Fetching BVN",
                0
        );
        hoverStrategy.setId("bvn");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "d85c09f7",
                "Fidelity Bank",
                "Fetching accounts",
                0
        );
        hoverStrategy.setId("accounts");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "f6ccd22e",
                "Fidelity Bank",
                "Fetching account balance",
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
