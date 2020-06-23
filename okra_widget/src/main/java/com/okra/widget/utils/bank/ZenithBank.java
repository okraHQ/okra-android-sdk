package com.okra.widget.utils.bank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hover.sdk.api.HoverParameters;
import com.okra.widget.interfaces.BankServices;

public class ZenithBank implements BankServices {
    private Context context;
    public ZenithBank(Context context){
        this.context = context;
    }

    @Override
    public void getBvn() {

    }

    @Override
    public void getAccounts() {

    }

    @Override
    public void getAccountBalance() {
        Intent intent = new HoverParameters.Builder(this.context)
                .setHeader("Working").initialProcessingMessage("please wait")
                .request("0ef3e9f6")
                //.extra("step_variable_name", "")
                .buildIntent();
        ((Activity) this.context).startActivityForResult(intent, 0);
    }

    @Override
    public void getTransactions() {

    }
}
