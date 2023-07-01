package com.rzrasel.kotlinadmob.interstitial.helper

import android.annotation.SuppressLint
import android.content.Context
import com.rzrasel.kotlinadmob.datastore.AdPreferences
import com.rzrasel.kotlinadmob.interstitial.model.AdPropertyModel
import com.rzrasel.kotlinadmob.utils.AdConstants

class AdMobInterstitialHelper private constructor(
    private val context: Context,
    private val session: String
) {
    private var adPropertyModel: AdPropertyModel
    private var adTriggerListener: AdTriggerListener? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AdMobInterstitialHelper? = null
        fun getInstance(context: Context, session: String): AdMobInterstitialHelper {
            if (instance == null) {
                synchronized(AdMobInterstitialHelper::class.java) {
                    instance = AdMobInterstitialHelper(context, session)
                }
            }
            return instance!!
        }
    }

    init {
        adPropertyModel = AdPropertyModel(
            session = session
        )
        adPropertyModel.init(context)
    }

    fun setAdTriggerListener(adTriggerListener: AdTriggerListener) {
        this.adTriggerListener = adTriggerListener
    }

    suspend fun onTriggerEven(eventName: String? = null, eventType: EventType) {
        //
        //adPropertyModel.lastEventName = eventName
        //
        /*adPropertyModel = AdPropertyModel.getProperty(context)
        adPropertyModel.lastEventName = eventName
        AdPropertyModel.setProperty(adPropertyModel)*/
        adPropertyModel.isAdPropertyStarting()
        val isAdTriggerReady = adPropertyModel.isAdTriggerReady()
        adTriggerListener?.let { itTriggerListener ->
            if (isAdTriggerReady) {
                itTriggerListener.adTriggerReady()
                return
            }
        }
        if (isAdTriggerReady) {
            return
        }
        adPropertyModel = getAdPropertyModel()
        adPropertyModel.lastEventName = eventName
        when (eventType) {
            EventType.CLICK_EVENT -> {
                adPropertyModel.clickEvent += 1
            }

            EventType.WINDOW_OPEN_EVENT -> {
                adPropertyModel.windowOpenEvent += 1
            }

            EventType.WINDOW_CLOSE_EVENT -> {
                adPropertyModel.windowCloseEvent += 1
            }
        }
        adPropertyModel.totalEvent += 1
        //adPropertyModel.totalEvent += 30
        setAdPropertyModel()
        println("DEBUG_LOG_PRINT: AdMobInterstitialHelper onTriggerEven ${adPropertyModel.toString()}")
    }

    private suspend fun getAdPropertyModel(): AdPropertyModel {
        return adPropertyModel.getProperty()
    }

    private suspend fun setAdPropertyModel() {
        adPropertyModel.setProperty()
    }

    public interface AdTriggerListener {
        fun adTriggerReady()
    }

    fun printAdPropertyModel() {
        println("DEBUG_LOG_PRINT: AdMobInterstitialHelper ${adPropertyModel.toString()}")
    }

    enum class EventType(slug: String) {
        CLICK_EVENT("click_event"),
        WINDOW_OPEN_EVENT("window_open_event"),
        WINDOW_CLOSE_EVENT("window_close_event");

        var slug: String? = slug
    }
}