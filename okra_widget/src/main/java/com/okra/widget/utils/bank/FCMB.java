package com.okra.widget.utils.bank;

import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
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
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "39bd69bb",
                "FCMB",
                "Fetching account balance",
                0
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "a755e3ec",
                "FCMB",
                "Fetching account transaction",
                0
        );
        hoverStrategy.setId("transactions");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }
}
