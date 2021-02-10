package com.okra.widget.models.OkHiModels

import io.okhi.android_core.models.OkHiException

data class LocationException(val code:String,val message:String)

fun OkHiException.toLocationException():LocationException{
    return LocationException(code,message ?: "Error")
}