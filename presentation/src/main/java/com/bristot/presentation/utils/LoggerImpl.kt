package com.bristot.presentation.utils

import android.util.Log
import com.bristot.domain.Logger
import com.bristot.domain.Resources
import com.bristot.presentation.BuildConfig
import javax.inject.Inject

class LoggerImpl @Inject constructor(resources: Resources) : Logger {

    private val tag = resources.appName

    override fun v(message: String) {
        if (BuildConfig.DEBUG) Log.v(tag, message)
    }

    override fun v(message: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) Log.v(tag, message, throwable)
    }

    override fun d(message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

    override fun d(message: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) Log.d(tag, message, throwable)
    }

    override fun i(message: String) {
        if (BuildConfig.DEBUG) Log.i(tag, message)
    }

    override fun i(message: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) Log.i(tag, message, throwable)
    }

    override fun w(message: String) {
        if (BuildConfig.DEBUG) Log.w(tag, message)
    }

    override fun w(message: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) Log.i(tag, message, throwable)
    }

    override fun w(throwable: Throwable) {
        if (BuildConfig.DEBUG) Log.w(tag, throwable)
    }

    override fun e(message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message)
    }

    override fun e(message: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) Log.e(tag, message, throwable)
    }

    override fun e(throwable: Throwable) {
        if (BuildConfig.DEBUG) Log.e(tag, throwable.message, throwable)
        else {
            // TODO: Not added Firebase Crashlytics yet
        }
    }
}