package com.loc.todoapp.data.local.taskdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


// Here is where we define the methods that will be used to interact with the database
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskModel: TaskModel)

    suspend fun updateTask(taskModel: TaskModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskModel)

    @Query("SELECT * FROM TaskModel WHERE id = :id")
    fun getTaskById(id: Int): TaskModel?

    @Query("SELECT * FROM TaskModel")
    fun getAllTasks(): Flow<List<TaskModel>>
}