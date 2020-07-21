package com.okra.widget.utils.bank;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;

public class GuaranteeTrustBank implements BankServices {
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
        HoverStrategy hoverStrategy = new HoverStrategy(
                "ad6c3e9c",
                "balance",
                "Guarantee Trust Bank",
                "Fetching account balance",
                1
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
