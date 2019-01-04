package com.bristot.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bristot.presentation.utils.Event
import com.bristot.presentation.utils.EventObserver

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(owner, androidx.lifecycle.Observer { observer(it) })
}

fun <T> LiveData<T>.reobserve(owner: LifecycleOwner, observer: (T?) -> Unit) {
    removeObservers(owner)
    observe(owner, observer)
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(owner, EventObserver(observer))
}

fun <T, U> LiveData<T>.map(mapper: (T?) -> U) = Transformations.map(this, mapper)
