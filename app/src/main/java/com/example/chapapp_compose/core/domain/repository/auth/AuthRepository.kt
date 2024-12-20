package com.example.chapapp_compose.core.domain.repository.auth

import com.example.chapapp_compose.core.data.UiState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password : String) : Flow<UiState<AuthResult>>

    fun registerUser(email: String, password : String) : Flow<UiState<AuthResult>>
}

