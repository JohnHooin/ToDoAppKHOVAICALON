package com.loc.todoapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.todoapp.domain.manager.LocalUserManager
import com.loc.todoapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * This class is responsible for managing the user's local data.
 */
class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    // Save the app entry status to the data store.
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[PreferencesKeys.APP_ENTRY] ?: false
        }
    }

}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}