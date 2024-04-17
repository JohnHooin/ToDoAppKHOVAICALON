package com.loc.todoapp.presentation.todolist.todolist_add_edit

import com.loc.todoapp.presentation.todolist.TodoListEvent


sealed class TodoListAddEditEvent {
    data class OnTitleChange(val title: String): TodoListAddEditEvent()
    data class OnDescriptionChange(val description: String): TodoListAddEditEvent()
    data class OnStartDateChange(val startDate: String): TodoListAddEditEvent()
    data class OnEndDateChange(val endDate: String): TodoListAddEditEvent()
    data class OnDoneChange(val isDone: Boolean): TodoListAddEditEvent()
    data object OnSaveTodoClick: TodoListAddEditEvent()


}