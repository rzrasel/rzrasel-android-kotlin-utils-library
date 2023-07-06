package com.rzrasel.kotlindatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException

class ProPrefDataStore(context: Context) {
    private val prefContext = context.applicationContext

    //Instance of DataStore
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    companion object {
        //Preference Name
        private const val PREFERENCE_NAME = "user-data-store"
    }

    /*private suspend fun writeToDataStore(context:Context,value:String,key:String) = context.dataStore.edit {settings -> settings[stringPreferencesKey(key)] = value }

    private suspend fun readFromDataStore(context: Context, key: String) = context.dataStore.data.first()[stringPreferencesKey(key)]*/

    /**
     * Add string data to data Store
     */
    suspend fun Context.writeString(key: String, value: String) {
        //appContext.dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
        try {
            prefContext.dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
            // If you got here, the preferences were successfully committed
        } catch (e: IOException) {
            // Handle error writing preferences to disk
        } catch (e: Exception) {
            // Handle error thrown while executing transform block
        }
    }


    /**
     * Read string from the data store preferences
     */
    fun Context.readString(key: String): Flow<String> {
        /*return appContext.dataStore.data.map { pref ->
            pref[stringPreferencesKey(key)] ?: ""
        }*/
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { pref ->
                pref[stringPreferencesKey(key)] ?: ""
            }
    }

    suspend fun writeString(key: String, value: String) {
        //appContext.dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
        try {
            prefContext.dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
            // If you got here, the preferences were successfully committed
        } catch (e: IOException) {
            // Handle error writing preferences to disk
        } catch (e: Exception) {
            // Handle error thrown while executing transform block
        }
    }

    /*fun readString(key: String): Flow<String> = prefContext.dataStore.data.map { preferences->
        preferences[stringPreferencesKey(key)] ?: ""
    }*/

    //
    fun readString(key: String): Flow<String> {
        /*return appContext.dataStore.data.map { pref ->
            pref[stringPreferencesKey(key)] ?: ""
        }*/
        return prefContext.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { pref ->
                //val prefData = preferences[stringPreferencesKey(key)]
                //println("DEBUG_LOG_PRINT: readString prefData $prefData")
                pref[stringPreferencesKey(key)] ?: ""
            }
    }

    /**
     * Add Integer to the data store
     */
    suspend fun Context.writeInt(key: String, value: Int) {
        prefContext.dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
    }

    /**
     * Reading the Int value from the data store
     */
    fun Context.readInt(key: String): Flow<Int> {
        return prefContext.dataStore.data.map { pref ->
            pref[intPreferencesKey(key)] ?: 0
        }
    }

    suspend fun writeInt(key: String, value: Int) {
        prefContext.dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
    }

    fun readInt(key: String): Flow<Int> {
        return prefContext.dataStore.data.map { pref ->
            pref[intPreferencesKey(key)] ?: 0
        }
    }

    /**
     * Adding Double to the data store
     */
    suspend fun Context.writeDouble(key: String, value: Double) {
        prefContext.dataStore.edit { pref -> pref[doublePreferencesKey(key)] = value }
    }

    /**
     * Reading the double value from the data store
     */
    fun Context.readDouble(key: String): Flow<Double> {
        return prefContext.dataStore.data.map { pref ->
            pref[doublePreferencesKey(key)] ?: 0.0
        }
    }

    suspend fun writeDouble(key: String, value: Double) {
        prefContext.dataStore.edit { pref -> pref[doublePreferencesKey(key)] = value }
    }

    fun readDouble(key: String): Flow<Double> {
        return prefContext.dataStore.data.map { pref ->
            pref[doublePreferencesKey(key)] ?: 0.0
        }
    }

    /**
     * Add Long to the data store
     */
    suspend fun Context.writeLong(key: String, value: Long) {
        prefContext.dataStore.edit { pref -> pref[longPreferencesKey(key)] = value }
    }

    /**
     * Reading the long from the data store
     */
    fun Context.readLong(key: String): Flow<Long> {
        return prefContext.dataStore.data.map { pref ->
            pref[longPreferencesKey(key)] ?: 0L
        }
    }

    suspend fun writeLong(key: String, value: Long) {
        prefContext.dataStore.edit { pref -> pref[longPreferencesKey(key)] = value }
    }

    fun readLong(key: String): Flow<Long> {
        return prefContext.dataStore.data.map { pref ->
            pref[longPreferencesKey(key)] ?: 0L
        }
    }


    /**
     * Add Boolean to the data store
     */
    suspend fun Context.writeBool(key: String, value: Boolean) {
        prefContext.dataStore.edit { pref -> pref[booleanPreferencesKey(key)] = value }
    }

    /**
     * Reading the Boolean from the data store
     */
    fun Context.readBoolean(key: String): Flow<Boolean> {
        return prefContext.dataStore.data.map { pref ->
            pref[booleanPreferencesKey(key)] ?: false
        }
    }

    suspend fun writeBoolean(key: String, value: Boolean) {
        prefContext.dataStore.edit { pref -> pref[booleanPreferencesKey(key)] = value }
    }

    fun readBoolean(key: String): Flow<Boolean> {
        return prefContext.dataStore.data.map { pref ->
            pref[booleanPreferencesKey(key)] ?: false
        }
    }

    //
    //
    //
    //
    suspend fun <T> write(dataType: DataType, key: String, value: T) {
        when (dataType) {
            DataType.BOOL_KEY ->
                writeBoolean(key, value as Boolean)

            DataType.DOUBLE_KEY ->
                writeDouble(key, value as Double)

            DataType.INT_KEY ->
                writeInt(key, value as Int)

            DataType.LONG_KEY ->
                writeLong(key, value as Long)

            DataType.STRING_KEY ->
                writeString(key, value as String)
        }
        //prefContext.dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
    }

    fun read(dataType: DataType, key: String): Flow<Any> {
        when (dataType) {
            DataType.BOOL_KEY ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[booleanPreferencesKey(key)] ?: false
                }*/
                return readBoolean(key)

            DataType.DOUBLE_KEY ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[doublePreferencesKey(key)] ?: 0.0
                }*/
                return readDouble(key)

            DataType.INT_KEY ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[intPreferencesKey(key)] ?: 0
                }*/
                return readInt(key)

            DataType.LONG_KEY ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[longPreferencesKey(key)] ?: 0L
                }*/
                return readLong(key)

            DataType.STRING_KEY ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[stringPreferencesKey(key)] ?: ""
                }*/
                return readString(key)
        }
        /*return prefContext.dataStore.data.map { pref ->
            pref[booleanPreferencesKey(key)] ?: false
        }*/
    }

    private suspend fun readDataStore(dataType: DataType, key: String): Any? {
        when (dataType) {
            DataType.BOOL_KEY ->
                return prefContext.dataStore.data.first()[booleanPreferencesKey(key)]

            DataType.DOUBLE_KEY ->
                return prefContext.dataStore.data.first()[doublePreferencesKey(key)]

            DataType.INT_KEY ->
                return prefContext.dataStore.data.first()[intPreferencesKey(key)]

            DataType.LONG_KEY ->
                return prefContext.dataStore.data.first()[longPreferencesKey(key)]

            DataType.STRING_KEY ->
                return prefContext.dataStore.data.first()[stringPreferencesKey(key)]
        }
    }

    //
    //
    //
    //
    suspend fun Context.readAllKeys(): Set<Preferences.Key<*>>? {
        val keys = prefContext.dataStore.data
            .map {
                it.asMap().keys
            }
        return keys.firstOrNull()
    }

    suspend fun Context.getValueByKey(key: Preferences.Key<*>): Any? {
        val value = prefContext.dataStore.data
            .map {
                it[key]
            }
        return value.firstOrNull()
    }

    suspend fun readAllKeys(): Set<Preferences.Key<*>>? {
        val keys = prefContext.dataStore.data
            .map {
                it.asMap().keys
            }
        return keys.firstOrNull()
    }

    suspend fun getValueByKey(key: Preferences.Key<*>): Any? {
        val value = prefContext.dataStore.data
            .map {
                it[key]
            }
        return value.firstOrNull()
    }

    suspend fun removeAll() {
        prefContext.dataStore.edit {
            it.clear()
        }
    }

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        prefContext.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun remove(dataType: DataType, key: String) {
        prefContext.dataStore.edit {
            when (dataType) {
                DataType.BOOL_KEY ->
                    if (it.contains(booleanPreferencesKey(key))) {
                        it.remove(booleanPreferencesKey(key))
                    }

                DataType.DOUBLE_KEY ->
                    if (it.contains(doublePreferencesKey(key))) {
                        it.remove(doublePreferencesKey(key))
                    }

                DataType.INT_KEY ->
                    if (it.contains(intPreferencesKey(key))) {
                        it.remove(intPreferencesKey(key))
                    }

                DataType.LONG_KEY ->
                    if (it.contains(longPreferencesKey(key))) {
                        it.remove(longPreferencesKey(key))
                    }

                DataType.STRING_KEY ->
                    if (it.contains(stringPreferencesKey(key))) {
                        it.remove(stringPreferencesKey(key))
                    }
            }
        }
    }
    //private val USER_FIRST_NAME = stringPreferencesKey("user_first_name")

    enum class DataType(prefKey: String) {
        BOOL_KEY("bool"),
        DOUBLE_KEY("double"),
        INT_KEY("int"),
        LONG_KEY("long"),
        STRING_KEY("string");
    }

}
/*
val prefDataStore: ProPrefDataStore = ProPrefDataStore(this@HorizontalRecyclerActivity)
lifecycleScope.launch {
    prefDataStore.writeString("test_key", "test data in data store")
}
//
lifecycleScope.launch {
    prefDataStore.readString("test_key").collect {
        println("DEBUG_LOG_PRINT: prefDataStore profData $it")
    }
}


class MainViewModel(val appContext: Application) : AndroidViewModel(appContext) {

    companion object{
        const val NAME_USER_KEY = "name"
    }

    //Save the name of logged in user
    fun saveNameUser(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeString(NAME_USER_KEY, name)
        }
    }

    //Getting the name of saved user
    val getUserName = appContext.readString(NAME_USER_KEY).asLiveData()
}

private object PreferencesKeys {
    val SORT_ORDER = stringPreferencesKey("sort_order")
    val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
}
*/
/*
https://medium.com/@chibichibi58/generic-way-to-use-android-data-store-preference-supported-for-all-data-types-ab97fd3022b6

How to Use Jetpack Preferences DataStore
https://betterprogramming.pub/using-jetpack-preferences-datastore-more-effectively-414e1126cff7
https://medium.com/androiddevelopers/all-about-preferences-datastore-cc7995679334
https://medium.com/androiddevelopers/all-about-preferences-datastore-cc7995679334
https://androidgeek.co/how-to-use-datastore-preferences-in-kotlin-f1df16f17ac0
*/
