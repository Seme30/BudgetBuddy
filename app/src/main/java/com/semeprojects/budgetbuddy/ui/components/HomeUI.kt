package com.semeprojects.budgetbuddy.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.OtherHouses
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.semeprojects.budgetbuddy.data.local.model.Wallet
import com.semeprojects.budgetbuddy.ui.nav_utils.BudgetBuddyScreens
import com.semeprojects.budgetbuddy.viewmodel.HomeViewModel
import com.semeprojects.budgetbuddy.viewmodel.UiState

@Composable
fun CardComponent(name: String, balance: String) {
    Card(
        modifier = Modifier
//            .padding(vertical = 8.dp)
            .shadow(elevation = 4.dp)
            .width(115.dp)
//            .padding(10.dp)
        ,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )

    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Default.OtherHouses,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Bank Icon",
                modifier = Modifier.size(28.dp),
            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = balance,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun CardTransact(item: Items) {
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
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = item.icon,
                tint = when(item.name){
                    "Expenses" -> MaterialTheme.colorScheme.error
                    "Savings" -> MaterialTheme.colorScheme.primary
                    "Monthly budget" -> MaterialTheme.colorScheme.primary
                    "Incomes" -> MaterialTheme.colorScheme.tertiary
                    else -> {
                        MaterialTheme.colorScheme.primary
                    }
                },
                contentDescription = "Bank Icon",
                modifier = Modifier.size(28.dp),
            )
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = item.balance,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun TransactionComponent(item: Items) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(
                shape = RoundedCornerShape(5.dp),
                width = 0.1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(text = item.name,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.icon,
                tint = when(item.name){
                    "Salary" -> MaterialTheme.colorScheme.primary
                    "Subway" -> MaterialTheme.colorScheme.error
                    "Groceries" -> MaterialTheme.colorScheme.primary
                    "Electricity Bill" -> MaterialTheme.colorScheme.primary
                    "Internet Bill" -> MaterialTheme.colorScheme.primary
                    else -> {
                        MaterialTheme.colorScheme.primary
                    }
                },
                contentDescription = "Bank Icon",
                modifier = Modifier.size(28.dp),
            )
            Text(text = item.balance, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


data class Items(
    val icon: ImageVector,
    val name: String,
    val balance: String
)




@Composable
fun HomeUI(
    navHostController: NavHostController,
    onSelectedItemChange: (Int) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {

    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.refreshData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        when(uiState){
            is UiState.Error -> {
                Text(text = (uiState as UiState.Error).errorMessage)
            }
            UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                val homeUiSuccessState = (uiState as UiState.Success).homeUiSuccessState

                val transactionWithCategory = homeUiSuccessState.transactions

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                    )
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        if(uiState == UiState.Loading){
                            CircularProgressIndicator()
                        } else {
                            Text(
                                text = "Total balance",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = homeUiSuccessState.totalBalance.toString(), textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    val walletList = homeUiSuccessState.wallets
                    WalletRow(
                        onSelectedItemChange,
                        walletList

                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center
                ) {

                    var totalExpenses = 0f
                    var totalSavings = 0f
                    var totalIncome = 0f

                    transactionWithCategory.forEach { transaction ->
                        when (transaction.transaction.type) {
                            "Expense" -> totalExpenses += transaction.transaction.amount
                            "Savings" -> totalSavings += transaction.transaction.amount
                            "Income" -> totalIncome += transaction.transaction.amount
                        }
                    }

                    // Create gridItems
                    val gridItems = listOf(
                        Items(Icons.Default.ArrowOutward, balance = "$totalExpenses", name = "Expenses"),
                        Items(Icons.Default.ArrowDownward, balance = "$totalSavings", name = "Savings"),
                        Items(Icons.Default.Money, balance = "${homeUiSuccessState.totalBalance}", name = "Monthly budget"),
                        Items(Icons.Default.ArrowDownward, balance = "$totalIncome", name = "Incomes")
                    )

                    items(gridItems.size) { index ->
                        CardTransact(item = gridItems[index])
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clickable {
                            navHostController.navigate(BudgetBuddyScreens.TransactionList.route)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Recent transactions", style = MaterialTheme.typography.titleMedium)
                    IconButton(
                        onClick = {
                            navHostController.navigate(BudgetBuddyScreens.TransactionList.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DoubleArrow,
                            contentDescription = "DoubleArrow Icon",
                            modifier = Modifier.size(28.dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    val transactionItems = transactionWithCategory.map { transaction ->
                        val icon = if (transaction.category.type == "Expense") {
                            Icons.Default.ArrowUpward
                        } else {
                            Icons.Default.ArrowDownward
                        }
                        val name = transaction.category.name
                        val balance = if (transaction.category.type == "Income") {
                            "+${transaction.transaction.amount}"
                        } else {
                            "-${transaction.transaction.amount}"
                        }

                        Items(icon, balance, name)
                    }

                    items(transactionItems.size) { index ->
                        TransactionComponent(item = transactionItems[index])
                    }

                }
            }

            UiState.Initial -> {

            }
        }
    }
}

@Composable
fun WalletRow(onSelectedItemChange: (Int) -> Unit, wallets: List<Wallet>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            AddCardComponent(
                onSelectedItemChange
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        items (wallets.size){ index ->
            CardComponent(wallets[index].name, wallets[index].balance.toString())
            Spacer(modifier = Modifier.width(10.dp))
        }

    }
}

@Composable
fun AddCardComponent(onSelectedItemChange: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .shadow(elevation = 4.dp)
            .width(115.dp)
            .clickable {
                onSelectedItemChange(2)
            },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column (
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            IconButton(onClick = {
                onSelectedItemChange(3)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Add Icon",
                    modifier = Modifier.size(28.dp),
                )
            }
            Text(
                text = "Add Wallet",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}

