package com.semeprojects.budgetbuddy.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class BudgetWithWallet(
    @Embedded val budget: Budget,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "walletId"
    )
    val wallet: Wallet
)