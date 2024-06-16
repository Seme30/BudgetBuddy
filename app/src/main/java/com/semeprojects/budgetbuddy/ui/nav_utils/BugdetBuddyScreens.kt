package com.semeprojects.budgetbuddy.ui.nav_utils

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.semeprojects.budgetbuddy.R

sealed class BudgetBuddyScreens(
    val route: String,
    @StringRes val resourceId: Int = 0,
    val icon: @Composable () -> Unit = {}
) {

    data object Home : BudgetBuddyScreens(
        route = "HomeScreen"
    )

    data object SignIn : BudgetBuddyScreens(
        route = "SignInScreen"
    )

    data object AddTransaction : BudgetBuddyScreens(
        route = "AddTransactionScreen"
    )

    data object TransactionList : BudgetBuddyScreens(
        route = "TransactionListScreen"
    )


    data object AddBudget: BudgetBuddyScreens(
        route = "AddBudgetScreen"
    )

    object Chat : BudgetBuddyScreens("chat/{message}") {
        fun createRoute(message: String) = "chat/$message"
    }
    data object Welcome : BudgetBuddyScreens(
        route = "WelcomeScreen",
        resourceId = R.string.welcome,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.welcome)
            )
        }
    )

    data object Settings : BudgetBuddyScreens(
        route = "SettingsScreen",
        resourceId = R.string.transaction,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.transaction)
            )
        })

    data object Account : BudgetBuddyScreens(
        route = "AccountScreen",
        resourceId = R.string.budget,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.budget)
            )
        }
    )
}