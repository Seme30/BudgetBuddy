package com.semeprojects.budgetbuddy.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.semeprojects.budgetbuddy.data.local.model.Budget
import com.semeprojects.budgetbuddy.data.local.model.BudgetWithCategory
import com.semeprojects.budgetbuddy.data.local.model.BudgetWithCategoryAndWallet
import com.semeprojects.budgetbuddy.data.local.model.Category
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.model.WalletType
import com.semeprojects.budgetbuddy.ui.components.BButton
import com.semeprojects.budgetbuddy.ui.components.BTextField
import com.semeprojects.budgetbuddy.ui.components.CustomRadioButton
import com.semeprojects.budgetbuddy.viewmodel.BudgetViewModel
import com.semeprojects.budgetbuddy.ui.components.BCustomDropDown


@Composable
fun BudgetScreen(
    navController: NavHostController,
    budgetViewModel: BudgetViewModel = hiltViewModel<BudgetViewModel>()
){


    var showDialog by remember { mutableStateOf(false) }
    val uiState by budgetViewModel.uiState.collectAsState()


    when(uiState){
        is BudgetViewModel.BudgetUiState.Initial -> {}
        is BudgetViewModel.BudgetUiState.Success -> {
            val successState = (uiState as BudgetViewModel.BudgetUiState.Success).budgetUiSuccessState
            val budgetsWithAllData = successState.budgets.collectAsState().value
            val wallets by successState.wallets.collectAsState()
            val categories by successState.categories.collectAsState()

            var showEditDialog by remember { mutableStateOf(false) }
            var budgetToEdit by remember { mutableStateOf<BudgetWithCategoryAndWallet?>(null) }


            BudgetContent(budgetsWithAllData, budgetToEdit = {
                budgetToEdit = it
            },
                showEditDialog = {
                    showEditDialog = it
                },
                showDialog = {
                    showDialog = it
                },
                onDeleteBudget = {
                    budgetViewModel.deleteBudget(it)
                }
                )

            EditBudgetDialog(
                budget = budgetToEdit ?: BudgetWithCategoryAndWallet(
                    budgetWithCategory = BudgetWithCategory(
                        budget = Budget(0, 0f, 0, 0),
                        category = Category(0, "", "")
                    ),
                    wallet = Wallet(0, "", 0f, 0, WalletType.WALLET)
                ),
                showDialog = showEditDialog,
                onDismiss = { showEditDialog = false },
                onSave = { budgetId, name, amount, walletId, categoryId ->
                    budgetViewModel.editBudget(budgetId, amount, categoryId, walletId)
                },
                wallets = wallets,
                categories = categories
            )

            AddBudgetDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                onSave = { budgetName, budgetAmount, walletId, categoryId ->
                    budgetViewModel.addBudget(budgetAmount, walletId = walletId, categoryId = categoryId)
                },
                wallets = wallets,
                categories = categories           )
        }

        is BudgetViewModel.BudgetUiState.Error -> {
            Text((uiState as BudgetViewModel.BudgetUiState.Error).errorMessage)
        }
        is BudgetViewModel.BudgetUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }


}

@Composable
fun BudgetContent(
    budgets: List<BudgetWithCategoryAndWallet>,
    budgetToEdit: (BudgetWithCategoryAndWallet?) -> Unit,
    showDialog: (Boolean)-> Unit,
    showEditDialog: (Boolean) -> Unit,
    onDeleteBudget: (BudgetWithCategoryAndWallet) -> Unit
) {

    val options = listOf("Expense", "Savings")
    var selectedOption by remember { mutableStateOf(options[0]) }
    val totalBudgetAmount = budgets.sumOf {
        it.budgetWithCategory.budget.amount.toDouble()
    }.toFloat()

    val categories = budgets.map {
        it.budgetWithCategory.category
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){


        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            )
        ) {
            Text(
                "Monthly Budget",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = String.format("%.2f", totalBudgetAmount),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "in ${categories.size} categories",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            options.forEach { option ->
                CustomRadioButton(option, selectedOption == option) {
                    selectedOption = it
                }
            }
        }

        val filteredBudgets = budgets.filter {
            it.budgetWithCategory.category.type.lowercase() == selectedOption.lowercase()
        }
        println(budgets.map { it.budgetWithCategory.category })
        println(filteredBudgets)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(horizontal = 16.dp)
        ) {
            items(filteredBudgets.size) { index ->
                BudgetCard(
                    filteredBudgets[index],
                    budgetToEdit,
                    showEditDialog,
                    onDeleteBudget
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        BButton(text = "Add New Budget"){
            println("Add Budget clicked")
            showDialog(true)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
fun BudgetCard(
    budget: BudgetWithCategoryAndWallet,
    budgetToEdit: (BudgetWithCategoryAndWallet?) -> Unit,
    showEditDialog: (Boolean) -> Unit,
    onDeleteBudget: (BudgetWithCategoryAndWallet) -> Unit
) {

    var showConfirmDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(10.dp),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.secondary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = budget.budgetWithCategory.category.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = budget.wallet.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = String.format("%.2f", budget.budgetWithCategory.budget.amount),
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(
                    onClick = {
                        budgetToEdit(budget)
                        showEditDialog(true)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                ConfirmDeleteDialog(
                    showDialog = showConfirmDialog,
                    onConfirm = {
                        onDeleteBudget(budget)
                        showConfirmDialog = false
                    },
                    onDismiss = { showConfirmDialog = false },
                    budgetToDelete = budget
                )

                IconButton(
                    onClick = {
                        showConfirmDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

        }
    }
}

@Composable
fun ConfirmDeleteDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    budgetToDelete: BudgetWithCategoryAndWallet
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete the budget for ${budgetToDelete.budgetWithCategory.category.name}?") },
            confirmButton = {
                BButton(
                    text = "Delete",
                ) {
                    onConfirm()
                }
            },
            dismissButton = {
                BButton(
                    text = "Cancel",
                ) {
                    onDismiss()
                }
            }
        )
    }
}

@Composable
fun AddBudgetDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (budgetName: String, budgetAmount: Float, walletId: Int, categoryId: Int) -> Unit,
    wallets: List<Wallet>,
    categories: List<Category> ) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val budgetName = remember { mutableStateOf("") }
                    val budgetAmount = remember { mutableStateOf("") }
                    val selectedWallet = remember { mutableStateOf<Wallet?>(null) } // Store the selected Wallet object
                    val selectedCategory = remember { mutableStateOf<Category?>(null) }

                    Text(
                        text = "Add Budget",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    BTextField(
                        inputState = budgetName,
                        placeholder = {
                            Text("budget name", style = MaterialTheme.typography.bodyMedium)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    BTextField(
                        inputState = budgetAmount,
                        placeholder = {
                            Text("amount", style = MaterialTheme.typography.bodyMedium)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

//                    BDropDown(
//                        types = wallets,
//                        inputState = selectedWallet,
//                        placeholder = "select a bank/wallet"
//                    )
                    BCustomDropDown(
                        items = wallets,
                        selectedItem = selectedWallet.value,
                        onItemSelected = { wallet -> selectedWallet.value = wallet },
                        itemText = { wallet -> Text(wallet.name) },
                        placeholder = "Select a wallet"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    BCustomDropDown(
                        items = categories,
                        selectedItem = selectedCategory.value,
                        onItemSelected = { category -> selectedCategory.value = category },
                        itemText = { category -> Text(category.name) },
                        placeholder = "Select a category"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        BButton(
                            text = "Cancel"
                        ){
                            onDismiss()
                        }

                        BButton(text = "Save") {
                            onSave(
                                budgetName.value,
                                budgetAmount.value.toFloatOrNull() ?: 0f,
                                selectedWallet.value?.walletId ?: 0,
                                selectedCategory.value?.categoryId ?: 0
                            )
                            onDismiss() // Close the dialog
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EditBudgetDialog(
    budget: BudgetWithCategoryAndWallet,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (budgetId: Int, budgetName: String, budgetAmount: Float, walletId: Int, categoryId: Int) -> Unit,
    wallets: List<Wallet>,
    categories: List<Category>
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val budgetName = remember { mutableStateOf(budget.budgetWithCategory.category.name) }
                    val budgetAmount = remember { mutableStateOf(budget.budgetWithCategory.budget.amount.toString()) }
                    val selectedWallet = remember { mutableStateOf(budget.wallet) }
                    val selectedCategory = remember { mutableStateOf(budget.budgetWithCategory.category) }

                    Text(
                        text = "Edit Budget",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    BTextField(
                        inputState = budgetName,
                        placeholder = {
                            Text("budget name", style = MaterialTheme.typography.bodyMedium)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    BTextField(
                        inputState = budgetAmount,
                        placeholder = {
                            Text("amount", style = MaterialTheme.typography.bodyMedium)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    BCustomDropDown(
                        items = wallets,
                        selectedItem = selectedWallet.value,
                        onItemSelected = { wallet -> selectedWallet.value = wallet },
                        itemText = { wallet -> Text(wallet.name) },
                        placeholder = "Select a wallet"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    BCustomDropDown(
                        items = categories,
                        selectedItem = selectedCategory.value,
                        onItemSelected = { category -> selectedCategory.value = category },
                        itemText = { category -> Text(category.name) },
                        placeholder = "Select a category"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        BButton(text = "Cancel") { onDismiss() }

                        BButton(text = "Save Changes") {
                            onSave(
                                budget.budgetWithCategory.budget.budgetId?: 0,
                                budgetName.value,
                                budgetAmount.value.toFloatOrNull() ?: 0f,
                                selectedWallet.value.walletId?:0,
                                selectedCategory.value.categoryId
                            )
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}
