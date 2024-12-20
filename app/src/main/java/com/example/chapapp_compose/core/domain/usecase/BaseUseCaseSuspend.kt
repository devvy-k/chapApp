package com.example.chapapp_compose.core.domain.usecase

abstract class BaseUseCaseSuspend<in Params, out T> {
    abstract suspend fun execute(params: Params): T
}