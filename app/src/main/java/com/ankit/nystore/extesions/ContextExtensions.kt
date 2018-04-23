package com.ankit.nystore.extesions

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

@Suppress("NOTHING_TO_INLINE")
inline fun Context.hasInternetConnection(): Boolean {
  val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
  val activeNetwork = cm.activeNetworkInfo
  return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}