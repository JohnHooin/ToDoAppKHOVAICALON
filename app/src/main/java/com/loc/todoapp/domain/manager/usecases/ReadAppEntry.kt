package com.loc.todoapp.domain.manager.usecases
import com.loc.todoapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (private val localUserManager: LocalUserManager
){
    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}