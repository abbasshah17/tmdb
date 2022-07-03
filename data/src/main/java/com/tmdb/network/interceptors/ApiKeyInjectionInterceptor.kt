package com.tmdb.network.interceptors

import com.tmdb.domain.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInjectionInterceptor @Inject constructor(
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .url(
                    chain.request().url
                        .newBuilder()
                        .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                        .build()
                ).build()
        )
    }
}

private const val API_KEY = "api_key"