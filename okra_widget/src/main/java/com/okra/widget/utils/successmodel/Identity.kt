package com.okra.widget.utils.successmodel

import java.io.Serializable


data class Identity (

		val bvn : String?,
		val firstname : String?,
		val middlename : String?,
		val lastname : String?,
		val email : List<String>?,
		val name : String?,
		val fullname : String?,
		val dob : String?,
		val gender : String?,
		val marital_status : String?,
		val phone : List<String>?,
		val address : List<String>?,
		val aliases : List<String>?,
		val details : String?,
		val photo_id : List<Photo_id>?,
		val customer : String?,
		val env : String?,
		val owner : List<String>?
): Serializable