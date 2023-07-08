package com.rzrasel.kotlinadmob.interstitial.helper

import android.annotation.SuppressLint
import android.content.Context
import com.rzrasel.kotlinadmob.interstitial.model.AdPropertyModel

class AdMobInterstitialTrigger private constructor(
    private val context: Context,
    private val session: String
) {
    private var adPropertyModel: AdPropertyModel
    private var adTriggerListener: AdTriggerListener? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AdMobInterstitialTrigger? = null
        fun getInstance(context: Context, session: String): AdMobInterstitialTrigger {
            if (instance == null) {
                synchronized(AdMobInterstitialTrigger::class.java) {
                    instance = AdMobInterstitialTrigger(context, session)
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

    suspend fun onTriggerEven(eventType: EventType, eventName: EventName) {
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
                //printAdPropertyModel()
                return
            }
        }
        if (isAdTriggerReady) {
            return
        }
        adPropertyModel = getAdPropertyModel()
        adPropertyModel.lastEventName = eventName.slug
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

    interface AdTriggerListener {
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
    enum class EventName(slug: String) {
        BUTTON_CLICK_EVENT("button_click_event"),
        ACTIVITY_CREATE_EVENT("window_activity_open_event"),
        ACTIVITY_PAUSE_EVENT("window_activity_pause_event"),
        ACTIVITY_RESUME_EVENT("window_activity_resume_event"),
        ACTIVITY_DESTROY_EVENT("window_activity_destroy_event");

        var slug: String? = slug
    }
}

/*
private fun interstitialAdHelper() {
    val adInterstitialTrigger: AdMobInterstitialTrigger =
        AdMobInterstitialTrigger.getInstance(context, "DashboardActivity")
    adInterstitialTrigger.setAdTriggerListener(object :
        AdMobInterstitialTrigger.AdTriggerListener {
        override fun adTriggerReady() {
            println("DEBUG_LOG_PRINT: DashboardActivity adTriggerReady")
        }
    })
    //adInterstitialHelper.printAdPropertyModel()
    lifecycleScope.launch {
        adInterstitialTrigger.onTriggerEven(
            AdMobInterstitialTrigger.EventType.WINDOW_OPEN_EVENT,
            AdMobInterstitialTrigger.EventName.ACTIVITY_CREATE_EVENT
        )
    }
}
*/
