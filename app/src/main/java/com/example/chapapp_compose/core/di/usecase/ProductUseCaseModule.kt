package com.example.chapapp_compose.core.di.usecase

import com.example.chapapp_compose.core.domain.repository.product.DbProductRepository
import com.example.chapapp_compose.core.domain.repository.product.ProductRepository
import com.example.chapapp_compose.core.domain.usecase.product.GetProductByIdUseCase
import com.example.chapapp_compose.core.domain.usecase.product.GetProductsUseCase
import com.example.chapapp_compose.core.domain.usecase.product.SearchProductUseCase
import com.example.chapapp_compose.core.domain.usecase.product.db.DeleteProductDbUseCase
import com.example.chapapp_compose.core.domain.usecase.product.db.GetProductByIdDbUseCase
import com.example.chapapp_compose.core.domain.usecase.product.db.GetProductsDbUseCase
import com.example.chapapp_compose.core.domain.usecase.product.db.InsertProductDbUseCase
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

    @Provides
    fun provideGetProductByIdDbUseCase(dbProductRepository: DbProductRepository): GetProductByIdDbUseCase {
        return GetProductByIdDbUseCase(dbProductRepository)
    }

    @Provides
    fun provideGetProductsDbUseCase(dbProductRepository: DbProductRepository): GetProductsDbUseCase {
        return GetProductsDbUseCase(dbProductRepository)
    }

    @Provides
    fun provideInsertProductDbUseCase(dbProductRepository: DbProductRepository): InsertProductDbUseCase {
        return InsertProductDbUseCase(dbProductRepository)
    }

    @Provides
    fun provideDeleteProductDbUseCase(dbProductRepository: DbProductRepository): DeleteProductDbUseCase {
        return DeleteProductDbUseCase(dbProductRepository)
    }

}