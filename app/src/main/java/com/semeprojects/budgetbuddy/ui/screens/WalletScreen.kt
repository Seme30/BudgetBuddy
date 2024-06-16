package com.semeprojects.budgetbuddy.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.data.local.model.WalletItem
import com.semeprojects.budgetbuddy.data.local.model.WalletType
import com.semeprojects.budgetbuddy.ui.components.BButton
import com.semeprojects.budgetbuddy.ui.components.BDropDown
import com.semeprojects.budgetbuddy.ui.components.BTextField
import com.semeprojects.budgetbuddy.viewmodel.WalletViewModel


@Composable
fun WalletScreen(
    walletViewModel: WalletViewModel = hiltViewModel<WalletViewModel>()
){

    val uiState by walletViewModel.uiState.collectAsState()

    var showAddWalletDialog by remember { mutableStateOf(false) }

    AddWalletDialog(
        showDialog = showAddWalletDialog,
        onDismiss = { showAddWalletDialog = false },
        onSave = { newWallet ->
            walletViewModel.addWallet(
                name = newWallet.name,
                balance = newWallet.balance,
                type = newWallet.type
            )
        }
    )

    when(uiState){
        is WalletViewModel.WalletUiState.Error -> {
            Text((uiState as WalletViewModel.WalletUiState.Error).errorMessage)
        }
        WalletViewModel.WalletUiState.Initial -> {
            println("Initial")
        }
        WalletViewModel.WalletUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is WalletViewModel.WalletUiState.Success -> {

            val wallets = (uiState as WalletViewModel.WalletUiState.Success).walletUiSuccessState.wallets

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
                WalletList(
                    wallets = wallets,
                    onEditWallet = { wallet -> walletViewModel.editWallet(wallet) },
                    onDeleteWallet = { wallet -> walletViewModel.deleteWallet(wallet) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                BButton(
                    text = "Add New Wallet"
                ){ showAddWalletDialog = true }
            }
        }
    }
}

@Composable
fun WalletList(
    wallets: List<Wallet>,
    onEditWallet: (Wallet) -> Unit,
    onDeleteWallet: (Wallet) -> Unit
) {
    LazyColumn {
        items(wallets.size) { index ->
            if(wallets.isEmpty()){
                Text("No wallets found")
            }else{
                WalletItemCard(wallets[index], onEditWallet, onDeleteWallet)
            }
        }
    }
}

@Composable
fun WalletItemCard(
    wallet: Wallet,
    onEditWallet: (Wallet) -> Unit,
    onDeleteWallet: (Wallet) -> Unit
) {

    var showEditWalletDialog by remember { mutableStateOf(false) }
    EditWalletDialog(
        wallet = wallet,
        showDialog = showEditWalletDialog,
        onDismiss = { showEditWalletDialog = false },
        onSave = { updatedWallet ->
            onEditWallet(updatedWallet)
        }
    )
    val context = LocalContext.current

    var showDeleteDialog by remember { mutableStateOf(false) }

    DeleteConfirmationDialog(
        showDialog = showDeleteDialog,
        onDismiss = { showDeleteDialog = false },
        onConfirm = {
            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
            onDeleteWallet(wallet)
            showDeleteDialog = false
        }
    )

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

            Icon(
                imageVector = if (wallet.type.name.lowercase() == "Bank".lowercase()) Icons.Filled.AccountBalance else Icons.Filled.Wallet,
                contentDescription = wallet.type.name,
                tint = if (wallet.type.name.lowercase() == "Bank".lowercase()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = wallet.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "${String.format("%.2f", wallet.balance)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                IconButton(
                    onClick = {
                        showEditWalletDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit",
                    )
                }

                IconButton(
                    onClick = {
                        showDeleteDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Confirm Deletion") },
            text = { Text("Do you really want to delete this item?") },
            confirmButton = {
                BButton(
                    "Delete"
                ) {
                    onConfirm()
                    onDismiss()
                }
            },
            dismissButton = {
                BButton(
                    "Cancel"
                ) {
                    onDismiss()
                }
            }
        )
    }
}

@Composable
fun AddWalletDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (Wallet) -> Unit
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
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val walletName = remember { mutableStateOf("") }
                    val walletBalance = remember { mutableStateOf("") }
                    val selectedType = remember { mutableStateOf("Select Bank/Wallet") }
                    val types = listOf("Bank", "Wallet")

                    Text(
                        text = "Add New Wallet",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )

                    BTextField(
                        inputState = walletName,
                        placeholder = {
                            Text("name", style = MaterialTheme.typography.bodyMedium)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    BTextField(
                        inputState = walletBalance,
                        placeholder = {
                            Text("initial balance", style = MaterialTheme.typography.bodyMedium)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    BDropDown(
                        types = types,
                        inputState = selectedType,
                        placeholder = "select a category"
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
                                Wallet(
                                    name = walletName.value,
                                    balance = walletBalance.value.toFloatOrNull() ?: 0f,
                                    profileId = 1,
                                    type = when (selectedType.value) {
                                        "Bank" -> WalletType.BANK
                                        "Wallet" -> WalletType.WALLET
                                        else -> {
                                            WalletType.BANK
                                        }
                                    }
                            )
                                            )
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun EditWalletDialog(
    wallet: Wallet,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (Wallet) -> Unit
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
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val walletName = remember { mutableStateOf(wallet.name) }
                    val walletBalance = remember { mutableStateOf(wallet.balance.toString()) }
                    val selectedType = remember { mutableStateOf(wallet.type.name) }
                    val types = listOf("Bank", "Wallet")

                    Text(
                        text = "Edit Wallet",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )

                    BTextField(
                        inputState = walletName,
                        placeholder = {
                            Text("budget name", style = MaterialTheme.typography.bodyMedium)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    BTextField(
                        inputState = walletBalance,
                        placeholder = {
                            Text("initial balance", style = MaterialTheme.typography.bodyMedium)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    BDropDown(
                        types = types,
                        inputState = selectedType,
                        placeholder = "select a category"
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
                            val updatedWallet = wallet.copy(
                                name = walletName.value,
                                balance = walletBalance.value.toFloatOrNull() ?: wallet.balance,
                                type = when (selectedType.value) {
                                    "Bank" -> WalletType.BANK
                                    "Wallet" -> WalletType.WALLET
                                    else -> {
                                        wallet.type
                                    }
                                }
                            )
                            onSave(updatedWallet)
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}