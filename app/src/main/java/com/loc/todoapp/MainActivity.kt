package com.loc.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.loc.todoapp.presentation.home.HomeScreen
import com.loc.todoapp.presentation.home.HomeViewModel
import com.loc.todoapp.ui.theme.TodoListComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListComposeTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
//                    val todoList by viewModel.todoList.collectAsStateWithLifecycle()
                    HomeScreen()
//                    HomeScreen(
//                        todoList = todoList,
//                        onCreateTodo = viewModel::createTodo,
//                        onUpdateTodo = viewModel::updateTodo,
//                        onDeleteTodo = viewModel::deleteTodo,
//                        onEditTodo = {todo ->
//                            navController.navigate("edit/${todo._id}")
//                        }
//                    )
                }
            }
        }
    }
}