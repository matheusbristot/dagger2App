package com.bristot.presentation.provider

import com.bristot.presentation.BuildConfig
import com.facebook.stetho.Stetho

class StethoProvider : BaseContentProvider() {
    override fun onCreate(): Boolean {
        if (BuildConfig.DEBUG) {
            context?.let { Stetho.initializeWithDefaults(context) }
        }
        return true
    }
}