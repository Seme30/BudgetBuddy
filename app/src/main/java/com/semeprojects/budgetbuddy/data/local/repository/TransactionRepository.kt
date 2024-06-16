package com.semeprojects.budgetbuddy.data.local.repository

import com.semeprojects.budgetbuddy.data.local.dao.TransactionDao
import com.semeprojects.budgetbuddy.data.local.model.Transaction
import com.semeprojects.budgetbuddy.data.local.model.TransactionWithCategory
import com.semeprojects.budgetbuddy.domain.repository.Response
import com.semeprojects.budgetbuddy.viewmodel.TransactionWithCategoryAndWallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class TransactionRepository(private val transactionDao: TransactionDao) {

//    suspend fun getAllTransactionsWithCategoryAndWallet(): List<TransactionWithCategoryAndWallet> {
//        return transactionDao.getAllTransactionsWithCategoryAndWallet()
//    }

    suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun getTransactionsForWallet(walletId: Int): List<Transaction> {
        return transactionDao.getTransactionsForWallet(walletId)
    }

    suspend fun getTransactionsWithCategories(): List<TransactionWithCategory> {
        return transactionDao.getAllTransactionsWithCategories()
    }

    suspend fun deleteTransaction(transaction: Transaction){
        transactionDao.deleteTransaction(transaction)
    }
}
