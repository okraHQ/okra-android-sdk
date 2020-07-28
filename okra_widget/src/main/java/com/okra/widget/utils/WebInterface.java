package com.okra.widget.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.webkit.JavascriptInterface;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.sims.SimInfo;
import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.OkraOptions;

import java.util.List;

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
            try{
                BankUtils.fireIntent(mContext, bankServices.getActionByIndex(1), bankAlias);
            }catch (Exception ex){}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
