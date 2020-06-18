package com.okra.widget.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.okra.widget.interfaces.SmsListener;

public class SmsReceiver extends BroadcastReceiver {
    SmsListener smsListener;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for (int index = 0 ; index < pdus.length; index++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[index]);
            smsListener.messageReceived(smsMessage);
        }
    }

    public void bindListener(SmsListener listener) {
        this.smsListener = listener;
    }
}
