package com.semeprojects.budgetbuddy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semeprojects.budgetbuddy.data.local.model.TransactionWithCategory

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE walletId = :walletId")
    suspend fun getTransactionsForWallet(walletId: Int): List<com.semeprojects.budgetbuddy.data.local.model.Transaction>

    @androidx.room.Transaction
    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactionsWithCategories(): List<TransactionWithCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: com.semeprojects.budgetbuddy.data.local.model.Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: com.semeprojects.budgetbuddy.data.local.model.Transaction)
}