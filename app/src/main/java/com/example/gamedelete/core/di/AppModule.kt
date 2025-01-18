package com.example.gamedelete.core.di

import com.example.gamedelete.core.data.PreferencesRepositoryImpl
import com.example.gamedelete.core.domain.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providePreferencesRepository() : PreferencesRepository = PreferencesRepositoryImpl()

}