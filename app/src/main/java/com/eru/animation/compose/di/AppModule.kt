package com.eru.animation.compose.di

import com.eru.animation.compose.network.ApiClient
import com.eru.animation.compose.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideApiInterface(): ApiInterface {
        return ApiClient.getClient()
    }
}
