package com.bristot.presentation.core.navigation

import android.content.Context
import com.bristot.presentation.ui.main.MainActivity

class Navigator {
    companion object {
        fun goTo(context: Context?, navData: NavData) {
            context?.let {
                when (navData) {
                    is NavData.MainNavData -> MainActivity.start(it)
                }
            }
        }
    }
}