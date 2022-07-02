package com.aaa.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        okhttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://google.com")
            .client(okhttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptors.forEach { interceptor ->
                    addInterceptor(interceptor)
                }
            }
            .build()
    }

    @Provides
    @IntoSet
    fun addsIntoSetHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}