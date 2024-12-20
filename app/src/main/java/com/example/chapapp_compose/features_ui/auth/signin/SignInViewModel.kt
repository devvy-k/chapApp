package com.example.chapapp_compose.features_ui.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.domain.repository.auth.AuthRepository
import com.example.chapapp_compose.features_ui.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        authRepository.loginUser(email, password).collect { result ->
            when (result) {
                is UiState.Loading -> {
                    _state.update { it.copy(isLoading = true)  }
                }
                is UiState.Success -> {
                    _state.update { it.copy(isLoading = false, isSignInSuccessful = true) }
                }
                is UiState.Error -> {
                    // Handle error
                }
            }
        }
    }

    fun onSignInGoogleResult(result: SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }
    fun resetState(){
        _state.update { AuthState() }
    }
}