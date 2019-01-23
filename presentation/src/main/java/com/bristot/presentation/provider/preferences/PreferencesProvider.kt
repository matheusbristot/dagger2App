package com.bristot.presentation.provider.preferences

import com.bristot.data.client.storage.preferences.PreferenceImpl
import com.bristot.presentation.provider.BaseContentProvider

class PreferencesProvider : BaseContentProvider() {

    override fun onCreate(): Boolean {
        context?.let { PreferenceImpl.start(it) }
        return true
    }
}