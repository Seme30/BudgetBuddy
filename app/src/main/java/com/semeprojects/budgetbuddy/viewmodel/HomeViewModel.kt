package com.semeprojects.budgetbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semeprojects.budgetbuddy.data.local.model.TransactionWithCategory
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.repository.BudgetRepository
import com.semeprojects.budgetbuddy.data.local.repository.TransactionRepository
import com.semeprojects.budgetbuddy.data.local.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiSuccessState(
    val totalBalance: Float,
    val wallets: List<Wallet>,
    val transactions: List<TransactionWithCategory>,
    val monthlyBudget: Float = 0f
)

sealed interface UiState {
    object Initial : UiState
    object Loading : UiState
    data class Success(val homeUiSuccessState: HomeUiSuccessState) : UiState
    data class Error(val errorMessage: String) : UiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val walletRepository: WalletRepository,
    private val budgetRepository: BudgetRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


   init {
       viewModelScope.launch {
           _uiState.value = UiState.Loading

           try {
               val totalBalance = walletRepository.getTotalBalance(1)
               val wallets = walletRepository.getWalletsForProfile(1)
               val transactions = transactionRepository.getTransactionsWithCategories()
//               val budget = budgetRepository.

               _uiState.value = UiState.Success(
                   HomeUiSuccessState(totalBalance ?: 0f, wallets, transactions)
               )

           } catch (e: Exception) {
               _uiState.value = UiState.Error(e.message ?: "Unknown error")
           }
       }
   }

    fun refreshData(){
        viewModelScope.launch {
            try {
                val totalBalance = walletRepository.getTotalBalance(1)
                val wallets = walletRepository.getWalletsForProfile(1)
                val transactions = transactionRepository.getTransactionsWithCategories()

                _uiState.value = UiState.Success(
                    HomeUiSuccessState(totalBalance ?: 0f, wallets, transactions)
                )

            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}