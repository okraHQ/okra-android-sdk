package com.okra.widget.utils.successmodel

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Auth (

		val clientId : String?,
		val type : String?,
		val status : Boolean?,
		val bank_details : Bank_details?,
		val login_type : String?
): Serializable