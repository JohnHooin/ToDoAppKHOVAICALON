package com.loc.todoapp.di

import android.app.Application
import com.loc.todoapp.data.manager.LocalUserManagerImpl
import com.loc.todoapp.domain.manager.LocalUserManager
import com.loc.todoapp.domain.manager.usecases.AppEntryUserCases
import com.loc.todoapp.domain.manager.usecases.ReadAppEntry
import com.loc.todoapp.domain.manager.usecases.SaveAppEntry
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
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager  = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUserCases(localUserManager : LocalUserManager) =  AppEntryUserCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}