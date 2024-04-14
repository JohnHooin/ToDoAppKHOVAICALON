package com.loc.todoapp.ui.todolist

import androidx.lifecycle.ViewModel
import com.loc.todoapp.data.local.taskdata.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {
}