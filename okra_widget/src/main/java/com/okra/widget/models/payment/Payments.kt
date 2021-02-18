package com.okra.widget.models.payment

data class Payments(
    val corp: Corp?,
    val ind: Ind?,
    val mobile: Mobile?,
    val ussd: Ussd?
)