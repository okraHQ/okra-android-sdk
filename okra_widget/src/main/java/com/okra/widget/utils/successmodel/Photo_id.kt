package com.okra.widget.utils.successmodel

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Photo_id (

	val _id : String?,
	val url : String?,
	val image_type : String?
): Serializable