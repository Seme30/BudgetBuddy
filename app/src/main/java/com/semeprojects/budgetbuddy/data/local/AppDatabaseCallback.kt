package com.semeprojects.budgetbuddy.data.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.time.Duration
import java.time.Instant
import java.util.Date

class AppDatabaseCallback : RoomDatabase.Callback() {

    @SuppressLint("NewApi")
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val profile1 = ContentValues().apply {
            put("name", "John Doe")
            put("monthlyBudget", 3000f)
            put("email", "john.doe@example.com")
            put("age", 30)
        }

        db.insert("profiles", SQLiteDatabase.CONFLICT_REPLACE, profile1)

        // Sample Wallets
        val wallet1 = ContentValues().apply {
            put("name", "CBE")
            put("balance", 1500f)
            put("type", "BANK")
            put("profileId", 1) // Associated with profile1
        }
        val wallet2 = ContentValues().apply {
            put("name", "BOA")
            put("balance", 5000f)
            put("type", "BANK")
            put("profileId", 1)
        }
        val wallet3 = ContentValues().apply {
            put("name", "telebirr")
            put("balance", 5000f)
            put("type", "WALLET")
            put("profileId", 1)
        }

        val wallet4 = ContentValues().apply {
            put("name", "MPESSA")
            put("balance", 5000f)
            put("type", "WALLET")
            put("profileId", 1)
        }
        db.insert("wallets", SQLiteDatabase.CONFLICT_REPLACE, wallet1)
        db.insert("wallets", SQLiteDatabase.CONFLICT_REPLACE, wallet2)
        db.insert("wallets", SQLiteDatabase.CONFLICT_REPLACE, wallet3)
        db.insert("wallets", SQLiteDatabase.CONFLICT_REPLACE, wallet4)


        val expenseCategoryList = listOf(
            "Groceries",
            "Transport",
            "Rent",
            "Utilities",
            "Dining",
            "Shopping",
            "Entertainment",
            "Clothing",
            "Healthcare",
            "Education"
        )
        val incomeCategoryList = listOf(
            "Salary",
            "Freelance Income",
            "Investments",
            "Side Hustle",
            "Gifts"
        )

        // Insert expense categories
        expenseCategoryList.forEachIndexed { index, categoryName ->
            val values = ContentValues().apply {
                put("name", categoryName)
                put("type", "Expense")
            }
            db.insert("categories", SQLiteDatabase.CONFLICT_REPLACE, values)
        }

        // Insert income categories
        incomeCategoryList.forEachIndexed { index, categoryName ->
            val values = ContentValues().apply {
                put("name", categoryName)
                put("type", "Income")
            }
            db.insert("categories", SQLiteDatabase.CONFLICT_REPLACE, values)
        }

        val savingCategory = ContentValues().apply {
            put("name", "Savings")
            put("type", "Savings")
        }

        db.insert("categories", SQLiteDatabase.CONFLICT_REPLACE, savingCategory)

        // Sample Transactions (Adjust category and wallet IDs accordingly)
        val transaction1 = ContentValues().apply {
            put("date", Date.from(Instant.now().minus(Duration.ofDays(5))).time)
            put("amount", 150f)
            put("categoryId", 1) // Groceries
            put("walletId", 1)
            put("type", "Expense")
        }
        val transaction2 = ContentValues().apply {
            put("date", Date.from(Instant.now().minus(Duration.ofDays(3))).time)
            put("amount", 2500f)
            put("categoryId", expenseCategoryList.size + 1) // Salary (offset by expense categories)
            put("walletId", 1)
            put("type", "Income")
        }
        db.insert("transactions", SQLiteDatabase.CONFLICT_REPLACE, transaction1)
        db.insert("transactions", SQLiteDatabase.CONFLICT_REPLACE, transaction2)

        val budget1 = ContentValues().apply {
            put("amount", 300f)
            put("categoryId", 1) // Groceries
            put("walletId", 1)
        }
        val budget2 = ContentValues().apply {
            put("amount", 500f)
            put("categoryId", 3) // Rent
            put("walletId", 1)
        }
        db.insert("budgets", SQLiteDatabase.CONFLICT_REPLACE, budget1)
        db.insert("budgets", SQLiteDatabase.CONFLICT_REPLACE, budget2)
    }
}
