package com.semeprojects.budgetbuddy.data.local.repository

import com.semeprojects.budgetbuddy.data.local.dao.CategoryDao
import com.semeprojects.budgetbuddy.data.local.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepository(private val categoryDao: CategoryDao) {
    suspend fun insertCategory(category: Category){
        categoryDao.insertCategory(category)
    }

    suspend fun getAllCategories(): List<Category> {
        return categoryDao.getAllCategories()
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }
}