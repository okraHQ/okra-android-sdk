package com.okra.widget.utils

import com.google.gson.Gson
import com.okra.widget.utils.successmodel.OkraResponse


object FormatJson {

    @JvmStatic
    fun formatJson(string:String):OkraResponse?{
        val defaultingString = string.substringAfter("v2_icon\":\"").substringBefore("\",\"png_logo\"")
        val cleanString =  if(defaultingString.contains("</svg>")) string.replace(defaultingString,"") else string
        return try {
            Gson().fromJson(cleanString, OkraResponse::class.java)
        }catch (ex:Exception){
            null}
    }
}

