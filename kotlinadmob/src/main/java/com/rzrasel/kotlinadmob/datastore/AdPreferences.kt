package com.rzrasel.kotlinadmob.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi


class AdPreferences private constructor(context: Context) {
    private val sharedPrefs: SharedPreferences
    private lateinit var prefsKey: String
    private var prefsMode: Int = 0
    private val DEFAULT_SUFFIX = "_preferences"
    private val LENGTH = "#LENGTH"

    /*init {
        context = builder.context
        prefsKey = builder.prefsKey
        prefsMode = builder.prefsMode
        sharedPrefs = context.getSharedPreferences(prefsKey, prefsMode)
        //val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }*/
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AdPreferences? = null
        fun getInstance(context: Context): AdPreferences {
            if (instance == null) {
                synchronized(AdPreferences::class.java) {
                    instance = AdPreferences(context)
                }
            }
            return instance!!
        }
    }

    init {
        prefsKey = context.packageName
        prefsMode = Mode.DEFAULT.mode
        sharedPrefs = context.getSharedPreferences(prefsKey, prefsMode)
        //val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    suspend fun writeString(key: String, value: String? = null) {
        with(sharedPrefs.edit()) {
            putString(key, value)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                apply()
            } else {
                commit()
            }
        }
    }

    suspend fun readString(key: String, defValue: String? = null): String? {
        return sharedPrefs.getString(key, defValue)
    }

    suspend fun writeInt(key: String, value: Int = 0) {
        with(sharedPrefs.edit()) {
            putInt(key, value)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                apply()
            } else {
                commit()
            }
        }
    }

    suspend fun readInt(key: String, defValue: Int = 0): Int {
        return sharedPrefs.getInt(key, defValue)
    }

    suspend fun writeLong(key: String, value: Long = 0L) {
        with(sharedPrefs.edit()) {
            putLong(key, value)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                apply()
            } else {
                commit()
            }
        }
    }

    suspend fun readLong(key: String, defValue: Long = 0L): Long {
        return sharedPrefs.getLong(key, defValue)
    }

    suspend fun writeFloat(key: String, value: Float = 0F) {
        with(sharedPrefs.edit()) {
            putFloat(key, value)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                apply()
            } else {
                commit()
            }
        }
    }

    suspend fun readFloat(key: String, defValue: Float = 0F): Float {
        return sharedPrefs.getFloat(key, defValue)
    }

    suspend fun writeBoolean(key: String, value: Boolean = false) {
        with(sharedPrefs.edit()) {
            putBoolean(key, value)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                apply()
            } else {
                commit()
            }
        }
    }

    suspend fun readBoolean(key: String, defValue: Boolean = false): Boolean {
        return sharedPrefs.getBoolean(key, defValue)
    }

    /*class Builder {
        internal lateinit var context: Context
        internal lateinit var prefsKey: String
        internal var prefsMode = -1
        internal var prefsUseDefault = false

        fun withPrefsName(prefsName: String): Builder {
            this.prefsKey = prefsName
            return this
        }

        fun build(context: Context): AdPreferences {
            this.context = context
            prefsMode = Mode.DEFAULT.mode
            prefsKey = context.packageName
            return AdPreferences(this)
        }
    }*/

    public enum class Mode(mode: Int) {
        PRIVATE(Context.MODE_PRIVATE),

        /*@SuppressLint("WorldReadableFiles")
        WORLD_READABLE(Context.MODE_WORLD_READABLE),

        @SuppressLint("WorldWriteableFiles")
        WORLD_WRITEABLE(Context.MODE_WORLD_WRITEABLE),

        @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
        MULTI_PROCESS(Context.MODE_MULTI_PROCESS),*/
        DEFAULT(ContextWrapper.MODE_PRIVATE);

        val mode: Int = mode
    }
}
//https://github.com/rzrasel/rz-android-sdk-git-java
//https://github.com/rzrasel/rz-android-sdk-git-java/blob/master/package-target-sdk-v33/rzandjavagit-propreferences/src/main/java/com/rzandjavagit/propreferences/ProPreferences.java