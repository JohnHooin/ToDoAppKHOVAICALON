package com.loc.todoapp.domain.usecase.todolist

import com.loc.todoapp.data.model.TodoList
import com.loc.todoapp.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodosUseCaseImpl @Inject constructor(
    private val repository: TodoListRepository
) : GetTodosUseCase {

    override operator fun invoke() = repository.readAllTodos()
}

interface GetTodosUseCase {
    operator fun invoke(): Flow<List<TodoList>>
}