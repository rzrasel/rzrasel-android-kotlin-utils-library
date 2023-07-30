package com.rzrasel.kotlinutils.internet

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.core.content.ContextCompat


final class InternetConnection {
    companion object {
        private var instance: InternetConnection? = null
        fun getInstance(): InternetConnection =
            instance ?: synchronized(this) {
                val newInstance =
                    instance ?: InternetConnection().also { instance = it }
                newInstance
            }

        fun isNetConnected(context: Context): Boolean {
            var hasPermission = 0
            hasPermission = ContextCompat.checkSelfPermission(
                (context as Activity?)!!,
                Manifest.permission.INTERNET
            )
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                //LogWriter.Log("Check it", "Please set the permission INTERNET");
                return false
            }
            hasPermission = ContextCompat.checkSelfPermission(
                (context as Activity?)!!,
                Manifest.permission.ACCESS_NETWORK_STATE
            )
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                //LogWriter.Log("Check it", "Please set the permission ACCESS_NETWORK_STATE");
                return false
            }

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val networkInfo = connectivityManager.allNetworkInfo
                if (networkInfo != null) {
                    for (i in networkInfo.indices) {
                        if (networkInfo[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }
        fun isNetworkConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networks = connectivityManager.allNetworks
                var connected = false

                for (network in networks) {
                    val networkInfo = connectivityManager.getNetworkInfo(network)
                    if (networkInfo != null && networkInfo.isConnected) {
                        connected = true
                        break
                    }
                }
                connected
            } else {
                // For older versions where allNetworks is not available
                val networkInfo = connectivityManager.activeNetworkInfo
                networkInfo != null && networkInfo.isConnected
            }
        }

        fun isConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = connectivityManager.activeNetwork
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    ?: false
            } else {
                val networkInfo = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            }
        }
    }
}