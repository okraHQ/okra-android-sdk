package com.okra.widget.utils.successmodel

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Accounts (

	val manual : Boolean?,
	val nuban : String?,
	val id : String?,
	val connected : Boolean?,
	val name : String?
):Serializable