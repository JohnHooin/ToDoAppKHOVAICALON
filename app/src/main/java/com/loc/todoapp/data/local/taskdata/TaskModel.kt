package com.loc.todoapp.data.local.taskdata

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

// Here is where we define the task entity that will hold the task data

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val taskTitle: String,
    val description: String?,
    val endDate: String,
    val startingDate: String? = LocalDate.now().toString(),
    val isDone: Boolean,
    val category: CharCategory
)