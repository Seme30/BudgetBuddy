package com.semeprojects.budgetbuddy.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.semeprojects.budgetbuddy.ui.nav_utils.SetUpNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Wrapper(startDestination: String, darkTheme: MutableState<Boolean>) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()

        SetUpNavGraph(
            darkTheme = darkTheme,
            navController = navController,
            startDestination = startDestination
        )
    }
}