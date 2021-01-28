package com.okra.widget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.hover.sdk.permissions.PermissionHelper;
import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.IntentData;

import org.json.JSONObject;

public class WebInterface {
    Context mContext;

    // Instantiate the interface and set the context
    public WebInterface(Context c) {
        mContext = c;
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
    public boolean permissionOn(String permission){
        boolean value = false;
        switch (permission) {
            case "message":
            case "messages":
                value = new PermissionHelper(mContext).hasSmsPerm();
                return value;
            case "accessibility":
                PermissionHelper ph = new PermissionHelper(mContext);
                value = ph.hasAccessPerm() && ph.hasBasicPerms() && ph.hasPhonePerm() && ph.hasOverlayPerm();
                return value;
            default: return false;
        }
    }

    @JavascriptInterface
    public void switchPermissionOn(String permission){
        PermissionHelper ph = new PermissionHelper(mContext);
        if(permission.equals("message") || permission.equals("messages")){
            if (!ph.hasBasicPerms()) {
                ph.requestBasicPerms((Activity) this.mContext, 1);
            }
            if (!ph.hasPhonePerm()) {
                ph.requestPhone((Activity) this.mContext, 1);
            }
        }else {
            if (!ph.hasAccessPerm()) {
                ph.requestAccessPerm();
            }
            if (!ph.hasOverlayPerm()) {
                ph.requestOverlayPerm();
            }
        }
    }

    @JavascriptInterface
    public void onClose(String json) {}

    @JavascriptInterface
    public void openUSSD(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String bankSlug = jsonObject.getJSONObject("bank").getString("slug");
            String bgColor = jsonObject.getJSONObject("bank").getString("bg");
            String accentColor = jsonObject.getJSONObject("bank").getString("accent");
            String buttonColor = jsonObject.getJSONObject("bank").getString("button");
            String pin = jsonObject.has("pin") ? jsonObject.getString("pin").trim().isEmpty() ? "" : jsonObject.getString("pin") : "";
            String nuban = jsonObject.has("account") ? jsonObject.getJSONObject("account").getString("number").trim()  : "";
            String recordId = jsonObject.has("record") ? jsonObject.getString("record") : "";
            BankServices bankServices = BankUtils.getBankImplementation(bankSlug);
            BankUtils.fireIntent(mContext, bankServices.getActionByIndex(1), new IntentData(bankSlug, recordId, pin, nuban, json, bgColor, accentColor, buttonColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}