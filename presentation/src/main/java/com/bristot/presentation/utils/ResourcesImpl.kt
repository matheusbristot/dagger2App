package com.bristot.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import com.bristot.domain.Resources
import com.bristot.presentation.R
import javax.inject.Inject

class ResourcesImpl @Inject constructor(context: Context) : Resources {

    private val context = context.applicationContext

    override val defaultText: String get() = stringRes(R.string.default_text)
    override val appName: String get() = stringRes(R.string.app_name)

    override val androidIcon: Int get() = R.drawable.ic_default_android_24dp

    private fun stringRes(@StringRes stringId: Int): String = context.getString(stringId)
}