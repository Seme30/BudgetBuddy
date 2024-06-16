package com.semeprojects.budgetbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semeprojects.budgetbuddy.data.local.model.Category
import com.semeprojects.budgetbuddy.data.local.model.Transaction
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class TransactionWithCategoryAndWallet(
    val transaction: Transaction,
    val category: Category?,
    val wallet: Wallet
)

data class TransactionUiSuccessState(
    val transactions: List<TransactionWithCategoryAndWallet>
)

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {


    sealed interface TransactionUiState {
        object Initial : TransactionUiState
        object Loading : TransactionUiState
        data class Success(val transactionUiSuccessState: TransactionUiSuccessState) : TransactionUiState
        data class Error(val errorMessage: String) : TransactionUiState
    }

    private val _uiState = MutableStateFlow<TransactionUiState>(TransactionUiState.Initial)
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()


    init {
//        viewModelScope.launch {
//            _uiState.value = TransactionUiState.Loading
//            try {
//                val transactions = transactionRepository.getAllTransactionsWithCategoryAndWallet()
//                _uiState.value = TransactionUiState.Success(TransactionUiSuccessState(transactions))
//            } catch (e: Exception) {
//                _uiState.value = TransactionUiState.Error(e.message ?: "Unknown error")
//            }
//        }
    }

}