package com.semeprojects.budgetbuddy.domain.repository

sealed class Response<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T): Response<T>(data = data)
    class Fail<T>(errorMessage: String): Response<T>(errorMessage = errorMessage)

    class Loading<T>(data: T? = null) : Response<T>(data)
}