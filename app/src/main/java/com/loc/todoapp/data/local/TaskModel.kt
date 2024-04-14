package com.loc.todoapp.data.local

data class TaskModel(
    val taskTitle: String,
    val description: String,
    val endDate: String,
    val startingDate: String,
    val status: String,
    val subtask: List<TaskModel>
)