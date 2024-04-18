package com.loc.todoapp.presentation.todolist.todolist_add_edit

import com.loc.todoapp.presentation.todolist.TodoListEvent
import java.time.LocalDate
import java.time.LocalTime


sealed class TodoListAddEditEvent {
    data class OnTitleChange(val title: String): TodoListAddEditEvent()
    data class OnDescriptionChange(val description: String): TodoListAddEditEvent()
    data class OnStartDateChange(val startDate: LocalDate): TodoListAddEditEvent()
    data class OnStartTimeChange(val startTime: LocalTime): TodoListAddEditEvent()
    data class OnEndDateChange(val endDate: LocalDate): TodoListAddEditEvent()
    data class OnEndTimeChange(val endTime: LocalTime): TodoListAddEditEvent()
    data class OnDoneChange(val isDone: Boolean): TodoListAddEditEvent()
    data object OnSaveTodoClick: TodoListAddEditEvent()


}