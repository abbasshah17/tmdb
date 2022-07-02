package com.tmdb.network.interceptors

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor

@Module
@InstallIn(SingletonComponent::class)
interface InterceptorModule {

    @Binds
    @IntoSet
    fun addsIntoSetApiKeyInjectionInterceptor(apiKeyInjectionInterceptor: ApiKeyInjectionInterceptor): Interceptor
}