package com.okra.widget.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAction(
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "bank") val bank: String?,
        @ColumnInfo(name = "status") val status: String?,
        @ColumnInfo(name = "action") val action: String?
)