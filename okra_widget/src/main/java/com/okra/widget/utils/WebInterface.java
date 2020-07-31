package com.okra.widget.utils;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.OkraOptions;
import com.okra.widget.models.response.RecordIdResponse;

import okhttp3.Response;

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
            //todo: work on error code 400 being an actual error and not returning the recordID.
            Response response = new NetworkUtils(okraOptions).get("https://a7b864ef4c62.ngrok.io/v1/ussd/generate-record");//"https://api.okra.ng/v1/ussd/generate-record"
            RecordIdResponse recordIdResponse = new Gson().fromJson(response.body().string(), RecordIdResponse.class);
            String recordId = recordIdResponse.getData().getRecord_id();
            BankServices bankServices = BankUtils.getBankImplementation(bankAlias);
            try{
                BankUtils.fireIntent(mContext, bankServices.getActionByIndex(1), bankAlias, recordId);
            }catch (Exception ignored){}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
