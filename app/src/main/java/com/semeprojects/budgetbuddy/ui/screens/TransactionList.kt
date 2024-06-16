package com.semeprojects.budgetbuddy.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.semeprojects.budgetbuddy.R

data class TransactionItem(
    val transactionId: Int,
    val date: String,
    val amount: Float,
    val category: String?,
    val wallet: String,
    val type: String
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionList(
    navController: NavHostController
) {

    val transactions = listOf<TransactionItem>(
        TransactionItem(1, "2023-12-18", 25.50f, "Coffee", "Cash", "expense"),
        TransactionItem(2, "2023-12-17", 120.00f, "Groceries", "Debit Card", "expense"),
        TransactionItem(3, "2023-12-16", 1500.00f, "Salary", "Bank Account", "income"),
        TransactionItem(4, "2023-12-15", 50.00f, "Fuel", "Credit Card", "expense"),
        TransactionItem(5, "2023-12-14", 100.00f, "Dinner", "Cash", "expense"),
        TransactionItem(6, "2023-12-13", 20.00f, "Movie Tickets", "Debit Card", "expense"),
        TransactionItem(7, "2023-12-12", 500.00f, "Freelance Payment", "Bank Account", "income"),
        TransactionItem(8, "2023-12-11", 35.75f, "Clothes", "Credit Card", "expense"),
        TransactionItem(9, "2023-12-10", 80.00f, null, "Savings Account", "saving"), // Saving
        TransactionItem(10, "2023-12-09", 15.00f, "Gift", "Cash", "expense")
    )

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
        },){
        paddingValues ->
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(paddingValues)
    ) {
        items(transactions.size) { index ->
            TransactionCard(transactions[index])
        }
    }
    }
}

@Composable
fun TransactionCard(transaction: TransactionItem) {
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
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = transaction.category ?: "Saving", // Display category or "Saving"
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (transaction.type == "expense") "-$" else "+$", // Indicate expense or income
                    style = MaterialTheme.typography.titleMedium,
                    color = if (transaction.type == "expense") Color.Red else Color.Green
                )
                Text(
                    text = String.format("%.2f", transaction.amount), // Format amount with 2 decimal places
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
                // Actions (Edit and Delete)
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}