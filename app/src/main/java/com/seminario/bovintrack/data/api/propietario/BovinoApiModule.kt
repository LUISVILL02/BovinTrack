package com.seminario.bovintrack.data.api.propietario

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BovinoApiModule {

    @Provides
    @Singleton
    fun provideBovinoApiService(retrofit: Retrofit): BovinoService {
        return retrofit.create(BovinoService::class.java)
    }
}