package com.semeprojects.budgetbuddy.domain.repository

sealed interface UiState {

    object Initial : UiState

    object Loading : UiState
    data class Success(val outputText: String) : UiState

    data class Error(val errorMessage: String) : UiState
}