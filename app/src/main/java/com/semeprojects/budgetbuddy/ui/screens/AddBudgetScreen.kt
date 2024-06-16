package com.semeprojects.budgetbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.semeprojects.budgetbuddy.ui.components.BButton
import com.semeprojects.budgetbuddy.ui.components.BDropDown
import com.semeprojects.budgetbuddy.ui.components.BTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBudgetScreen(navController: NavHostController) {
    val budgetName = remember { mutableStateOf("") }
    val budgetAmount = remember { mutableStateOf("") }
    val selectedWallet = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("") }
    val wallets = listOf("BOA", "CBE", "Telebirr", "MPESA",)
    val categories = listOf("Groceries", "Dining", "Transport", "Savings")

    Scaffold(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = "Transaction History", textAlign = TextAlign.Center)
                },
                modifier = Modifier.fillMaxWidth().shadow(elevation = 4.dp)
            )
        },){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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


            BDropDown(
                types = wallets,
                inputState = selectedWallet,
                placeholder = "select a bank/wallet"
            )
            Spacer(modifier = Modifier.height(10.dp))


            BDropDown(
                types = categories,
                inputState = selectedCategory,
                placeholder = "select a bank/wallet"
            )

            Spacer(modifier = Modifier.height(10.dp))

            BButton(text = "Save Budget") {
                // Handle saving the budget (e.g., add to database, update state)
                // You can use the collected values: budgetName, budgetAmount, selectedWallet, selectedCategory
                navController.popBackStack()
            }
        }
    }
}