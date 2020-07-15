package com.okra.widget.utils.bank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hover.sdk.api.HoverParameters;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;

public class ZenithBank implements BankServices {
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
                "11229370",
                "Zenith Bank",
                "Fetching account balance",
                0
        );
    }

    @Override
    public HoverStrategy getTransactions() throws Exception {
        throw new Exception("Not implemented");
    }
}
