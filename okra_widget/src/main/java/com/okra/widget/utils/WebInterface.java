package com.okra.widget.utils;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;

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
    public void onClose(String json) {}
}
