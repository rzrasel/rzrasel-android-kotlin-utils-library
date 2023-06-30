package com.rzrasel.kotlinadmob.interstitial.model

import android.content.Context
import com.rzrasel.kotlinadmob.datastore.AdPreferences
import com.rzrasel.kotlinadmob.utils.AdConstants

internal data class AdPropertyModel(
    var nextTime: Long = 0L,
    var lastTime: Long = 0L,
    var timeDuration: Long = AdConstants.AdMob.getTimeDuration(),
    //
    var clickEvent: Int = 0,
    var windowOpenEvent: Int = 0,
    var windowCloseEvent: Int = 0,
    var totalEvent: Int = 0,
    var targetTotalEvent: Int = AdConstants.AdMob.getTargetTotalEvent(),
    //
    var session: String? = "session_name",
    var lastEventName: String? = "last_event_name"
) {
    private lateinit var context: Context
    private lateinit var sharedPrefers: AdPreferences

    fun init(context: Context) {
        this.context = context
        sharedPrefers = AdPreferences.getInstance(context)
    }

    companion object {
        suspend fun getProperty(context: Context): AdPropertyModel {
            val propertyModel: AdPropertyModel = AdPropertyModel()
            propertyModel.init(context)
            propertyModel.sharedPrefers.let {
                it.apply {
                    propertyModel.nextTime = readLong(PreferenceKey.nextTime)
                    propertyModel.lastTime = readLong(PreferenceKey.lastTime)
                    propertyModel.timeDuration = readLong(PreferenceKey.timeDuration)
                    //
                    propertyModel.clickEvent = readInt(PreferenceKey.clickEvent)
                    propertyModel.windowOpenEvent = readInt(PreferenceKey.windowOpenEvent)
                    propertyModel.windowCloseEvent = readInt(PreferenceKey.windowCloseEvent)
                    propertyModel.totalEvent = readInt(PreferenceKey.totalEvent)
                    propertyModel.targetTotalEvent = readInt(PreferenceKey.targetTotalEvent)
                    //
                    propertyModel.session = readString(PreferenceKey.session)
                    propertyModel.lastEventName = readString(PreferenceKey.lastEventName)
                }
            }
            return propertyModel
        }

        suspend fun setProperty(adPropertyModel: AdPropertyModel) {
            adPropertyModel.sharedPrefers.let {
                it.apply {
                    writeLong(PreferenceKey.nextTime, adPropertyModel.nextTime)
                    writeLong(PreferenceKey.lastTime, adPropertyModel.lastTime)
                    writeLong(PreferenceKey.timeDuration, adPropertyModel.timeDuration)
                    //
                    writeInt(PreferenceKey.clickEvent, adPropertyModel.clickEvent)
                    writeInt(PreferenceKey.windowOpenEvent, adPropertyModel.windowOpenEvent)
                    writeInt(PreferenceKey.windowCloseEvent, adPropertyModel.windowCloseEvent)
                    writeInt(PreferenceKey.totalEvent, adPropertyModel.totalEvent)
                    writeInt(PreferenceKey.targetTotalEvent, adPropertyModel.targetTotalEvent)
                    //
                    writeString(PreferenceKey.session, adPropertyModel.session)
                    writeString(PreferenceKey.lastEventName, adPropertyModel.lastEventName)
                }
            }
        }
    }

    suspend fun getProperty(): AdPropertyModel {
        sharedPrefers.apply {
            nextTime = readLong(PreferenceKey.nextTime)
            lastTime = readLong(PreferenceKey.lastTime)
            timeDuration = readLong(PreferenceKey.timeDuration)
            //
            clickEvent = readInt(PreferenceKey.clickEvent)
            windowOpenEvent = readInt(PreferenceKey.windowOpenEvent)
            windowCloseEvent = readInt(PreferenceKey.windowCloseEvent)
            totalEvent = readInt(PreferenceKey.totalEvent)
            targetTotalEvent = readInt(PreferenceKey.targetTotalEvent)
            //
            session = readString(PreferenceKey.session)
            lastEventName = readString(PreferenceKey.lastEventName)
        }
        return this
    }

    //suspend fun setProperty(adPropertyModel: AdPropertyModel) {
    suspend fun setProperty() {
        sharedPrefers.apply {
            writeLong(PreferenceKey.nextTime, nextTime)
            writeLong(PreferenceKey.lastTime, lastTime)
            writeLong(PreferenceKey.timeDuration, timeDuration)
            //
            writeInt(PreferenceKey.clickEvent, clickEvent)
            writeInt(PreferenceKey.windowOpenEvent, windowOpenEvent)
            writeInt(PreferenceKey.windowCloseEvent, windowCloseEvent)
            writeInt(PreferenceKey.totalEvent, totalEvent)
            writeInt(PreferenceKey.targetTotalEvent, targetTotalEvent)
            //
            writeString(PreferenceKey.session, session)
            writeString(PreferenceKey.lastEventName, lastEventName)
        }
    }

    private suspend fun setTriggerNextTime(nextTime: Long) {
        sharedPrefers.apply {
            val prefsNextTime: Long = readLong(PreferenceKey.nextTime)
            writeLong(PreferenceKey.nextTime, nextTime)
            writeLong(PreferenceKey.lastTime, prefsNextTime)
        }
    }

    private suspend fun resetTriggerProperty() {
        val timeDuration = AdConstants.AdMob.getTimeDuration()
        val nextTime = System.currentTimeMillis() + timeDuration
        val targetEvent = AdConstants.AdMob.getTargetTotalEvent()
        sharedPrefers.apply {
            writeLong(PreferenceKey.timeDuration, timeDuration)
            writeInt(PreferenceKey.clickEvent, 0)
            writeInt(PreferenceKey.windowOpenEvent, 0)
            writeInt(PreferenceKey.windowCloseEvent, 0)
            writeInt(PreferenceKey.totalEvent, 0)
            writeInt(PreferenceKey.targetTotalEvent, targetEvent)
        }
        setTriggerNextTime(nextTime)
    }

    suspend fun isAdPropertyStarting(): Boolean {
        val nextTime = sharedPrefers.readLong(PreferenceKey.nextTime)
        if (nextTime > 0) {
            //resetTriggerProperty()
            return false
        }
        resetTriggerProperty()
        return true
    }

    suspend fun isAdTriggerReady(): Boolean {
        val isTimeOver = isTriggerTimeOver()
        val isEventOver = isTriggerEventOver()
        if (isTimeOver && isEventOver) {
            resetTriggerProperty()
            return true
        }
        return false
    }

    private suspend fun isTriggerTimeOver(): Boolean {
        val nextTime = sharedPrefers.readLong(PreferenceKey.nextTime)
        //val targetTotalEvent = sharedPrefers.readInt(PreferenceKey.targetTotalEvent)
        if (System.currentTimeMillis() >= nextTime) {
            return true
        }
        return false
    }

    private suspend fun isTriggerEventOver(): Boolean {
        val totalEvent = sharedPrefers.readInt(PreferenceKey.totalEvent)
        val targetTotalEvent = sharedPrefers.readInt(PreferenceKey.targetTotalEvent)
        if (totalEvent >= targetTotalEvent) {
            return true
        }
        return false
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
