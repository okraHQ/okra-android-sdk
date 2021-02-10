package com.okra.widget.models.OkHiModels

import io.okhi.android_core.models.OkHiLocation

data class Location( val id:String,val   lat:Double, val  lon:Double)


fun OkHiLocation.toLocation():Location{
    return Location(id, lat, lon)
}

fun Location.toOkHiLocation():OkHiLocation{
    return OkHiLocation(id, lat, lon)
}