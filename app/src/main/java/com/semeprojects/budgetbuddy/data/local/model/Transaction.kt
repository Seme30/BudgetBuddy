package com.semeprojects.budgetbuddy.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.Date

@Serializable
@Entity(tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Wallet::class,
            parentColumns = ["walletId"],
            childColumns = ["walletId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
    )
data class Transaction(
    @PrimaryKey(autoGenerate = true) val transactionId: Int,
    @Contextual val date: Date,
    val amount: Float,
    val categoryId: Int,
    val walletId: Int,
    val type: String

)