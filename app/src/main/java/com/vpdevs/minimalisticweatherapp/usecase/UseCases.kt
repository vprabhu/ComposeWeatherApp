package com.vpdevs.minimalisticweatherapp.usecase

interface BaseUseCase {

    val tag: String
        get() = this::class.java.name

    companion object {
        private const val TAG_PREFIX = "VNetwork"
    }

}

interface UseCase<in T, out R> : BaseUseCase {

    fun execute(input: T): Result<R>
}

interface UseCaseSuspended<in T, out R> : BaseUseCase {

    suspend fun execute(input: T): Result<R>
}

interface UseCaseNoInput<out R> : BaseUseCase {

    fun execute(): Result<R>
}

interface UseCaseNoInputSuspended<out R> : BaseUseCase {

    suspend fun execute(): Result<R>
}

