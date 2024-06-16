package com.semeprojects.budgetbuddy.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true) val profileId: Int,
    val name: String,
    val monthlyBudget: Float,
    val email: String,
    val age: Int,
)