package com.aaa.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InetConnectivity @Inject constructor(
    @ApplicationContext private val context: Context
): InternetConnectivity {
    override fun isConnected(): Boolean {
        return context.isNetworkAvailable()
    }
}


val Context.connectivityManager: ConnectivityManager get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.isNetworkAvailable(): Boolean {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {

        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        nwInfo.isConnected
    }
}