package com.okra.widget.utils.successmodel

import androidx.annotation.Keep
import java.io.Serializable

@Keep
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