package com.semeprojects.budgetbuddy.ui.screens

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.semeprojects.budgetbuddy.R
import com.semeprojects.budgetbuddy.ui.components.HomeUI
import com.semeprojects.budgetbuddy.ui.nav_utils.BudgetBuddyScreens


data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    selectedItemIndex: Int,
    onSelectedItemChange: (Int) -> Unit,
    darkTheme: MutableState<Boolean>
) {


    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            route = BudgetBuddyScreens.Welcome.route
        ),
        BottomNavigationItem(
            title = "Budget",
            selectedIcon = Icons.Default.AccountBalanceWallet,
            unselectedIcon = Icons.Outlined.AccountBalanceWallet,
            hasNews = false,
            route = BudgetBuddyScreens.Account.route,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Add",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            route = BudgetBuddyScreens.AddTransaction.route,
            hasNews = true,
        ),
        BottomNavigationItem(
            title = "Wallet",
            selectedIcon = Icons.Filled.Wallet,
            unselectedIcon = Icons.Outlined.Wallet,
            hasNews = false,
            route = BudgetBuddyScreens.Settings.route,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Analytics",
            selectedIcon = Icons.Filled.BarChart,
            unselectedIcon = Icons.Outlined.BarChart,
            hasNews = false,
            route = BudgetBuddyScreens.Account.route,
            badgeCount = 45
        ),
    )

    val context = LocalContext.current
    val view = LocalView.current


    Scaffold(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        topBar = {
            var expanded by remember { mutableStateOf(false) }

            TopAppBar(
                title = {
                    Text(text = stringResource(id = when(selectedItemIndex){
                        0 -> R.string.home
                        1 -> R.string.budget
                        2 -> R.string.transaction
                        3 -> R.string.wallet
                        4 -> R.string.report
                        else -> R.string.app_name
                    }), textAlign = TextAlign.Center)
                        },
                modifier = Modifier.fillMaxWidth().shadow(elevation = 4.dp),
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                    // Overflow menu
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = {
                                navController.navigate(BudgetBuddyScreens.Settings.route)
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Settings, contentDescription = "Settings")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Profile") },
                            onClick = {
                                navController.navigate(BudgetBuddyScreens.Account.route)
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(Icons.Default.AccountCircle, contentDescription = "Account")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(if (darkTheme.value) "Light Mode" else "Dark Mode") },
                            onClick = {
                                darkTheme.value = !darkTheme.value
                                (context as? Activity)?.window?.let { window ->
                                    val wic = WindowCompat.getInsetsController(window, view)
                                    wic.isAppearanceLightStatusBars = !darkTheme.value
                                }
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = if (darkTheme.value) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                                    contentDescription = "Toggle Dark Mode"
                                )
                            },
                            trailingIcon = {
                                Switch(
                                    checked = darkTheme.value,
                                    onCheckedChange = { darkTheme.value = it },
                                    modifier = Modifier.scale(0.8f)
                                )
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.background,
                            unselectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.secondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            disabledIconColor = MaterialTheme.colorScheme.onPrimary,
                            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedIndicatorColor = MaterialTheme.colorScheme.secondary,
                        ),
                        selected = selectedItemIndex == index,
                        onClick = {
                            onSelectedItemChange(index)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            if (index == 2) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.secondary)
                                ) {
                                    Icon(
                                        imageVector = if (selectedItemIndex == index) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title,
                                        tint = MaterialTheme.colorScheme.background
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = if (selectedItemIndex == index) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp).padding(paddingValues),
        ) {
            when (selectedItemIndex) {
                0 -> HomeUI(
                    navHostController = navController,
                    onSelectedItemChange
                )
                1 -> BudgetScreen(
                    navController
                )
                2 -> AddTransactionScreen()
                3 -> WalletScreen()
                4 -> ReportScreen(
                    navController
                )
                else -> Text("Unknown screen")
            }
        }
    }
}

