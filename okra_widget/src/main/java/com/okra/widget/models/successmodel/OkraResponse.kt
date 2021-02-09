package com.okra.widget.models.successmodel

import java.io.Serializable

data class OkraResponse (

		val done : Boolean?,
		val record_id : String?,
		val record : String?,
		val bank_id : String?,
		val customer_id : String?,
		val balance : Balance?,
		val transactions : Transactions?,
		val guarantors : List<String>?,
		val redirect_url : String?,
		val callback_url : String?,
		val launchAgain : Boolean?,
		val hideExit : String?,
		val success_title : String?,
		val success_message : String?,
		//val options : Options?,
		val directors : String?,
		val auth : Auth?,
		val identity : Identity?,
		val income : Income?,
		val accounts : List<Accounts>?
): Serializable