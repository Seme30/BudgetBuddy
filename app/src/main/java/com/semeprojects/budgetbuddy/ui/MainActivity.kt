package com.semeprojects.budgetbuddy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.semeprojects.budgetbuddy.R
import com.semeprojects.budgetbuddy.ui.theme.BudgetBuddyTheme
import com.semeprojects.budgetbuddy.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }



        setContent {
            val initialTheme = isSystemInDarkTheme()

            val darkTheme = remember { mutableStateOf(initialTheme) }

            BudgetBuddyTheme(
                darkTheme = darkTheme.value
            ) {

                val loadingState by splashViewModel.isLoading

                if (loadingState) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo"
                        )
                        CircularProgressIndicator(
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                } else {
                    Wrapper(
                        darkTheme = darkTheme,
                        startDestination = splashViewModel.startDestination.value
                    )
                }
            }
        }
    }
}
