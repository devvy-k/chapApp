package com.example.chapapp_compose.core.data.repository.auth

import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.domain.repository.auth.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth : FirebaseAuth
) : AuthRepository {
    override fun loginUser(email: String, password: String) : Flow<UiState<AuthResult>> {
        return flow {
            emit(value = UiState.Loading)
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(value = UiState.Success(data = result))
        }.catch {
            emit(value = UiState.Error(errorMessage = it.message.toString()))
        }
    }

    override fun registerUser(email: String, password: String) : Flow<UiState<AuthResult>> {
        return flow {
            emit(value = UiState.Loading)
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(value = UiState.Success(data = result))
        }.catch {
            emit(value = UiState.Error(errorMessage = it.message.toString()))
        }
    }
}
