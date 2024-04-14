package com.loc.todoapp.presentation.nvgraph
sealed class Route(
    val route: String
) {
    data object OnBoardingScreen: Route(route = "onBoardingScreen")
    data object  HomeScreen: Route(route = "homeScreen")
    data object  TodoDetailScreen: Route(route = "todoDetailScreen")
    data object  SearchScreen: Route(route = "searchScreen")
    data object  SettingsScreen: Route(route = "settingsScreen")
    data object  AppStartNavigation: Route(route = "appStartNavigation")
    data object TodoNavigation: Route(route = "todoNavigation")
    data object TodoNavigatorScreen: Route(route = "todoNavigatorScreen")
}
