package com.example.chapapp_compose.features_ui.auth.signin

data class SignInResult(
    val data : UserData?,
    val errorMessage : String?
)

data class UserData(
    val userId : String,
    val username : String?,
    val profilePictureUrl : String?
)