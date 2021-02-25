package com.okra.widget.utils

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SmsWorker(appContext: Context, workerParams: WorkerParameters)
: Worker(appContext, workerParams)  {
    override fun doWork(): Result {
        Log.i("partyneverstops", "so na very 10 secs")
        return Result.success()
    }
}