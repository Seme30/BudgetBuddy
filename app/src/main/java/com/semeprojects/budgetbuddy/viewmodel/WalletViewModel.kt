package com.semeprojects.budgetbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.model.WalletType
import com.semeprojects.budgetbuddy.data.local.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WalletUiSuccessState(
    val wallets: List<Wallet>
)
@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletRepository: WalletRepository
): ViewModel() {

    sealed interface WalletUiState {
        object Initial : WalletUiState
        object Loading : WalletUiState
        data class Success(val walletUiSuccessState: WalletUiSuccessState) : WalletUiState
        data class Error(val errorMessage: String) : WalletUiState
    }

    private val _uiState = MutableStateFlow<WalletUiState>(WalletUiState.Initial)
    val uiState: StateFlow<WalletUiState> = _uiState.asStateFlow()

    init {

        viewModelScope.launch {
            _uiState.value = WalletUiState.Loading
            try {
                val wallets = walletRepository.getWalletsForProfile(1)
                _uiState.value = WalletUiState.Success(WalletUiSuccessState(wallets))
            } catch (e: Exception) {
                _uiState.value = WalletUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addWallet(name: String, balance: Float, type: WalletType) {
        viewModelScope.launch {
            _uiState.value = WalletUiState.Loading
            try {
                val newWallet = Wallet(
                    name = name,
                    balance = balance,
                    profileId = 1,
                    type = type
                )
                val updatedWallets = walletRepository.insertAndGetWallets(newWallet)

                _uiState.value = WalletUiState.Success(WalletUiSuccessState(updatedWallets))
            } catch (e: Exception) {
                _uiState.value = WalletUiState.Error("Error adding wallet: ${e.message}")
            }
        }
    }

    fun editWallet(wallet: Wallet) {
        viewModelScope.launch {
            _uiState.value = WalletUiState.Loading
            try {
                val updatedWallets = walletRepository.updateAndGetWallet(wallet)
                _uiState.value = WalletUiState.Success(WalletUiSuccessState(updatedWallets))
            } catch (e: Exception) {
                _uiState.value = WalletUiState.Error("Error editing wallet: ${e.message}")
            }
        }
    }

    fun deleteWallet(wallet: Wallet) {
        viewModelScope.launch {
            _uiState.value = WalletUiState.Loading
            try {
                val updatedWallets = walletRepository.deleteAndGetWallet(wallet)
                _uiState.value = WalletUiState.Success(WalletUiSuccessState(updatedWallets))
            } catch (e: Exception) {
                _uiState.value = WalletUiState.Error("Error deleting wallet: ${e.message}")
            }
        }
    }

}