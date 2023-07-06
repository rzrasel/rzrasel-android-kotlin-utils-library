package com.rzrasel.kotlinadmob.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
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

class AdDataStore(context: Context) {
    private val prefContext = context.applicationContext

    //Instance of DataStore
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    companion object {
        //Preference Name
        private const val PREFERENCE_NAME = "user-data-store"
    }

    /*private suspend fun writeToDataStore(context:Context,value:String,key:String) = context.dataStore.edit {settings -> settings[stringPreferencesKey(key)] = value }

    private suspend fun readFromDataStore(context: Context, key: String) = context.dataStore.data.first()[stringPreferencesKey(key)]*/

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        prefContext.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):
            Flow<T> = prefContext.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val result = preferences[key] ?: defaultValue
        result
    }

    suspend fun writePreference(prefsDataSet: ArrayList<PrefsDataModel>) {
        try {
            prefContext.dataStore.edit { preferences ->
                //preferences[key] = value
                prefsDataSet.forEach {
                    when (it.dataType) {
                        DataType.BOOLEAN -> {
                            val key = booleanPreferencesKey(it.key)
                            preferences[key] = it.value.toString().toBoolean()
                        }

                        DataType.DOUBLE -> {
                            val key = doublePreferencesKey(it.key)
                            preferences[key] = it.value.toString().toDouble()
                        }

                        DataType.FLOAT -> {
                            val key = floatPreferencesKey(it.key)
                            preferences[key] = it.value.toString().toFloat()
                        }

                        DataType.INT -> {
                            val key = intPreferencesKey(it.key)
                            preferences[key] = it.value.toString().toInt()
                        }

                        DataType.LONG -> {
                            val key = longPreferencesKey(it.key)
                            preferences[key] = it.value.toString().toLong()
                        }

                        DataType.STRING -> {
                            val key = stringPreferencesKey(it.key)
                            preferences[key] = it.value.toString()
                        }

                        else -> {}
                    }
                }
            }
        } catch (ex: IOException) {
            throw ex
        } catch (ex: Exception) {
            throw ex
        }
    }

    suspend fun <T> readPreference(prefsDataSet: ArrayList<PrefsDataModel>):
            Flow<T> = prefContext.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        for(item in prefsDataSet) {
            when (item.dataType) {
                DataType.BOOLEAN -> {
                    val key = booleanPreferencesKey(item.key)
                    item.value = preferences[key].toString().toBoolean()
                }

                DataType.DOUBLE -> {
                    val key = doublePreferencesKey(item.key)
                    item.value = preferences[key].toString().toDouble()
                }

                DataType.FLOAT -> {
                    val key = floatPreferencesKey(item.key)
                    item.value = preferences[key].toString().toFloat()
                }

                DataType.INT -> {
                    val key = intPreferencesKey(item.key)
                    item.value = preferences[key].toString().toInt()
                }

                DataType.LONG -> {
                    val key = longPreferencesKey(item.key)
                    item.value = preferences[key].toString().toLong()
                }

                DataType.STRING -> {
                    val key = stringPreferencesKey(item.key)
                    item.value = preferences[key].toString()
                }

                else -> {}
            }
        }
        prefsDataSet as T
    }

    data class PrefsDataModel(
        var key: String,
        var value: Any?,
        var dataType: DataType,
        var defaultValue: Any? = null
    )

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
            DataType.BOOLEAN ->
                writeBoolean(key, value as Boolean)

            DataType.DOUBLE ->
                writeDouble(key, value as Double)

            DataType.INT ->
                writeInt(key, value as Int)

            DataType.LONG ->
                writeLong(key, value as Long)

            DataType.STRING ->
                writeString(key, value as String)

            else -> {}
        }
        //prefContext.dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
    }

    fun read(dataType: DataType, key: String): Flow<Any>? {
        when (dataType) {
            DataType.BOOLEAN ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[booleanPreferencesKey(key)] ?: false
                }*/
                return readBoolean(key)

            DataType.DOUBLE ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[doublePreferencesKey(key)] ?: 0.0
                }*/
                return readDouble(key)

            DataType.INT ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[intPreferencesKey(key)] ?: 0
                }*/
                return readInt(key)

            DataType.LONG ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[longPreferencesKey(key)] ?: 0L
                }*/
                return readLong(key)

            DataType.STRING ->
                /*return prefContext.dataStore.data.map { pref ->
                    pref[stringPreferencesKey(key)] ?: ""
                }*/
                return readString(key)

            else -> {
                return null
            }
        }
        /*return prefContext.dataStore.data.map { pref ->
            pref[booleanPreferencesKey(key)] ?: false
        }*/
    }

    private suspend fun readDataStore(dataType: DataType, key: String): Any? {
        when (dataType) {
            DataType.BOOLEAN ->
                return prefContext.dataStore.data.first()[booleanPreferencesKey(key)]

            DataType.DOUBLE ->
                return prefContext.dataStore.data.first()[doublePreferencesKey(key)]

            DataType.INT ->
                return prefContext.dataStore.data.first()[intPreferencesKey(key)]

            DataType.LONG ->
                return prefContext.dataStore.data.first()[longPreferencesKey(key)]

            DataType.STRING ->
                return prefContext.dataStore.data.first()[stringPreferencesKey(key)]

            else -> {
                return null
            }
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

    suspend fun remove(dataType: DataType, key: String) {
        prefContext.dataStore.edit {
            when (dataType) {
                DataType.BOOLEAN ->
                    if (it.contains(booleanPreferencesKey(key))) {
                        it.remove(booleanPreferencesKey(key))
                    }

                DataType.DOUBLE ->
                    if (it.contains(doublePreferencesKey(key))) {
                        it.remove(doublePreferencesKey(key))
                    }

                DataType.INT ->
                    if (it.contains(intPreferencesKey(key))) {
                        it.remove(intPreferencesKey(key))
                    }

                DataType.LONG ->
                    if (it.contains(longPreferencesKey(key))) {
                        it.remove(longPreferencesKey(key))
                    }

                DataType.STRING ->
                    if (it.contains(stringPreferencesKey(key))) {
                        it.remove(stringPreferencesKey(key))
                    }

                else -> {}
            }
        }
    }
    //private val USER_FIRST_NAME = stringPreferencesKey("user_first_name")

    enum class DataType(type: String) {
        BOOLEAN("bool"),
        DOUBLE("double"),
        FLOAT("float"),
        INT("int"),
        LONG("long"),
        STRING("string");
    }
    /*enum class DataType(type: String) {
        BOOL_KEY("bool"),
        DOUBLE_KEY("double"),
        INT_KEY("int"),
        LONG_KEY("long"),
        STRING_KEY("string");
    }*/

}
/*
https://medium.com/@chibichibi58/generic-way-to-use-android-data-store-preference-supported-for-all-data-types-ab97fd3022b6

val prefDataStore: AdDataStore = AdDataStore(this@HorizontalRecyclerActivity)
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
How to Use Jetpack Preferences DataStore
https://betterprogramming.pub/using-jetpack-preferences-datastore-more-effectively-414e1126cff7
https://medium.com/androiddevelopers/all-about-preferences-datastore-cc7995679334
https://medium.com/androiddevelopers/all-about-preferences-datastore-cc7995679334
https://androidgeek.co/how-to-use-datastore-preferences-in-kotlin-f1df16f17ac0
*/
/*

val prefsInDataSet: ArrayList<AdDataStore.PrefsDataModel> = ArrayList()
prefsInDataSet.add(
    AdDataStore.PrefsDataModel(
        "key_1",
        "value_1",
        AdDataStore.DataType.STRING
    )
)
prefsInDataSet.add(
    AdDataStore.PrefsDataModel(
        "key_2",
        "value_2",
        AdDataStore.DataType.STRING
    )
)
prefsInDataSet.add(
    AdDataStore.PrefsDataModel(
        "key_3",
        true,
        AdDataStore.DataType.BOOLEAN
    )
)
val prefDataStore: AdDataStore = AdDataStore(this)
lifecycleScope.launch {
    /=*prefDataStore.writeString("session_key", "ad session")
    prefDataStore.writeString("test_key", "test data in data store")*=/
    prefDataStore.writePreference(prefsInDataSet)
}
val prefsOutDataSet: ArrayList<AdDataStore.PrefsDataModel> = ArrayList()
prefsOutDataSet.add(
    AdDataStore.PrefsDataModel(
        "key_1",
        null,
        AdDataStore.DataType.STRING
    )
)
prefsOutDataSet.add(
    AdDataStore.PrefsDataModel(
        "key_2",
        null,
        AdDataStore.DataType.STRING
    )
)
prefsOutDataSet.add(
    AdDataStore.PrefsDataModel(
        "key_3",
        null,
        AdDataStore.DataType.BOOLEAN
    )
)
lifecycleScope.launch {
    /=*prefDataStore.readString("session_key").collect {
        println("DEBUG_LOG_PRINT: prefDataStore session $it")
    }
    prefDataStore.readString("test_key").collect {
        println("DEBUG_LOG_PRINT: prefDataStore profData $it")
    }*=/
    prefDataStore.readPreference<ArrayList<AdDataStore.PrefsDataModel>>(prefsOutDataSet)
        .collect {
            println("DEBUG_LOG_PRINT: prefDataStore 149 $it")
        }
}

*/