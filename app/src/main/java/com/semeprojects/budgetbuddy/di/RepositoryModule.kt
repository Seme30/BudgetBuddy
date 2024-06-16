package com.semeprojects.budgetbuddy.di

import android.app.Application
import com.semeprojects.budgetbuddy.data.datapreferences.DataStoreRepository
import com.semeprojects.budgetbuddy.data.local.dao.BudgetDao
import com.semeprojects.budgetbuddy.data.local.dao.CategoryDao
import com.semeprojects.budgetbuddy.data.local.dao.ProfileDao
import com.semeprojects.budgetbuddy.data.local.dao.TransactionDao
import com.semeprojects.budgetbuddy.data.local.dao.WalletDao
import com.semeprojects.budgetbuddy.data.local.repository.BudgetRepository
import com.semeprojects.budgetbuddy.data.local.repository.CategoryRepository
import com.semeprojects.budgetbuddy.data.local.repository.ProfileRepository
import com.semeprojects.budgetbuddy.data.local.repository.TransactionRepository
import com.semeprojects.budgetbuddy.data.local.repository.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun providesDataStoreRepository(application: Application) : DataStoreRepository{
        return DataStoreRepository(
            application)
    }

    @Provides
    @Singleton 
    fun providesBudgetRepository(budgetDao: BudgetDao): BudgetRepository {
        return BudgetRepository(budgetDao)
    }

    @Provides
    @Singleton
    fun providesProfileRepository(profileDao: ProfileDao): ProfileRepository {
        return ProfileRepository(profileDao)
    }

    @Provides
    @Singleton
    fun providesCategoryDao(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepository(categoryDao)
    }

    @Provides
    @Singleton
    fun providesTransactionDao(transactionDao: TransactionDao): TransactionRepository {
        return TransactionRepository(transactionDao)
    }

    @Provides
    @Singleton
    fun providesWalletDao(walletDao: WalletDao): WalletRepository {
        return WalletRepository(walletDao)
    }


}