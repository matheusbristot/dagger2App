package com.bristot.presentation.ui.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import com.bristot.domain.Logger
import com.bristot.domain.Resources
import com.bristot.presentation.core.base.BaseViewModel
import com.bristot.presentation.utils.FlexibleLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val resources: Resources,
        private val logger: Logger
) : BaseViewModel() {

    val onResumeLifeCycle: LiveData<String> get() = onResumeLifeCycleLiveData
    val icon: LiveData<Int> get() = iconLiveData
    private val onResumeLifeCycleLiveData: FlexibleLiveData<String> = FlexibleLiveData()
    private val iconLiveData: FlexibleLiveData<Int> = FlexibleLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        logger.e(Lifecycle.Event.ON_CREATE.name)
        iconLiveData.value = resources.androidIcon
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        onResumeLifeCycleLiveData.value = resources.defaultText
        logger.e(Lifecycle.Event.ON_RESUME.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        logger.e(Lifecycle.Event.ON_START.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        logger.e(Lifecycle.Event.ON_PAUSE.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        logger.e(Lifecycle.Event.ON_STOP.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        logger.e(Lifecycle.Event.ON_DESTROY.name)
    }
}