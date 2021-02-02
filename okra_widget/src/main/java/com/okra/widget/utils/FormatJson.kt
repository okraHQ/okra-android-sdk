package com.okra.widget.utils

import com.google.gson.Gson
import com.okra.widget.utils.successmodel.OkraResponse
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


object FormatJson {

    @JvmStatic
    fun formatJson(string:String):OkraResponse?{
        return try {
            Gson().fromJson(string, OkraResponse::class.java)
        }catch (ex:Exception){
            removeSVGFromV2Icon(string) ?: removeAllSVGIcons(string)
        }

    }

    private fun removeAllSVGIcons(string: String):OkraResponse? {
        val regex = "<svg.*?</svg>"
        val pattern = Pattern.compile(regex)
        val list: MutableList<String> = ArrayList<String>()
        val m: Matcher = pattern.matcher(string)
        while (m.find()) {
            list.add(m.group())
        }
        var cleanString = string
        list.forEach {
            cleanString = cleanString.replace(it, "")
        }

        return try {
            Gson().fromJson(cleanString, OkraResponse::class.java)
        } catch (ex: Exception) {
            println(ex)
            null
        }
    }

    private fun removeSVGFromV2Icon(string: String): OkraResponse? {
        val defaultingString = string.substringAfter("v2_icon\":\"").substringBefore("\",\"png_logo\"")
        val cleanString = if (defaultingString.contains("</svg>")) string.replace(defaultingString, "") else string
        return try {
            Gson().fromJson(cleanString, OkraResponse::class.java)
        } catch (ex: Exception) {
            null
        }
    }


}

