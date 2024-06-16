package com.semeprojects.budgetbuddy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semeprojects.budgetbuddy.data.local.dao.BudgetDao
import com.semeprojects.budgetbuddy.data.local.dao.CategoryDao
import com.semeprojects.budgetbuddy.data.local.dao.ProfileDao
import com.semeprojects.budgetbuddy.data.local.dao.TransactionDao
import com.semeprojects.budgetbuddy.data.local.dao.WalletDao
import com.semeprojects.budgetbuddy.data.local.model.Profile
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.model.Transaction
import com.semeprojects.budgetbuddy.data.local.model.Category
import com.semeprojects.budgetbuddy.data.local.model.Budget


@Database(entities = [Profile::class, Wallet::class, Transaction::class, Category::class, Budget::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun walletDao(): WalletDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun budgetDao(): BudgetDao
}
