package com.loc.todoapp.domain.usecase.todolist

import com.loc.todoapp.data.model.TodoList
import com.loc.todoapp.domain.repository.TodoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DeleteTodoUseCaseImpl @Inject constructor(
    private val repository: TodoListRepository
) : DeleteTodoUseCase {

    override suspend fun invoke(todo: TodoList) {
        withContext(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }

}
interface DeleteTodoUseCase {
    suspend operator fun invoke(todo: TodoList)
}