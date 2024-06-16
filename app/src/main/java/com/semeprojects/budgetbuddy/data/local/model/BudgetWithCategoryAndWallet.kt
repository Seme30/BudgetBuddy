package com.semeprojects.budgetbuddy.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class BudgetWithCategoryAndWallet(
    @Embedded val budgetWithCategory: BudgetWithCategory,
    @Relation(
        entity = Wallet::class,
        parentColumn = "walletId",
        entityColumn = "walletId"
    )
    val wallet: Wallet
)