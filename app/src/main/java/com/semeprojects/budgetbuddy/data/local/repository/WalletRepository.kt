package com.semeprojects.budgetbuddy.data.local.repository

import com.semeprojects.budgetbuddy.data.local.dao.WalletDao
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WalletRepository(private val walletDao: WalletDao) {


    suspend fun getTotalBalance(profileId: Int): Float? {
        return walletDao.getTotalBalanceForProfile(profileId)
    }

    suspend fun insertAndGetWallets(wallet: Wallet): List<Wallet>{
        walletDao.insertWallet(wallet)
        return walletDao.getWalletsForProfile(1)
    }

    suspend fun getWalletsForProfile(profileId: Int): List<Wallet>{
        return walletDao.getWalletsForProfile(profileId)
    }

    suspend fun deleteAndGetWallet(wallet: Wallet): List<Wallet>{
        walletDao.deleteWallet(wallet)
        return walletDao.getWalletsForProfile(1)
    }

    suspend fun updateAndGetWallet(wallet: Wallet): List<Wallet>{
        walletDao.updateWallet(wallet)
        return walletDao.getWalletsForProfile(1)
    }
}