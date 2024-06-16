package com.semeprojects.budgetbuddy.di

import android.app.Application
import androidx.room.Room
import com.semeprojects.budgetbuddy.data.local.AppDatabase
import com.semeprojects.budgetbuddy.data.local.AppDatabaseCallback
import com.semeprojects.budgetbuddy.data.local.dao.BudgetDao
import com.semeprojects.budgetbuddy.data.local.dao.CategoryDao
import com.semeprojects.budgetbuddy.data.local.dao.ProfileDao
import com.semeprojects.budgetbuddy.data.local.dao.TransactionDao
import com.semeprojects.budgetbuddy.data.local.dao.WalletDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = AppDatabase::class.java,
            name = "budget_buddy_db"
        ).fallbackToDestructiveMigration()
            .addCallback(AppDatabaseCallback())
            .build()
    }

    @Provides
    @Singleton
    fun provideBudgetDao(appDatabase: AppDatabase): BudgetDao {
        return appDatabase.budgetDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideProfileDao(appDatabase: AppDatabase): ProfileDao {
        return appDatabase.profileDao()
    }

    @Provides
    @Singleton
    fun providesTransactionDao(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    @Singleton
    fun providesWalletDao(appDatabase: AppDatabase): WalletDao {
        return appDatabase.walletDao()
    }
}