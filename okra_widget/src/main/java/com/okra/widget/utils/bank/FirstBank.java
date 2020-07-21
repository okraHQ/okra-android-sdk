package com.okra.widget.utils.bank;

import com.hover.sdk.api.Hover;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;

public class FirstBank implements BankServices {
    @Override
    public HoverStrategy getBvn() throws Exception {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "c034108e",
                "First Bank",
                "Fetching BVN",
                0
        );
        hoverStrategy.setId("bvn");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        hoverStrategy.setFirstAction(true);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccounts() throws Exception {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "87276a42",
                "First Bank",
                "Fetching account(s)",
                0
        );
        hoverStrategy.setId("accounts");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "dd25c8eb",
                "First Bank",
                "Fetching account balance",
                0
        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "eb9915fc",
                "First Bank",
                "Fetching transaction(s)",
                0
        );
        hoverStrategy.setId("transactions");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }
}
