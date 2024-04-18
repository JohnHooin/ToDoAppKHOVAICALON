package com.loc.todoapp.data.local.taskdata

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.loc.todoapp.data.Converters


// Here is where we define the database and the entities that will be used
@Database(entities = [TaskModel::class], version = 3)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase(){
    abstract val dao: TaskDao  // Research more on this

    companion object {
        const val DATABASE_NAME: String = "todo_app_database"
    }

}