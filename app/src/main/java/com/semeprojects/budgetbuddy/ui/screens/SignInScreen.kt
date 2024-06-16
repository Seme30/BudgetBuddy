package com.semeprojects.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.semeprojects.budgetbuddy.ui.components.BButton
import com.semeprojects.budgetbuddy.ui.components.BText
import com.semeprojects.budgetbuddy.ui.nav_utils.BudgetBuddyScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navHostController: NavHostController
) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BButton(click = {}, text = "SIGN UP")
                Spacer(modifier = Modifier.padding(16.dp))
                BButton(click = {
                    navHostController.navigate(BudgetBuddyScreens.Home.route)
                }, text = "SIGN IN")
            }
            Spacer(modifier = Modifier.height(32.dp))
            BText(text = "SIGN UP", size = 16.sp)
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { BText("Email", size = 14.sp) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { BText("Password", size = 14.sp) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface
                )
            )
            BText(text = "*must contain 8-12 characters", size = 14.sp)
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider()


        }

}

//@Preview(showSystemUi = true)
//@Composable()
//fun viewSign(){
//    SignInScreen()
//}
