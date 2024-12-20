package com.example.chapapp_compose.features_ui.auth.signup

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.domain.repository.auth.AuthRepository
import com.example.chapapp_compose.features_ui.auth.AuthState
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _registerState = MutableStateFlow(AuthState())
    val state = _registerState.asStateFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUser(email, password).collectLatest { result ->
            when (result) {
                is UiState.Loading -> {
                    _registerState.update { it.copy(isLoading = true) }
                }
                is UiState.Success -> {
                    _registerState.update { it.copy(isLoading = false, isSignUpSuccessful = true) }
                }
                is UiState.Error -> {
                    // Handle error
                }
            }
        }
    }
}