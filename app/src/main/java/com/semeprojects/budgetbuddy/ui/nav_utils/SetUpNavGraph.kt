package com.semeprojects.budgetbuddy.ui.nav_utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.semeprojects.budgetbuddy.ui.screens.AddBudgetScreen
import com.semeprojects.budgetbuddy.ui.screens.AddTransactionScreen
import com.semeprojects.budgetbuddy.ui.screens.ChatScreen
import com.semeprojects.budgetbuddy.ui.screens.HomeScreen
import com.semeprojects.budgetbuddy.ui.screens.SignInScreen
import com.semeprojects.budgetbuddy.ui.screens.TransactionList
import com.semeprojects.budgetbuddy.ui.screens.WelcomeScreen



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination: String,
    darkTheme: MutableState<Boolean>
){

    val canNavBack = remember { mutableStateOf(false) }
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){

        composable(route = BudgetBuddyScreens.Home.route){
            HomeScreen(
                navController = navController,
                selectedItemIndex = selectedItemIndex,
                darkTheme = darkTheme,
                onSelectedItemChange = { newIndex ->
                selectedItemIndex = newIndex
            }
                )
        }

        composable(
            route = BudgetBuddyScreens.SignIn.route
        ) {
            SignInScreen(navHostController = navController)
        }

        composable(
            route = BudgetBuddyScreens.Chat.route,
                    arguments = listOf(navArgument("message") { type = NavType.StringType })
        ) { backStackEntry ->
            ChatScreen(
                navController = navController,
                message = backStackEntry.arguments?.getString("message")
            )
        }

        composable(
            route = BudgetBuddyScreens.Settings.route
        ) {
            Settings(
                navController
            )
        }

        composable(
            route = BudgetBuddyScreens.TransactionList.route
        ){
            TransactionList(
                navController
            )
        }

        composable(
            route = BudgetBuddyScreens.Account.route,
        ) {
            Account(
                navController
            )
        }

        composable(
            route = BudgetBuddyScreens.AddBudget.route
        ){
            AddBudgetScreen(
                navController
            )
        }

        composable(
            route = BudgetBuddyScreens.Welcome.route
        ) {
            WelcomeScreen(
                navController
            )
        }

        composable(
            route = BudgetBuddyScreens.AddTransaction.route
        ){
            AddTransactionScreen()
        }
    }
    }




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    navController: NavHostController
){
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
                title = { Text("Settings")},
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Settings List")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Account(
    navController: NavHostController
){
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
                title = { Text("Profile")},
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Profile")
        }
    }
}