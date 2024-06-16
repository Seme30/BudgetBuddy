package com.semeprojects.budgetbuddy.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class TransactionWithCategory(
    @Embedded val transaction: Transaction,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val category: Category
)