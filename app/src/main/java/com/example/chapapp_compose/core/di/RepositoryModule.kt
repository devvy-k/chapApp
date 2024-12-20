package com.example.chapapp_compose.core.di

import com.example.chapapp_compose.core.data.datasource.remote.ApiService
import com.example.chapapp_compose.core.data.repository.auth.AuthRepositoryImpl
import com.example.chapapp_compose.core.data.repository.product.ProductRepositoryImpl
import com.example.chapapp_compose.core.domain.repository.auth.AuthRepository
import com.example.chapapp_compose.core.domain.repository.product.ProductRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth = firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}