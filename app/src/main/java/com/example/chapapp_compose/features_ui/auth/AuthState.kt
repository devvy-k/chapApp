package com.example.chapapp_compose.features_ui.auth

data class AuthState(
    val isSignInSuccessful : Boolean = false,
    val isSignUpSuccessful : Boolean = false,
    val signInError : String? = null,
    val signUpError : String? = null,
    val isLoading : Boolean = false
)
