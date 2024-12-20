package com.example.chapapp_compose.core.domain.usecase

abstract class BaseUseCase<in Params, out T> {
    abstract fun execute(param: Params): T
}