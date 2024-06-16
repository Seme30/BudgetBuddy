package com.semeprojects.budgetbuddy.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "wallets",
    foreignKeys = [
        ForeignKey(
            entity = Profile::class,
            parentColumns = ["profileId"],
            childColumns = ["profileId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Wallet(
    @PrimaryKey(autoGenerate = true) var walletId: Int? = null,
    val name: String,
    val balance: Float,
    val profileId: Int,
    val type: WalletType
)

enum class WalletType {
    BANK,
    WALLET
}