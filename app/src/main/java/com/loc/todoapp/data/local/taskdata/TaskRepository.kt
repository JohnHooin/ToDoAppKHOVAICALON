package com.loc.todoapp.data.local.taskdata

import kotlinx.coroutines.flow.Flow


// He
interface TaskRepository {
    suspend fun insertTask(taskModel: TaskModel)

    suspend fun deleteTask(taskModel: TaskModel)

    fun getTaskById(id: Int): TaskModel?

    fun getAllTasks(): Flow<List<TaskModel>>
}