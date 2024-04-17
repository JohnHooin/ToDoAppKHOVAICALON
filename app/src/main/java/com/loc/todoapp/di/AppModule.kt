package com.loc.todoapp.di

import android.app.Application
import androidx.room.Room
import com.loc.todoapp.data.local.taskdata.TaskDatabase
import com.loc.todoapp.data.local.taskdata.TaskRepository
import com.loc.todoapp.data.local.taskdata.TaskRepositoryImpl
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
@InstallIn(SingletonComponent::class) // Define how long object dependency will be alive
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

    @Provides
    @Singleton
    fun provideTaskDatabase(
        app: Application
    ): TaskDatabase {
        return Room.databaseBuilder(app, TaskDatabase::class.java, TaskDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration() // This will delete the database and recreate it when the version number is changed which means that the database is not backward compatible or changed structure
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskDatabase: TaskDatabase
    ) : TaskRepository{
        return TaskRepositoryImpl(taskDatabase.dao)

    } // this will create repository instance using the dao from
    // the database instance that we have created above


}