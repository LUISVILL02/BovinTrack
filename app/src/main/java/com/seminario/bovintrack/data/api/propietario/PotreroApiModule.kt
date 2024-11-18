package com.seminario.bovintrack.data.api.propietario

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PotreroApiModule {
    @Provides
    @Singleton
    fun providePotreroApiService(retrofit: Retrofit): PotreroApiService {
        return retrofit.create(PotreroApiService::class.java)
    }
}