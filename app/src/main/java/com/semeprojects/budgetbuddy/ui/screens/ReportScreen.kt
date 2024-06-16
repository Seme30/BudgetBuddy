package com.semeprojects.budgetbuddy.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jaikeerthick.composable_graphs.composables.bar.BarGraph
import java.util.Date

import com.jaikeerthick.composable_graphs.composables.bar.model.BarData
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphStyle
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphVisibility
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphVisibility
import com.jaikeerthick.composable_graphs.style.LabelPosition
import com.semeprojects.budgetbuddy.data.local.model.BudgetItem
import com.semeprojects.budgetbuddy.data.local.model.FinancialData
import com.semeprojects.budgetbuddy.data.local.model.TransactionItem
import com.semeprojects.budgetbuddy.data.local.model.WalletItem
import com.semeprojects.budgetbuddy.ui.nav_utils.BudgetBuddyScreens
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun ReportScreen(
    navController: NavHostController
) {
    // Dummy Data
    val budgetItemData = listOf(
        BudgetItem(1, 300f, "Groceries", "CBE", "expense"),
        BudgetItem(2, 150f, "Dining", "CBE", "expense"),
        BudgetItem(3, 50f, "Transport", "Telebirr", "expense"),
        BudgetItem(4, 1000f, "Savings", "MPESA", "saving")
    )

    val walletData = listOf(
        WalletItem(1, "CBE", 1500f, "Bank"),
        WalletItem(2, "BOA", 2000f, "Bank"),
        WalletItem(3, "telebirr", 500f, "Wallet"),
        WalletItem(4, "MPESSA", 300f, "Wallet")
    )

    fun getRandomDateInMonth(year: Int, month: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val randomDay = (1..daysInMonth).random()
        calendar.set(year, month, randomDay)
        return calendar.time
    }

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)

   val transactionData = listOf(
        TransactionItem(1, 50f, "Expense", "Food", getRandomDateInMonth(currentYear, currentMonth).toString()),
        TransactionItem(2, 100f, "Expense", "Transport", getRandomDateInMonth(currentYear, currentMonth).toString()),
        TransactionItem(3, 2000f, "Income", "Salary", getRandomDateInMonth(currentYear, currentMonth).toString()),
        TransactionItem(4, 150f, "Expense", "Shopping", getRandomDateInMonth(currentYear, currentMonth).toString()),
        TransactionItem(5, 50f, "Expense", "Food", getRandomDateInMonth(currentYear, currentMonth).toString())
    )

    val financialData = FinancialData(transactionData, walletData, budgetItemData)

    val jsonFormattedData = Json.encodeToString(
        value = financialData
    )






    val walletBalances = walletData.groupBy { it.name }
        .mapValues { (_, wallets) ->
            wallets.sumOf { it.balance.toDouble() }
        }

    val expensesByCategory = transactionData.filter { it.type == "Expense" }
        .groupBy { it.category }
        .mapValues { (_, expenses) ->
            expenses.sumOf { it.amount.toDouble() }
        }



    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            AnalysisButtonRow(navController, jsonFormattedData)
            Spacer(modifier = Modifier.height(8.dp))
        }
        item{
            val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
            val context = LocalContext.current
             val transactionsByDay = transactionData.groupBy {
                Calendar.getInstance().apply {
                    time = dateFormat.parse(it.date)!!
                }.get(Calendar.DAY_OF_MONTH)
            }
                .mapValues { (_, transactions) ->
                    transactions.sumOf { it.amount.toDouble() }
                }

            val lineChartData = transactionsByDay.map { (day, amount) ->
                LineData(x = day.toString(), y = amount.toFloat())
            }.sortedBy { it.x.toInt() } // Sort by day of the month

            val lineGraphStyle = LineGraphStyle(
                colors = LineGraphColors(
                    lineColor = MaterialTheme.colorScheme.secondary,
                    pointColor = Color.Red
                ),
                visibility = LineGraphVisibility(
                    isXAxisLabelVisible = true,
                    isYAxisLabelVisible = true
                ),
                yAxisLabelPosition = LabelPosition.LEFT,
            )
            Text("Transactions in a month", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LineGraph(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                data = lineChartData,
                onPointClick = { value: LineData ->
                    Toast.makeText(context, "Clicked on point: ${value.x}", Toast.LENGTH_SHORT).show()
                },
                style = lineGraphStyle,
            )
        }
        item{

            Spacer(modifier = Modifier.height(8.dp))

            val barChartData = expensesByCategory.map { (category, amount) ->
                BarData(x = category, y = amount.toFloat())
            }
            val barGraphStyle = BarGraphStyle(
                visibility = BarGraphVisibility(
                    isYAxisLabelVisible = true,
                    isXAxisLabelVisible = true,
                ),
                yAxisLabelPosition = LabelPosition.LEFT
            )
            Text("Expenses By Category",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            BarGraph(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                data = barChartData,
                style = barGraphStyle
            )
            Spacer(modifier = Modifier.size(10.dp))
        }

        item{
            val barChartDataForWallets = walletBalances.map { (walletName, balance) ->
                BarData(x = walletName, y = balance.toFloat())
            }

            Text("Wallet/Bank Amounts",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            val barGraphStyleForWallets = BarGraphStyle(
                visibility = BarGraphVisibility(
                    isYAxisLabelVisible = true,
                    isXAxisLabelVisible = true
                ),
                yAxisLabelPosition = LabelPosition.LEFT
            )
            BarGraph(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                data = barChartDataForWallets,
                style = barGraphStyleForWallets
            )
        }



    }


}


@Composable
fun AnalysisButtonRow(navController: NavHostController, jsonFormattedData: String) {

   Column(
       modifier = Modifier.fillMaxWidth(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Top
   ){
       Text("Ask Gemini", style = MaterialTheme.typography.titleMedium,
           modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
       Spacer(modifier = Modifier.height(8.dp))
       Row(
           modifier = Modifier.fillMaxWidth().padding(16.dp),
           horizontalArrangement = Arrangement.SpaceAround
       ) {

           AnalysisButton(
               text = "Summarize",
               icon = Icons.Default.Summarize,
               onClick = {
                   val geminiData = "Analyze the following financial data and provide a summary of my financial situation, highlighting key strengths and weaknesses. Also, suggest potential areas for improvement. Data: $jsonFormattedData"
                   navController.navigate(BudgetBuddyScreens.Chat.createRoute(geminiData))
               }
           )

           // Spending Analysis Button
           AnalysisButton(
               text = "Spending",
               icon = Icons.Default.ShoppingCart,
               onClick = {
                   val spendingAnalysisPrompt = """
                    Analyze the provided financial data and identify my top spending categories. 
                    Calculate the percentage of total expenses allocated to each category and provide 
                    insights into their spending habits. Offer suggestions on potential areas where 
                    expenses could be reduced.
                    Data: $jsonFormattedData
                """.trimIndent()
                   navController.navigate(BudgetBuddyScreens.Chat.createRoute(spendingAnalysisPrompt))
               }
           )


           AnalysisButton(
               text = "Budget",
               icon = Icons.Default.AttachMoney,
               onClick = {
                   val budgetOptimizationPrompt = """
                    Given the following financial data, including budget allocations, analyze my current spending against their budget. Identify areas of overspending 
                    or potential savings. Recommend adjustments to the budget for better financial 
                    management.
                    Data: $jsonFormattedData
                """.trimIndent()
                   navController.navigate(BudgetBuddyScreens.Chat.createRoute(budgetOptimizationPrompt))
               }
           )


       }

       Spacer(modifier = Modifier.height(8.dp))

//       Row(
//           modifier = Modifier.fillMaxWidth().padding(16.dp),
//           horizontalArrangement = Arrangement.SpaceAround
//       ) {
//
//           AnalysisButton(
//               text = "Savings",
//               icon = Icons.Default.Savings,
//               onClick = {
//                   val savingsGoalPrompt = """
//Assuming the user has a savings goal of [insert desired amount] to be reached
//within [insert desired timeframe], assess the feasibility of achieving this goal
//based on their current financial data. Provide recommendations on how to adjust
//spending or increase income to reach the desired savings.
//Data: $jsonFormattedData
//"""
//                   navController.navigate(BudgetBuddyScreens.Chat.createRoute(savingsGoalPrompt))
//               }
//           )
//
//           AnalysisButton(
//               text = "Debt",
//               icon = Icons.Default.Money,
//               onClick = {
//                   val debtManagementPrompt = """
//Analyze the provided financial data, focusing on any outstanding debts. Calculate
//the debt-to-income ratio and provide recommendations on how to prioritize debt
//repayment and potentially reduce interest costs.
//Data: $jsonFormattedData
//"""
//                   navController.navigate(BudgetBuddyScreens.Chat.createRoute(debtManagementPrompt))
//               }
//           )
//
//
//       }

   }
}

@Composable
fun AnalysisButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        )
    ) {
        Column(
            modifier = Modifier.clickable(onClick = onClick).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            IconButton(onClick = onClick) {
                Icon(imageVector = icon, contentDescription = text)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text, style = MaterialTheme.typography.bodySmall)
        }
    }
}

