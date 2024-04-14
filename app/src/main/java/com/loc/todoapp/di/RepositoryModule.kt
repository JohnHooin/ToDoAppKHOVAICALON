package com.loc.todoapp.di

import com.loc.todoapp.data.repository.TodoListRepositoryImpl
import com.loc.todoapp.domain.repository.TodoListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindToDoListRepository(
        toDoRepositoryImpl: TodoListRepositoryImpl
    ): TodoListRepository

}