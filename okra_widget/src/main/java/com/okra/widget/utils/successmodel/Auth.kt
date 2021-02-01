package com.okra.widget.utils.successmodel

import java.io.Serializable

data class Auth (

		val clientId : String?,
		val type : String?,
		val status : Boolean?,
		val bank_details : Bank_details?,
		val login_type : String?
): Serializable