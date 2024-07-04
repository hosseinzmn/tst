package com.example.borvvista.domain.model

sealed class NetworkException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    class FetchError(message: String? = null, cause: Throwable? = null) : NetworkException(message, cause)
    class ParseError(message: String? = null, cause: Throwable? = null) : NetworkException(message, cause)
    class ServerError(val code: Int, message: String? = null, cause: Throwable? = null) : NetworkException(message, cause)
    class UnknownError(message: String? = null, cause: Throwable? = null) : NetworkException(message, cause)
}

fun NetworkException.getUserMessage(): String {
    return when (this) {
        is NetworkException.FetchError -> "Network error: ${this.message}"
        is NetworkException.ParseError -> "Error parsing data"
        is NetworkException.ServerError -> "Server error: Code ${this.code}"
        is NetworkException.UnknownError -> "Unknown network error"
    }
}