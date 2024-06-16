package com.semeprojects.budgetbuddy.data.local.repository

import com.semeprojects.budgetbuddy.data.local.dao.BudgetDao
import com.semeprojects.budgetbuddy.data.local.model.Budget
import com.semeprojects.budgetbuddy.data.local.model.BudgetWithCategoryAndWallet

class BudgetRepository(private val budgetDao: BudgetDao) {

    suspend fun insertAndGetBudgets(budget: Budget): List<BudgetWithCategoryAndWallet> {
        budgetDao.insertBudget(budget)
        return budgetDao.getAllBudgetsWithCategoryAndWallet()
    }

    suspend fun getBudgetsForWallet(walletId: Int): List<Budget> {
        return budgetDao.getBudgetsForWallet(walletId)
    }

    suspend fun getBudgetsForCategory(categoryId: Int): List<BudgetWithCategoryAndWallet> {
        return budgetDao.getBudgetsForCategory(categoryId)
    }

    suspend fun editAndGetBudgets(budget: Budget): List<BudgetWithCategoryAndWallet> {
        budgetDao.editBudget(budget)
        return budgetDao.getAllBudgetsWithCategoryAndWallet()
    }

    suspend fun getAllBudgetsWithCategoryAndWallet(): List<BudgetWithCategoryAndWallet> {
        return budgetDao.getAllBudgetsWithCategoryAndWallet()
    }
    suspend fun deleteBudget(budget: Budget) = run { budgetDao.deleteBudget(budget) }
}