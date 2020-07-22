package com.okra.widget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

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
                fireIntent(bankServices.getBvn());
            }catch (Exception ex){}

            try {
                fireIntent(bankServices.getAccounts());
            }catch (Exception ex){}

            try {
                fireIntent(bankServices.getAccountBalance());
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

            try {
                fireIntent(bankServices.getTransactions());
            }catch (Exception ex){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fireIntent(HoverStrategy hoverStrategy) {
        Intent intent;
            HoverParameters.Builder hoverBuilder = new HoverParameters.Builder(mContext)
                    .private_extra("id", hoverStrategy.getId())
                    .private_extra("bankResponseMethod", hoverStrategy.getBankResponseMethod().toString())
                    .private_extra("isFirstAction", hoverStrategy.isFirstAction().toString())
                    .private_extra("isLastAction", hoverStrategy.isLastAction().toString())
                    .setHeader(hoverStrategy.getHeader()).initialProcessingMessage(hoverStrategy.getProcessingMessage())
                    .request(hoverStrategy.getActionId());

            if(!hoverStrategy.isFirstAction()){
                hoverBuilder.setSim(BankUtils.selectedSim.getImsi());
            }

            if(hoverStrategy.getDisplayTime() > 0){
                hoverBuilder.finalMsgDisplayTime(hoverStrategy.getDisplayTime());
            }


            intent = hoverBuilder.buildIntent();
        ((Activity)mContext).startActivityForResult(intent, 0);
    }
}
