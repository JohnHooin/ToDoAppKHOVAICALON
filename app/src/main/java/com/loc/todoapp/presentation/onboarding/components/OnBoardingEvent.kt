package com.loc.todoapp.presentation.onboarding.components

sealed class OnBoardingEvent{
    data object SaveAppEntry: OnBoardingEvent()
}