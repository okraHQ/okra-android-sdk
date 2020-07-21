package com.okra.widget.utils.bank;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;

public class AccessBank implements BankServices {
    @Override
    public HoverStrategy getBvn() {
       HoverStrategy hoverStrategy =  new HoverStrategy(
               "46f6e4e2",
               "bvn",
               "Access Bank",
               "Fetching BVN",
               0
       );
       hoverStrategy.setId("bvn");
       hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
       hoverStrategy.setFirstAction(true);
       return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccounts() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "6732cb89",
                "Access Bank",
                "Fetching your account number(s)",
                0
        );
        hoverStrategy.setId("accounts");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getAccountBalance() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "7377a940",
                "Access Bank",
                "Fetching your account balance",
                0

        );
        hoverStrategy.setId("balance");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        return hoverStrategy;
    }

    @Override
    public HoverStrategy getTransactions() {
        HoverStrategy hoverStrategy = new HoverStrategy(
                "5584dd8c",
                "Access Bank",
                "Fetching your account statement",
                0
        );
        hoverStrategy.setId("transactions");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }
}
