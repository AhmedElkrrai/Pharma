package com.example.pharmamanufacturer.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IOContext

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainContext

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @IOContext
    fun providesIODispatcher(): CoroutineContext = Dispatchers.IO

    @MainContext
    @Provides
    fun provideMainDispatcher(): CoroutineContext = Dispatchers.Main

    @Provides
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Job())
}