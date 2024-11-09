package com.seminario.bovintrack.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenPreference @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
    }

    val tokenflow: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
            .catch {
                emit(null)
            }

    suspend fun saveToken(token: String){
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }
    suspend fun deleteToken(){
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}