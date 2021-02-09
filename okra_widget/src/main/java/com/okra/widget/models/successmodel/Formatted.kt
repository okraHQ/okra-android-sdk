package com.okra.widget.models.successmodel

import java.io.Serializable


data class Formatted (

	val available_balance : Double?,
	val ledger_balance : Double?,
	val currency : String?,
	val name : String?,
	val nuban : Double?,
	val ref : String?,
	val status : String?,
	val account : String?,
	val connected : Boolean?
): Serializable