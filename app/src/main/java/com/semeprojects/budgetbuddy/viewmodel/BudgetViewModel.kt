package com.semeprojects.budgetbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semeprojects.budgetbuddy.data.local.model.Budget
import com.semeprojects.budgetbuddy.data.local.model.BudgetWithCategoryAndWallet
import com.semeprojects.budgetbuddy.data.local.model.Category
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.repository.BudgetRepository
import com.semeprojects.budgetbuddy.data.local.repository.CategoryRepository
import com.semeprojects.budgetbuddy.data.local.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BudgetUiSuccessState(
    val budgets: MutableStateFlow<List<BudgetWithCategoryAndWallet>> = MutableStateFlow(emptyList()),
    val wallets: MutableStateFlow<List<Wallet>> = MutableStateFlow(emptyList()),
    val categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
)



@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository,
    private val walletRepository: WalletRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    sealed interface BudgetUiState {
        object Initial : BudgetUiState
        object Loading : BudgetUiState
        data class Success(val budgetUiSuccessState: BudgetUiSuccessState) : BudgetUiState
        data class Error(val errorMessage: String) : BudgetUiState
    }

    private val _uiState = MutableStateFlow<BudgetUiState>(BudgetUiState.Initial)
    val uiState: StateFlow<BudgetUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = BudgetUiState.Loading
            try {
                val fetchedBudgets = budgetRepository.getAllBudgetsWithCategoryAndWallet()
                val fetchedWallets = walletRepository.getWalletsForProfile(1)
                val fetchedCategories = categoryRepository.getAllCategories().filter {
                    it.type.lowercase() != "Income".lowercase()
                }

                _uiState.value = BudgetUiState.Success(
                    BudgetUiSuccessState(
                        budgets = MutableStateFlow(fetchedBudgets),
                        wallets = MutableStateFlow(fetchedWallets),
                        categories = MutableStateFlow(fetchedCategories)
                    )
                )
            } catch (e: Exception) {
                _uiState.value = BudgetUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addBudget(amount: Float, categoryId: Int, walletId: Int) {
        viewModelScope.launch {
            _uiState.value = BudgetUiState.Loading
            try {
                val updatedBudgets = budgetRepository.insertAndGetBudgets(
                    Budget(
                        amount = amount,
                        categoryId = categoryId,
                        walletId = walletId
                    )
                )
                println(updatedBudgets)
                val fetchedWallets = walletRepository.getWalletsForProfile(1)
                val fetchedCategories = categoryRepository.getAllCategories().filter {
                    it.type.lowercase() != "Income".lowercase()
                }
                _uiState.value = BudgetUiState.Success(
                    BudgetUiSuccessState(
                        budgets = MutableStateFlow(updatedBudgets),
                        wallets = MutableStateFlow(fetchedWallets),
                        categories = MutableStateFlow(fetchedCategories)
                    )
                )


            } catch (e: Exception) {
                _uiState.value = BudgetUiState.Error("Error adding budget: ${e.message}")
            }
        }
    }

    fun editBudget(budgetId: Int, amount: Float, categoryId: Int, walletId: Int) {
        viewModelScope.launch {
            _uiState.value = BudgetUiState.Loading
            try {
                val updatedBudgets = budgetRepository.editAndGetBudgets(
                    Budget(budgetId, amount, categoryId, walletId)
                )
                val fetchedWallets = walletRepository.getWalletsForProfile(1)
                val fetchedCategories = categoryRepository.getAllCategories().filter {
                    it.type.lowercase() != "Income".lowercase()
                }
                _uiState.value = BudgetUiState.Success(
                    BudgetUiSuccessState(
                        budgets = MutableStateFlow(updatedBudgets),
                        wallets = MutableStateFlow(fetchedWallets),
                        categories = MutableStateFlow(fetchedCategories)
                    )
                )

            } catch (e: Exception) {
                _uiState.value = BudgetUiState.Error("Error editing budget: ${e.message}")
            }
        }
    }

    fun deleteBudget(budget: BudgetWithCategoryAndWallet) {
        viewModelScope.launch {
            _uiState.value = BudgetUiState.Loading
            try {
                budgetRepository.deleteBudget(budget.budgetWithCategory.budget)

                val fetchedBudgets = budgetRepository.getAllBudgetsWithCategoryAndWallet()
                val fetchedWallets = walletRepository.getWalletsForProfile(1)
                val fetchedCategories = categoryRepository.getAllCategories().filter {
                    it.type.lowercase() != "Income".lowercase()
                }

                _uiState.value = BudgetUiState.Success(
                    BudgetUiSuccessState(
                        budgets = MutableStateFlow(fetchedBudgets),
                        wallets = MutableStateFlow(fetchedWallets),
                        categories = MutableStateFlow(fetchedCategories)
                    )
                )

            } catch (e: Exception) {
                _uiState.value = BudgetUiState.Error("Error deleting budget: ${e.message}")
            }
        }
    }

}