package com.loc.todoapp.domain.manager.usecases

import com.loc.todoapp.domain.manager.LocalUserManager

class SaveAppEntry (

    // Let this depend on the top-level interface instead of the concrete implementation.
    // This way, we make the code more flexible and easier to test. By using
    // pattern dependency injection, we can easily switch the implementation of the interface.
    private val localUserManager: LocalUserManager
){
    suspend operator fun invoke() = localUserManager.saveAppEntry()
}