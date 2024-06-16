package com.semeprojects.budgetbuddy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.model.WalletType

@Dao
interface WalletDao {

    @Query("SELECT * FROM wallets WHERE profileId = :profileId")
    suspend fun getWalletsForProfile(profileId: Int): List<Wallet>

    @Query("SELECT SUM(balance) FROM wallets WHERE profileId = :profileId")
    suspend fun getTotalBalanceForProfile(profileId: Int): Float

    @Insert
    suspend fun insertWallet(wallet: Wallet)

    @Delete
    suspend fun deleteWallet(wallet: Wallet)

    @Query("SELECT * FROM wallets")
    suspend fun getAllWallets(): List<Wallet>

    @Query("SELECT * FROM wallets WHERE walletId = :walletId")
    suspend fun getWalletById(walletId: Int): Wallet?

    @Update
    suspend fun updateWallet(wallet: Wallet)

    @Query("SELECT * FROM wallets WHERE type = :walletType")
    suspend fun getWalletsByType(walletType: WalletType): List<Wallet>
}