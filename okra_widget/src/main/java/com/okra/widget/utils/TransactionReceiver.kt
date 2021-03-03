package com.okra.widget.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.okra.widget.services.bundle2string


class TransactionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        println("USSD DATA 2 ${bundle2string(intent.extras)}")
    }
}