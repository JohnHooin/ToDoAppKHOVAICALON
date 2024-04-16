package com.loc.todoapp.presentation.nvgraph
sealed class Route(
    val route: String
) {
    data object OnBoardingScreen: Route(route = "onBoardingScreen")
    data object TodoNavigatorScreen: Route(route = "todoNavigatorScreen")
    data object HomeScreen: Route(route = "homeScreen")
    data object AppStartNavigation: Route(route = "appStartNavigation")
    data object AddTask: Route (route = "taskAdd")
}
