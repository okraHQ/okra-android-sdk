package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;

public class StanbicBank implements BankServices {
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
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "f6ccd22e",
                "Stanbic Bank",
                "Fetching account balance",
                0
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
