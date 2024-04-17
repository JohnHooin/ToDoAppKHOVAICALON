package com.loc.todoapp.presentation.todolist

import com.loc.todoapp.data.local.taskdata.TaskModel

sealed class TodoListEvent {
    data class OnTaskClick(val task: TaskModel): TodoListEvent()
    data class OnDoneChange(val task: TaskModel, val isDone: Boolean): TodoListEvent()
    data object OnAddTaskClick: TodoListEvent()
    data object OnUndoDelete: TodoListEvent()
    data class OnDeleteTaskClick(val task: TaskModel): TodoListEvent()


}