package com.semeprojects.budgetbuddy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.semeprojects.budgetbuddy.data.local.model.Budget
import com.semeprojects.budgetbuddy.data.local.model.BudgetWithCategoryAndWallet


@Dao
interface BudgetDao {

    @Transaction
    @Query("SELECT * FROM budgets WHERE walletId = :walletId")
    suspend fun getBudgetsForWallet(walletId: Int): List<Budget>

    @Transaction
    @Query("SELECT * FROM budgets WHERE categoryId = :categoryId")
    suspend fun getBudgetsForCategory(categoryId: Int): List<BudgetWithCategoryAndWallet>

    @Transaction
    @Query("SELECT * FROM budgets")
    suspend fun getAllBudgetsWithCategoryAndWallet(): List<BudgetWithCategoryAndWallet>

    @Insert
    suspend fun insertBudget(budget: Budget)

    @Delete
    suspend fun deleteBudget(budget: Budget)

    @Update
    suspend fun editBudget(budget: Budget)
}