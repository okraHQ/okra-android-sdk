package com.okra.widget.models.payment

data class CustomerAccount(
    val _id: String?,
    val balance: String?,
    val bank: String?,
    val name: String?,
    val nuban: String?,
    var hashedNuban: String?,
    val hashedNubanFallback: String?
)