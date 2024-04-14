package com.loc.todoapp.di

import com.loc.todoapp.domain.repository.TodoListRepository
import com.loc.todoapp.domain.usecase.todolist.AddTodoUseCase
import com.loc.todoapp.domain.usecase.todolist.AddTodoUseCaseImpl
import com.loc.todoapp.domain.usecase.todolist.DeleteTodoUseCase
import com.loc.todoapp.domain.usecase.todolist.DeleteTodoUseCaseImpl
import com.loc.todoapp.domain.usecase.todolist.GetTodosUseCase
import com.loc.todoapp.domain.usecase.todolist.GetTodosUseCaseImpl
import com.loc.todoapp.domain.usecase.todolist.UpdateTodoUseCase
import com.loc.todoapp.domain.usecase.todolist.UpdateTodoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideGetTodos(
        repository: TodoListRepository
    ): GetTodosUseCase {
        return GetTodosUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideAddTodo(
        repository: TodoListRepository
    ): AddTodoUseCase {
        return AddTodoUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteTodo(
        repository: TodoListRepository
    ): DeleteTodoUseCase {
        return DeleteTodoUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateTodo(
        repository: TodoListRepository
    ): UpdateTodoUseCase {
        return UpdateTodoUseCaseImpl(repository)
    }

}