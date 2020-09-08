package com.okra.widget.utils;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.IntentData;
import com.okra.widget.models.OkraOptions;

import org.json.JSONObject;

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
    public String getCustomer(){
        return "08098516969";
    }

    @JavascriptInterface
    public void onClose(String json) {}

    @JavascriptInterface
    public void openUSSD(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String bankSlug = jsonObject.getJSONObject("bank").getString("slug");
            String pin = jsonObject.has("pin") ? jsonObject.getString("pin").trim().isEmpty() ? "" : jsonObject.getString("pin") : "";
            String recordId = jsonObject.has("record") ? jsonObject.getString("record") : "";
            BankServices bankServices = BankUtils.getBankImplementation(bankSlug);
            try{
                BankUtils.fireIntent(mContext, bankServices.getActionByIndex(1), new IntentData(bankSlug, recordId, pin, json));
            }catch (Exception ignored){}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}