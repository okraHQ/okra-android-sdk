package com.okra.widget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.sims.SimInfo;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverResponse;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.request.Balance;
import com.okra.widget.utils.bank.AccessBank;
import com.okra.widget.utils.bank.FCMB;
import com.okra.widget.utils.bank.FidelityBank;
import com.okra.widget.utils.bank.FirstBank;
import com.okra.widget.utils.bank.GuaranteeTrustBank;
import com.okra.widget.utils.bank.HeritageBank;
import com.okra.widget.utils.bank.KeystoneBank;
import com.okra.widget.utils.bank.PolarisBank;
import com.okra.widget.utils.bank.StanbicBank;
import com.okra.widget.utils.bank.SterlingBank;
import com.okra.widget.utils.bank.UnionBank;
import com.okra.widget.utils.bank.UnityBank;
import com.okra.widget.utils.bank.WemaBank;
import com.okra.widget.utils.bank.ZenithBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankUtils {

    public static int simSlot;
    public static SimInfo selectedSim;
    public static BankServices getBankImplementation(String bankAlias){
        switch (bankAlias.toLowerCase())
        {
            case "access bank":
                return new AccessBank();
            case "fcmb":
                System.out.println("this is it");
                return new FCMB();
            case "fidelity bank":
                return new FidelityBank();
            case "first bank":
                return new FirstBank();
            case "gt bank":
                return new GuaranteeTrustBank();
            case "heritage bank":
                return new HeritageBank();
            case "keystone bank":
                return new KeystoneBank();
            case "polaris bank":
                return new PolarisBank();
            case "stanbic bank":
                return new StanbicBank();
            case "sterling bank":
                return new SterlingBank();
            case "union bank":
                return new UnionBank();
            case "unity bank":
                return new UnityBank();
            case "wema bank":
                return new WemaBank();
            case "zenith bank":
                return new ZenithBank();
            default:
                return new AccessBank();
        }
    }

    public static boolean hasAccountNumber(ArrayList<Balance> balances, String accountNumber){
        boolean hasAccountNumber = false;
        for (Balance balance: balances) {
            if(balance.getAccountNumber().equals(accountNumber)){
                hasAccountNumber = true;
                break;
            }
        }
        return hasAccountNumber;
    }

    public static boolean findBalanceAndUpdateAccountBalance(ArrayList<Balance> balances, String accountNumber, double accountBalance){
        boolean hasUpdatedAccountBalance = false;
        for (Balance balance: balances) {
            if(balance.getAccountNumber().equals(accountNumber)){
                hasUpdatedAccountBalance = true;
                balance.setCurrentBalance(accountBalance);
                balance.setAvailableBalance(accountBalance);
                break;
            }
        }
        return hasUpdatedAccountBalance;
    }

    public static void fireIntent(Context mContext, HoverStrategy hoverStrategy, String bankAlias, String recordId) {
        Intent intent;
        HoverParameters.Builder hoverBuilder = new HoverParameters.Builder(mContext)
                .private_extra("id", hoverStrategy.getId())
                .private_extra("bankResponseMethod", hoverStrategy.getBankResponseMethod().toString())
                .private_extra("isFirstAction", hoverStrategy.isFirstAction().toString())
                .private_extra("isLastAction", hoverStrategy.isLastAction().toString())
                .private_extra("bank", bankAlias)
                .private_extra("recordId", recordId)
                .setHeader(hoverStrategy.getHeader()).initialProcessingMessage(hoverStrategy.getProcessingMessage())
                .request(hoverStrategy.getActionId());

        if(!hoverStrategy.isFirstAction()){
            hoverBuilder.setSim(BankUtils.selectedSim.getOSReportedHni());
        }

        //if(hoverStrategy.getDisplayTime() > 0){
            hoverBuilder.finalMsgDisplayTime(hoverStrategy.getDisplayTime());
        //}


        intent = hoverBuilder.buildIntent();
        ((Activity)mContext).startActivityForResult(intent, 0);
    }

    public static Map<String, String> getInputExtras(Intent intent){
        Bundle bundle = intent.getExtras();
        if(bundle !=null && intent.hasExtra("input_extras")) {
            return (Map<String, String>) bundle.get("input_extras");
        }
        return null;
    }

    public static boolean isFirstAction(Map<String, String> map){
        if(map == null) return false;
        if(map.isEmpty()) return false;
        if(!map.containsKey("isFirstAction")) return false;
        try {
            return Boolean.parseBoolean(map.get("isFirstAction"));
        }catch (Exception exception){
            return false;
        }
    }

    public static SimInfo getSelectedSim(Context context, Intent intent){
        try {
            BankUtils.simSlot = intent.getIntExtra("SimIdx", -1);
            return Hover.getPresentSims(context).get(BankUtils.simSlot);
        }catch (Exception exception){
            return null;
        }
    }

    public static HoverResponse getHoverResponse(Intent intent){
        HoverResponse hoverResponse = new HoverResponse();
        try {
            hoverResponse.setSessionMessages(intent.getStringArrayExtra("session_messages"));
        }catch (Exception ignored){}

        try{
         hoverResponse.setUuid(intent.getStringExtra("uuid"));
        }catch (Exception ignored){ }

        return  hoverResponse;
    }
}
