package com.okra.widget.utils;

import com.hover.sdk.sims.SimInfo;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.utils.bank.AccessBank;
import com.okra.widget.utils.bank.FCMB;
import com.okra.widget.utils.bank.FidelityBank;
import com.okra.widget.utils.bank.FirstBank;
import com.okra.widget.utils.bank.GuaranteeTrustBank;
import com.okra.widget.utils.bank.HeritageBank;
import com.okra.widget.utils.bank.KeystoneBank;
import com.okra.widget.utils.bank.StanbicBank;
import com.okra.widget.utils.bank.SterlingBank;
import com.okra.widget.utils.bank.UnionBank;
import com.okra.widget.utils.bank.UnityBank;
import com.okra.widget.utils.bank.WemaBank;
import com.okra.widget.utils.bank.ZenithBank;

public class BankUtils {

    public static int simSlot;
    public static SimInfo selectedSim;
    public static BankServices getBankImplementation(String bankAlias) throws Exception {
        switch (bankAlias.toLowerCase())
        {
            case "access bank":
                return new AccessBank();
            case "fcmb":
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
                throw new Exception("Bank implementation for alias" + bankAlias + "not found");
        }
    }
}
