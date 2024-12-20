package com.example.chapapp_compose.core.di.usecase

import com.example.chapapp_compose.core.domain.repository.product.ProductRepository
import com.example.chapapp_compose.core.domain.usecase.product.GetProductByIdUseCase
import com.example.chapapp_compose.core.domain.usecase.product.GetProductsUseCase
import com.example.chapapp_compose.core.domain.usecase.product.SearchProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ProductUseCaseModule {

    @Provides
    fun provideGetProductUseCase(productRepository: ProductRepository): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    @Provides
    fun provideSearchProductUseCase(productRepository: ProductRepository): SearchProductUseCase {
        return SearchProductUseCase(productRepository)
    }

    @Provides
    fun provideGetProductByIdUseCase(productRepository: ProductRepository): GetProductByIdUseCase {
        return GetProductByIdUseCase(productRepository)
    }

}