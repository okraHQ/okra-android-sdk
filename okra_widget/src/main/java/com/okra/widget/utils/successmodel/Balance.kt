package com.okra.widget.utils.successmodel

import java.io.Serializable


data class Balance (

	val clientId : String?,
	val type : String?,
	val status : Boolean?,
	val balance_callback : String?,
	val data : Data?
): Serializable