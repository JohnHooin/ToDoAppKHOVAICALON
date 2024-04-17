package com.loc.todoapp.data.local.taskdata

import kotlinx.coroutines.flow.Flow



// Actually I don't think this is necessary in this to do project for middle term exam 'cause
// this thing makes the project more complex and redundant but I will do it anyway for the sake of
// learning and understanding the concept of repository pattern
class TaskRepositoryImpl (
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun insertTask(taskModel: TaskModel) {
        taskDao.insertTask(taskModel)
    }

    override suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel)
    }

    override fun getTaskById(id: Int): TaskModel? {
        return taskDao.getTaskById(id)
    }

    override fun getAllTasks(): Flow<List<TaskModel>> {
        return taskDao.getAllTasks()
    }
}