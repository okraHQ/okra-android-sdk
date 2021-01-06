package com.okra.widget.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.api.HoverTemplates;
import com.hover.sdk.sims.SimInfo;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.HoverStrategy;
import com.okra.widget.models.IntentData;
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
import java.util.Map;

public class BankUtils {

    public static int simSlot;
    public static SimInfo selectedSim;
    public static BankServices getBankImplementation(String bankAlias){
        switch (bankAlias.toLowerCase())
        {
            case "access-bank":
                return new AccessBank();
            case "first-city-monument-bank":
                System.out.println("this is it");
                return new FCMB();
            case "fidelity-bank":
                return new FidelityBank();
            case "first-bank-of-nigeria":
                return new FirstBank();
            case "guaranty-trust-bank":
                return new GuaranteeTrustBank();
            case "heritage-bank":
                return new HeritageBank();
            case "keystone-bank":
                return new KeystoneBank();
            case "polaris-bank":
                return new PolarisBank();
            case "stanbic-ibtc-bank":
                return new StanbicBank();
            case "sterling-bank":
                return new SterlingBank();
            case "union-bank-of-nigeria":
                return new UnionBank();
            case "unity-bank":
                return new UnityBank();
            case "wema-bank":
                return new WemaBank();
            case "zenith-bank":
                return new ZenithBank();
            default:
                return new AccessBank();
        }
    }

    public static void fireIntent(Context mContext, HoverStrategy hoverStrategy, IntentData intentData) {
        Intent intent;
        HoverParameters.Builder hoverBuilder = new HoverParameters.Builder(mContext)
                .private_extra("id", hoverStrategy.getId())
                .private_extra("bankResponseMethod", hoverStrategy.getBankResponseMethod().toString())
                .private_extra("isFirstAction", hoverStrategy.isFirstAction().toString())
                .private_extra("isLastAction", hoverStrategy.isLastAction().toString())
                .private_extra("bank", intentData.getBankSlug())
                .private_extra("recordId", intentData.getRecordId())
                .private_extra("miscellaneous", intentData.getExtra())

                .private_extra("bgColor", intentData.getBgColor())
                .private_extra("accentColor", intentData.getAccentColor())
                .private_extra("buttonColor", intentData.getButtonColor())

                .private_extra("authPin", intentData.getPin())
                .private_extra("apiKey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZDkyODhlYTE4MmQzZDAwMGNiN2M0ODYiLCJpYXQiOjE2MDE5ODIwODV9.R59jXuebkEPSrBjSSyo0rIveiw07-YrioEtP-YxcXWc")
                .setHeader(hoverStrategy.getHeader()).initialProcessingMessage(hoverStrategy.getProcessingMessage())
                .template(HoverTemplates.OKRA)
                .setHeader(String.format("Connecting to %s...", intentData.getBankSlug().replace("-", " ")))
                .initialProcessingMessage("Verifying your credentials")
                .usableColors(Color.parseColor(intentData.getBgColor()), Color.WHITE, Color.parseColor(intentData.getButtonColor())) // .usableColors takes 3 colors, (Background color, Accent Color, Button Color)
                .request(hoverStrategy.getActionId());
        if(!intentData.getPin().isEmpty() || !intentData.getPin().trim().isEmpty()){
            hoverBuilder.extra("pin", intentData.getPin());
        }

        if(!hoverStrategy.isFirstAction()){
            hoverBuilder.setSim(BankUtils.selectedSim.getOSReportedHni());
        }

        hoverBuilder.finalMsgDisplayTime(0);

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
            BankUtils.simSlot = intent.getIntExtra("slot_idx", -1);
            return Hover.getPresentSims(context).get(BankUtils.simSlot);
        }catch (Exception exception){
            return null;
        }
    }
}
