package com.semeprojects.budgetbuddy.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpensesForm(){

    val selectedWallet = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }

    val options = listOf("Expense", "Income", "Saving")
    var selectedOption by remember { mutableStateOf(options[0]) }
    val walletList = listOf("CBE", "BOA", "telebirr", "MPESSA")

        val expenseCategoryList = listOf(
            "Groceries",
            "Transport",
            "Rent",
            "Utilities",
            "Dining",
            "Shopping",
            "Entertainment",
            "Clothing",
            "Healthcare",
            "Education",
            "Gift",
            "Travel",
            "Subscription",
            "Other"
        )
        val incomeCategoryList = listOf(
            "Salary",
            "Wage",
            "Investment",
            "Rent",
            "Business",
            "Hustle",
            "Gift",
            "Refund",
            "Other"
        )



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
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
            Spacer(modifier = Modifier.height(10.dp))
            BTextField(
                inputState = amount,
                placeholder = {
                    Text(
                        "Amount",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            BDropDown(
                inputState = selectedWallet,
                placeholder = "Select a Bank",
                types = walletList
            )

            Spacer(modifier = Modifier.height(10.dp))

            when(selectedOption){
                "Expense" -> ExpenseCategory(expenseCategoryList)
                "Income" -> ExpenseCategory(incomeCategoryList)
                else -> null
            }

            Spacer(modifier = Modifier.height(10.dp))

            DDatePicker {
                selectedDate.value = it.toString()
                println(selectedDate.value)
            }

            Spacer(modifier = Modifier.height(20.dp))
            BButton(text = "Add Transaction"){
                println("Add Transaction")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
}
@Composable
fun ExpenseComponent(name: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .border(
                shape = RoundedCornerShape(8.dp),
                width = 0.1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
    ) {
        Card(
            modifier = Modifier
//            .padding(vertical = 8.dp)
                .shadow(elevation = 4.dp)
                .width(115.dp)
                .clickable(onClick = onClick)
//            .padding(10.dp)
            ,
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                contentColor = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
            )

        ) {
            Column (
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
@Composable
fun ExpenseCategory(
    categoryList: List<String>
) {
    var selectedOption by remember { mutableStateOf("") }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .shadow(elevation = 4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        )
    ) {
            LazyVerticalGrid (
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(5.dp),
                modifier = Modifier
                    .height(200.dp)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center

            ) {
                item {
                    ExpenseComponent("Add Category", false, onClick = { })
                }
                items(categoryList.size) {
                    ExpenseComponent(categoryList[it], selectedOption == categoryList[it]){
                        selectedOption = categoryList[it]
                    }
                }
            }

    }
}
@Composable
fun CustomRadioButton(
    text: String,
    isSelected: Boolean,
    onSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
            .clickable { onSelected(text) },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .shadow(elevation = 4.dp)
                .width(115.dp)
            ,
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background,
                contentColor = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onBackground
            )

        ) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(color = if (isSelected) MaterialTheme.colorScheme.onSecondary else Color.Gray)
            )
        }
    }
}
