package com.loc.todoapp.presentation.todolist

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.todoapp.data.local.taskdata.TaskModel
import com.loc.todoapp.presentation.home.HomeViewModel
import com.loc.todoapp.util.StringAndDateConvertor
import com.loc.todoapp.util.UIEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val tasks = viewModel.tasks.collectAsState(initial = emptyList())
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.actionLabel
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TodoListEvent.OnUndoDelete)
                    }
                }
                is UIEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TodoListEvent.OnAddTaskClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { paddings ->
        ToDoListVisibility(
            tasks = tasks.value,
            paddings = paddings,
            onEvent = { viewModel.onEvent(it) }
        )
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ToDoListVisibility(
    tasks: List<TaskModel>,
    onEvent: (TodoListEvent) ->Unit,
    paddings: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddings),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = tasks.isEmpty(),
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Text(
                text = "No Todos Yet!",
                color = Color.White,
                fontSize = 22.sp
            )
        }
        AnimatedVisibility(
            visible = tasks.isNotEmpty(),
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = paddings.calculateBottomPadding() + 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        start = 8.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    tasks.sortedBy { it.isDone },
                    key = { it.id?.toHexString() ?: -1 }
                ) { todo ->
                    TodoItem(
                        task = todo,
                        onEvent = {
                            onEvent(it)
                        }
                    )
                }
            }
        }
    }
}
