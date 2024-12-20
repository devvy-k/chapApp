package com.example.chapapp_compose.core.util

import android.util.Log

object UtilFunctions {
    fun logE(message: String) {
        val debug = "true".toBoolean()
        if (debug) Log.e("ERROR_IMO", message)
    }
}