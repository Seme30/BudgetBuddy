package com.semeprojects.budgetbuddy.data.local.model
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class BudgetItem(
    val id: Int,
    val amount: Float,
    val category: String,
    val wallet: String,
    val type: String
)

@Serializable
data class WalletItem(
    val id: Int,
    val name: String,
    val balance: Float,
    val type: String
)

@Serializable
data class TransactionItem(
    val id: Int,
    val amount: Float,
    val type: String,
    val category: String,
    val date: String
)


@Serializable
data class FinancialData(
    val transactions: List<TransactionItem>,
    val wallets: List<WalletItem>,
    val budgets: List<BudgetItem>
)
