package com.okra.widget.utils.bank;
import com.hover.sdk.transactions.Transaction;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.BankRequest;

public class AccessBank extends BaseBank implements BankServices {

    public static int index = 1;

    @Override
    public int getActionCount() {
        return 4;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int setIndex(int index) {
        AccessBank.index = index;
        return AccessBank.index;
    }

    @Override
    public HoverStrategy getNextAction() {
        if(index >= getActionCount()){this.index = 0;}
        return getActionByIndex(index + 1);
    }

    @Override
    public boolean hasNext() {
        return AccessBank.index < getActionCount();
    }

    @Override
    public HoverStrategy getActionByIndex(int index) {
        switch (index){
            case 1:
                return getAccounts();
            case 2:
                return getAccountBalance();
            case 3:
                return getTransactions();
            case 4:
                return getBvn();
            default:
                return getBvn();
        }
    }

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
                10000
        );
        hoverStrategy.setId("transactions");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.sms);
        hoverStrategy.setLastAction(true);
        return hoverStrategy;
    }
}
