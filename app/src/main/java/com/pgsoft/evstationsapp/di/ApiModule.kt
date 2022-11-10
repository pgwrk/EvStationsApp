package com.pgsoft.evstationsapp.di

import com.google.gson.GsonBuilder
import com.pgsoft.evstationsapp.BuildConfig
import com.pgsoft.evstationsapp.data.remote.auth.AuthApi
import com.pgsoft.evstationsapp.data.remote.interceptors.AuthInterceptor
import com.pgsoft.evstationsapp.data.remote.interceptors.StationApiMockInterceptor
import com.pgsoft.evstationsapp.data.remote.station.StationsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
         Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(StationApiMockInterceptor())
            .addInterceptor(authInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            )
            .build()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideStationsApi(retrofit: Retrofit): StationsApi = retrofit.create(StationsApi::class.java)
}