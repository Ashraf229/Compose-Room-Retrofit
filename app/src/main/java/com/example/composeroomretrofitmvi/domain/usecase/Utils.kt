package com.example.composeroomretrofitmvi.domain.usecase

import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    // Utility function to check if the internet is available
    fun isInternetAvailable(connectivityManager:ConnectivityManager): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    // Utility function to get the current date as a String (e.g., "2024-10-10")
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}