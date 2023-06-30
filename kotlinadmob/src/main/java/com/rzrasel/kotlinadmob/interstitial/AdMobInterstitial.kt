package com.rzrasel.kotlinadmob.interstitial

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdMobInterstitial private constructor(
    private var activity: Activity,
    private var context: Context
) {
    private var interstitialAd: InterstitialAd? = null
    private var adMobListener: AdMobListener? = null

    //private constructor()

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AdMobInterstitial? = null

        /*@Synchronized
        fun getInstance(): AdMobInterstitial {
            if (instance == null) {
                synchronized(AdMobInterstitial::class.java) {
                    instance = AdMobInterstitial()
                }
            }
            return instance!!
        }*/
        fun getInstance(activity: Activity, context: Context): AdMobInterstitial =
            instance ?: synchronized(this) {
                val newInstance =
                    instance ?: AdMobInterstitial(activity, context).also { instance = it }
                newInstance
            }
    }

    fun setAdMobListener(adMobListener: AdMobListener): AdMobInterstitial {
        this.adMobListener = adMobListener
        return this
    }

    fun initAds(): AdMobInterstitial {
        MobileAds.setRequestConfiguration(getConfiguration())
        MobileAds.initialize(context) {}
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("ABCDEF012345"))
                .build()
        )
        return this
    }

    fun show(): AdMobInterstitial {
        interstitialAd.let { itInterstitialAd ->
            itInterstitialAd?.show(activity)
        } ?: run {
            //println("DEBUG_LOG_PRINT: The interstitial ad wasn't ready yet")
        }
        return this
    }

    fun onLoadInterstitial(adUnitId: String): AdMobInterstitial {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(argInterstitialAd: InterstitialAd) {
                //println("DEBUG_LOG_PRINT: The interstitial ad loaded")
                interstitialAd = argInterstitialAd
                interstitialAd?.fullScreenContentCallback = fullScreenContentCallback
                //showInterstitialAd()
                //adMobState = 1
                //onStartTimer(DELAY_MILLISECONDS)
                //countDownTimer?.start()
                //show()
                adMobListener?.onAdLoaded(interstitialAd!!)
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                //println("DEBUG_LOG_PRINT: Interstitial onAdFailedToLoad ${adError.message}")
                //var errorMessage = "RzAdMob Error: ${adError.toString()} ${adError.message} ${adError.code}"
                interstitialAd = null
                //finish()
                adMobListener?.onAdLoadFailed(adError)
            }
        })
        return this
    }

    private fun getConfiguration(): RequestConfiguration {
        return RequestConfiguration.Builder()
            .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
            .build()
    }

    private val fullScreenContentCallback = object : FullScreenContentCallback() {

        override fun onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent()
            //println("DEBUG_LOG_PRINT: Interstitial onAdShowedFullScreenContent")
        }

        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            interstitialAd = null
            //println("DEBUG_LOG_PRINT: Interstitial onAdDismissedFullScreenContent")
            /*adMobState = 2
            onStartTimer(2000)*/
            //countDownTimer?.start()
            adMobListener?.onAdDismissed()
        }

        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
            super.onAdFailedToShowFullScreenContent(adError)
            //Log.d(TAG, "RzAdMob Ad failed to show. Error: ${adError.message}")
            //println("DEBUG_LOG_PRINT: Interstitial onAdFailedToShowFullScreenContent ${adError.message}")
        }

    }

    interface AdMobListener {
        fun onAdLoaded(interstitialAd: InterstitialAd)
        fun onAdDismissed()
        fun onAdLoadFailed(adError: LoadAdError)
    }
}