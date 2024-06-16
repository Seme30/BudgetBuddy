package com.semeprojects.budgetbuddy.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semeprojects.budgetbuddy.data.datapreferences.DataStoreRepository
import com.semeprojects.budgetbuddy.ui.nav_utils.BudgetBuddyScreens
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(BudgetBuddyScreens.Welcome.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = BudgetBuddyScreens.Home.route
                    _isLoading.value  = false
                } else {
                    _startDestination.value = BudgetBuddyScreens.Welcome.route
                    _isLoading.value  = false
                }
            }
            _isLoading.value = false
        }
    }
}
