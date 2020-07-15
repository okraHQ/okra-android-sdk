package com.okra.widget.utils.bank;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

public class AccessBank implements BankServices {
    @Override
    public HoverStrategy getBvn() {
       return new HoverStrategy(
               "46f6e4e2",
               "Access Bank",
               "Fetching BVN",
               0
       );
    }

    @Override
    public HoverStrategy getAccounts() {
        return new HoverStrategy(
                "6732cb89",
                "Access Bank",
                "Fetching your account number(s)",
                0
        );
    }

    @Override
    public HoverStrategy getAccountBalance() {
        return new HoverStrategy(
                "7377a940",
                "Access Bank",
                "Fetching your account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() {
        return new HoverStrategy(
                "5584dd8c",
                "Access Bank",
                "Fetching your account statement",
                0
        );
    }
}
