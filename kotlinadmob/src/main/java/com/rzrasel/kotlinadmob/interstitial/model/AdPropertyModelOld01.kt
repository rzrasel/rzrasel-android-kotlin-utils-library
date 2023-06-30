package com.rzrasel.kotlinadmob.interstitial.model

import android.content.Context
import com.rzrasel.kotlinadmob.datastore.AdDataStore
import com.rzrasel.kotlinadmob.utils.AdConstants

//internal data class AdPropertyModel(
data class AdPropertyModelOld01(
    var nextTime: Long = System.currentTimeMillis(),
    var lastTime: Long = System.currentTimeMillis(),
    var timeDuration: Long = AdConstants.AdMob.getTimeDuration(),
    var clickEvent: Int = 0,
    var windowOpenEvent: Int = 0,
    var windowCloseEvent: Int = 0,
    var totalEvent: Int = 0,
    var targetTotalEvent: Int = AdConstants.AdMob.getTargetTotalEvent(),
    var session: String = "session_name",
    var lastEventName: String = "last_event_name"
) {
    private lateinit var context: Context
    private lateinit var prefDataStore: AdDataStore
    fun init(context: Context) {
        this.context = context
        prefDataStore = AdDataStore(context)
    }

    suspend fun getProperty(): AdPropertyModelOld01 {
        prefDataStore.let { itPrefDataStore ->
            itPrefDataStore.apply {
                readLong(PreferenceKey.nextTime).collect {
                    println("DEBUG_LOG_PRINT: getProperty nextTime $it")
                    nextTime = it
                }
                readLong(PreferenceKey.lastTime).collect {
                    println("DEBUG_LOG_PRINT: getProperty lastTime $it")
                    lastTime = it
                }
                readLong(PreferenceKey.timeDuration).collect {
                    println("DEBUG_LOG_PRINT: getProperty timeDuration $it")
                    timeDuration = it
                }
                readInt(PreferenceKey.clickEvent).collect {
                    println("DEBUG_LOG_PRINT: getProperty clickEvent $it")
                    clickEvent = it
                }
                readInt(PreferenceKey.windowOpenEvent).collect {
                    println("DEBUG_LOG_PRINT: getProperty windowOpenEvent $it")
                    windowOpenEvent = it
                }
                readInt(PreferenceKey.windowCloseEvent).collect {
                    println("DEBUG_LOG_PRINT: getProperty windowCloseEvent $it")
                    windowCloseEvent = it
                }
                readInt(PreferenceKey.totalEvent).collect {
                    println("DEBUG_LOG_PRINT: getProperty totalEvent $it")
                    totalEvent = it
                }
                readInt(PreferenceKey.targetTotalEvent).collect {
                    println("DEBUG_LOG_PRINT: getProperty targetTotalEvent $it")
                    targetTotalEvent = it
                }
                readString(PreferenceKey.session).collect {
                    println("DEBUG_LOG_PRINT: getProperty session $it")
                    session = it
                }
                readString(PreferenceKey.lastEventName).collect {
                    println("DEBUG_LOG_PRINT: getProperty lastEventName $it")
                    lastEventName = it
                }
            }
        }
        return this
    }

    suspend fun setProperty() {
        prefDataStore.let { itPrefDataStore ->
            itPrefDataStore.apply {
                writeLong(PreferenceKey.nextTime, nextTime)
                writeLong(PreferenceKey.lastTime, lastTime)
                writeLong(PreferenceKey.timeDuration, timeDuration)
                writeInt(PreferenceKey.clickEvent, clickEvent)
                writeInt(PreferenceKey.windowOpenEvent, windowOpenEvent)
                writeInt(PreferenceKey.windowCloseEvent, windowCloseEvent)
                writeInt(PreferenceKey.totalEvent, totalEvent)
                writeInt(PreferenceKey.targetTotalEvent, targetTotalEvent)
                writeString(PreferenceKey.session, session)
                writeString(PreferenceKey.lastEventName, lastEventName)
            }
        }
    }

    object PreferenceKey {
        const val nextTime: String = "ad_pref_key_next_time"
        const val lastTime: String = "ad_pref_key_last_time"
        const val timeDuration: String = "ad_pref_key_time_duration"
        const val clickEvent: String = "ad_pref_key_click_event"
        const val windowOpenEvent: String = "ad_pref_key_window_open_event"
        const val windowCloseEvent: String = "ad_pref_key_window_close_event"
        const val totalEvent: String = "ad_pref_key_total_event"
        const val targetTotalEvent: String = "ad_pref_key_target_total_event"
        const val session: String = "ad_pref_key_session_name"
        const val lastEventName: String = "ad_pref_key_last_event_name"
    }

    override fun toString(): String {
        super.toString()
        return "AdPropertyModel:" +
                " nextTime: $nextTime" +
                ", lastTime: $lastTime" +
                ", timeDuration: $timeDuration" +
                ", clickEvent: $clickEvent" +
                ", windowOpenEvent: $windowOpenEvent" +
                ", windowCloseEvent: $windowCloseEvent" +
                ", totalEvent: $totalEvent" +
                ", targetTotalEvent: $targetTotalEvent" +
                ", session: $session" +
                ", lastEventName: $lastEventName"
    }
}
