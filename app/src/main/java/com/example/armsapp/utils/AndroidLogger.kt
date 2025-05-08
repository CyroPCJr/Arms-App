package com.example.armsapp.utils

import android.util.Log

interface Logger {
    fun e(
        tag: String,
        message: String,
    )

    fun d(
        tag: String,
        message: String,
    )
}

class AndroidLogger : Logger {
    override fun e(
        tag: String,
        message: String,
    ) {
        Log.e(tag, message)
    }

    override fun d(
        tag: String,
        message: String,
    ) {
        Log.d(tag, message)
    }
}
