package com.okra.widget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.hover.sdk.api.HoverParameters;
import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.OkraOptions;

public class WebInterface {
    Context mContext;
    OkraOptions okraOptions;

    // Instantiate the interface and set the context
    public WebInterface(Context c, OkraOptions okraOptions) {
        mContext = c;
        this.okraOptions = okraOptions;
    }

    @JavascriptInterface
    public void exitModal() {
        Intent intent = new Intent(mContext, Okra.baseContext.getClass());
        mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void onSuccess(String json) {
        OkraHandler.data = json;
        OkraHandler.isSuccessful = true;
        OkraHandler.isDone = true;
    }

    @JavascriptInterface
    public void onError(String json) {
        OkraHandler.data = json;
        OkraHandler.hasError = true;
        OkraHandler.isDone = true;
    }

    @JavascriptInterface
    public void onClose(String json) {}

    @JavascriptInterface
    public void openUssd(String bankAlias) {
        try {
            BankServices bankServices = BankUtils.getBankImplementation(bankAlias);
            fireIntent(bankServices.getBvn());
            fireIntent(bankServices.getAccounts());
            fireIntent(bankServices.getAccountBalance());
            fireIntent(bankServices.getTransactions());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fireIntent(HoverStrategy hoverStrategy) {
        Intent intent = new HoverParameters.Builder(mContext).finalMsgDisplayTime(hoverStrategy.getDisplayTime())
                .setHeader(hoverStrategy.getHeader()).initialProcessingMessage(hoverStrategy.getProcessingMessage())
                .request(hoverStrategy.getActionId())
                    .buildIntent();
        ((Activity)mContext).startActivityForResult(intent, 0);
    }
}
