package com.semeprojects.budgetbuddy.ui.screens

import androidx.annotation.DrawableRes
import com.semeprojects.budgetbuddy.R

sealed class OnBoardingScreen(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {

    data object First: OnBoardingScreen(
        image = R.drawable.logo,
        title = "Companion in Finance",
        description = "Welcome to BudgetBuddy!"
    )

    data object Second: OnBoardingScreen(
        image = R.drawable.logo,
        title = "Master Your Money",
        description = "Track your income and expenses"
    )

    data object Third: OnBoardingScreen(
        image = R.drawable.logo,
        title = "Your Finance Partner",
        description = "Get personalized insights"
    )


}