package com.okra.widget.utils.successmodel

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Data (

	val formatted : List<Formatted>?
): Serializable