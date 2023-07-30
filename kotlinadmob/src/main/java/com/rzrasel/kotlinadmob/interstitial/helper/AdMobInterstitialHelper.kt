package com.rzrasel.kotlinadmob.interstitial.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.rzrasel.kotlinadmob.interstitial.AdMobInterstitial

class AdMobInterstitialHelper private constructor(
    activity: Activity,
    private val context: Context,
    private val adUnitId: String,
    private val session: String? = null
) {
    private var adMobListener: AdMobListener? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AdMobInterstitialHelper? = null
        fun getInstance(
            activity: Activity,
            context: Context,
            adUnitId: String,
            session: String? = null
        ): AdMobInterstitialHelper {
            if (instance == null) {
                synchronized(AdMobInterstitialHelper::class.java) {
                    instance = AdMobInterstitialHelper(activity, context, adUnitId, session)
                }
            }
            return instance!!
        }
    }

    fun setAdMobHelperListener(adMobListener: AdMobListener): AdMobInterstitialHelper {
        this.adMobListener = adMobListener
        return this
    }

    private var runAdInterstitial: RunAdMobInterstitial =
        RunAdMobInterstitial(activity, context, adUnitId)

    /*suspend fun runAdMobHelper() {
        if(session != null) {
            //
        }
    }*/

    suspend fun runAdMobHelper(
        eventType: AdMobInterstitialTrigger.EventType,
        eventName: AdMobInterstitialTrigger.EventName
    ) {
        if (session != null) {
            runAdMobTrigger(session, eventType, eventName)
            return
        }
        runAdInterstitial.onLoadAd()
    }

    private suspend fun runAdMobTrigger(
        session: String,
        eventType: AdMobInterstitialTrigger.EventType,
        eventName: AdMobInterstitialTrigger.EventName
    ) {
        val runAdTrigger: RunAdMobTrigger = RunAdMobTrigger(context, session)
        runAdTrigger.setAdTriggerListener(eventType, eventName) {
            //println("DEBUG_LOG_PRINT: runAdMobTrigger")
            //showToast("Run AdMob Trigger")
            runAdInterstitial.onLoadAd()
        }
    }

    inner class RunAdMobInterstitial(activity: Activity, context: Context, adUnitId: String) {
        private var adMobInterstitial: AdMobInterstitial

        init {
            adMobInterstitial =
                AdMobInterstitial.getInstance(activity, context)
            adMobInterstitial.initAds()
                .setAdMobListener(object : AdMobInterstitial.AdMobListener {
                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        adMobInterstitial.show()
                        adMobListener?.onAdLoaded(interstitialAd)
                    }

                    override fun onAdDismissed() {
                        adMobListener?.onAdDismissed()
                    }

                    override fun onAdLoadFailed(adError: LoadAdError) {
                        adMobListener?.onAdLoadFailed(adError)
                    }
                })
        }

        fun onLoadAd() {
            adMobInterstitial.onLoadInterstitial(adUnitId)
        }
    }

    inner class RunAdMobTrigger(context: Context, session: String) {
        private val adMobTrigger: AdMobInterstitialTrigger =
            AdMobInterstitialTrigger.getInstance(context, session)

        /*private val adMobTriggerListener: AdMobInterstitialTrigger.AdTriggerListener = object :
            AdMobInterstitialTrigger.AdTriggerListener {
            override fun adTriggerReady() {
            }
        }*/

        suspend fun setAdTriggerListener(
            eventType: AdMobInterstitialTrigger.EventType,
            eventName: AdMobInterstitialTrigger.EventName,
            adTriggerListener: () -> Unit
        ) {
            runAdTrigger(eventType, eventName)
            adMobTrigger.setAdTriggerListener(object : AdMobInterstitialTrigger.AdTriggerListener {
                override fun adTriggerReady() {
                    adTriggerListener()
                }
            })
        }

        private suspend fun runAdTrigger(
            eventType: AdMobInterstitialTrigger.EventType,
            eventName: AdMobInterstitialTrigger.EventName
        ) {
            adMobTrigger.onTriggerEven(
                eventType,
                eventName
            )
        }
    }

    interface AdMobListener {
        fun onAdLoaded(interstitialAd: InterstitialAd)
        fun onAdDismissed()
        fun onAdLoadFailed(adError: LoadAdError)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}