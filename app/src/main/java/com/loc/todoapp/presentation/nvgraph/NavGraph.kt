package com.loc.todoapp.presentation.nvgraph


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.loc.todoapp.presentation.home.HomeViewModel
import com.loc.todoapp.presentation.todolist.HomeScreen
import com.loc.todoapp.presentation.onboarding.OnBoardingScreen
import com.loc.todoapp.presentation.onboarding.OnBoardingViewModel
import com.loc.todoapp.presentation.todolist.todolist_add_edit.TodoListAddEditScreen

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.TodoNavigatorScreen.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen(
                    onNavigate = { navController.navigate(it.route) },
                )
            }
            composable(
                route = Route.AddTask.route + "?id={id}",
                arguments = listOf(
                    navArgument(name = "id") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                TodoListAddEditScreen(onPopBackStack = {
                    navController.popBackStack()
                })
            }
        }
    }
}