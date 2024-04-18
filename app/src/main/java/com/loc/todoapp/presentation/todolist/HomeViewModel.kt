package com.loc.todoapp.presentation.home

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.todoapp.data.local.taskdata.TaskModel
import com.loc.todoapp.data.local.taskdata.TaskRepository
import com.loc.todoapp.presentation.nvgraph.Route
import com.loc.todoapp.presentation.todolist.TodoListEvent
import com.loc.todoapp.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// View-models are where we do the business logic of the app and they state of the view and the data
// that is being displayed on the screen. The state of the view is the data that is being displayed on
// screen that should have been frequently updated. The view-models are also responsible for handling
// the user interactions and the communication between the view and the data. The state hold in the
// view-models are not gonna be destroyed when the view is changed or destroyed.

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {
    val tasks = repository.getAllTasks()

    // Channel is a coroutine construct that allows us to send data between coroutines. It is a
    // unidirectional channel that can be used to send data from one coroutine to another.
    // Why we use Channel instead of LiveData? LiveData is a lifecycle-aware data holder with the
    // observer pattern. It is used to hold the data that is being displayed on the screen. LiveData
    // is lifecycle-aware, which means that it is aware of the lifecycle of the view and it will only
    // update the data when the view is in the active state.
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTask: TaskModel? = null

    fun onEvent(event: TodoListEvent) {

        when(event) {
            is TodoListEvent.OnTaskClick -> {
                sendUIEvent(UIEvent.Navigate(Route.AddTask.route + "?id=${event.task.id}"))
            }
            is TodoListEvent.OnAddTaskClick -> {
                sendUIEvent(UIEvent.Navigate(Route.AddTask.route))
            }
            is TodoListEvent.OnUndoDelete -> {
                deletedTask?.let {
                    viewModelScope.launch {
                        repository.insertTask(it)
                    }
                }
            }
            is TodoListEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    deletedTask = event.task
                    repository.deleteTask(event.task)
                    sendUIEvent(UIEvent.ShowSnackbar(
                        message = "Todo deleted",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Short
                    ))
                }
            }
            is TodoListEvent.OnDoneChange-> {
                viewModelScope.launch {
                    repository.insertTask(event.task.copy(isDone = event.isDone))
                }
            }

        }
    }

    private fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

