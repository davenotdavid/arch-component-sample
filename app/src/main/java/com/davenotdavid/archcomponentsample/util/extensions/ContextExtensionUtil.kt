package com.davenotdavid.archcomponentsample.util.extensions

import android.content.Context
import android.net.ConnectivityManager

fun Context?.isNetworkConnected(): Boolean {
    this?.let { ctx ->
        val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    return false
}
