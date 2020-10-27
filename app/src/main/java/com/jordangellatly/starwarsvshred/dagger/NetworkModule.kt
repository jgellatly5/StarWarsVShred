package com.jordangellatly.starwarsvshred.dagger

import com.jordangellatly.starwarsvshred.network.ServiceBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrlString(): String = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String, client: OkHttpClient) =
        ServiceBuilder(baseUrl, client)

    companion object {
        private const val BASE_URL = "BASE_URL"
    }
}