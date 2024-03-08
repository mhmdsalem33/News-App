package com.sa.football.data.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtils @Inject constructor( private val connectivityManager: ConnectivityManager ) {

    fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)
    }
}
