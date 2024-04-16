package com.loc.todoapp.presentation.todolist.todolist_add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.todoapp.data.local.taskdata.TaskModel
import com.loc.todoapp.data.local.taskdata.TaskRepository
import com.loc.todoapp.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.loc.todoapp.util.StringAndDateConvertor
import javax.inject.Inject

@HiltViewModel
class TodoListAddEditViewModel @Inject constructor(
    private val repository: TaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var task by mutableStateOf<TaskModel?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var startDate by mutableStateOf("")
        private set
    var endDate by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val taskId = savedStateHandle.get<Int>("id")!!
        if (taskId != -1) {
            viewModelScope.launch {
                repository.getTaskById(taskId)?.let { todo ->
                    title = todo.title
                    description = todo.description
                    startDate = StringAndDateConvertor.dateToString(todo.startDate)
                    endDate = StringAndDateConvertor.dateToString(todo.endDate)
                    this@TodoListAddEditViewModel.task = todo
                }
            }
        }
    }

    fun onEvent(event: TodoListAddEditEvent) {
        when (event) {
            is TodoListAddEditEvent.OnTitleChange -> {
                title = event.title
            }
            is TodoListAddEditEvent.OnDescriptionChange -> {
                description = event.description
            }
            is TodoListAddEditEvent.OnStartDateChange -> {
                startDate = event.startDate
            }
            is TodoListAddEditEvent.OnEndDateChange -> {
                endDate = event.endDate
            }
            is TodoListAddEditEvent.OnDoneChange -> {
                task = task?.copy(isDone = event.isDone)
            }

            is TodoListAddEditEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(
                            UIEvent.ShowSnackbar(
                                message = "The title can't be empty"
                            )
                        )
                        return@launch
                    }
                    if (description.isBlank()) {
                        sendUiEvent(
                            UIEvent.ShowSnackbar(
                                message = "The description can't be empty"
                            )
                        )
                        return@launch
                    }
                    if (startDate.isBlank() || endDate.isBlank()) {
                        sendUiEvent(
                            UIEvent.ShowSnackbar(
                                message = "The start day and deadline can't be empty"
                            )
                        )
                        return@launch
                    }
                    repository.insertTask(
                        TaskModel(
                            title = title,
                            description = description,
                            isDone = task?.isDone ?: false,
                            id = task?.id,
                            startDate = task?.startDate ?: StringAndDateConvertor.stringToDate(startDate),
                            endDate = task?.endDate ?: StringAndDateConvertor.stringToDate(endDate)
                        )
                    )
                    sendUiEvent(UIEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
