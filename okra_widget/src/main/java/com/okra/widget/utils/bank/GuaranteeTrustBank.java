package com.okra.widget.utils.bank;
import com.okra.widget.interfaces.BankServices;
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
        return new HoverStrategy(
                "ad6c3e9c",
                "Guarantee Trust Bank",
                "Fetching account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
