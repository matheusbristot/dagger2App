package com.bristot.presentation.provider.application

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.bristot.presentation.core.graph.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class Dagger2Application : MultiDexApplication(), HasActivityInjector {

    @Inject
    protected lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingActivityInjector
    }
}


