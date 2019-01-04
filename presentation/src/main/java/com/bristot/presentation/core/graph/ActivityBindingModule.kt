package com.bristot.presentation.core.graph

import com.bristot.presentation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector()
    fun contributeMainActivity(): MainActivity
}