package com.okra.widget.models.OkHiModels

import io.okhi.android_core.models.OkHiUser

data class User(val firstName:String,val lastName:String,
                val phone:String,val id:String)

fun OkHiUser.toUser():User{
    return User(firstName, lastName, phone, id)
}