package com.decode.tumblr.ui.helper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Lifecycle aware AndroidViewModel.
 * Use this when you need to handle your Rx subscriptions in AndroidViewModel.
 */
open class LifecycleAndroidViewModel(application: Application)
    : AndroidViewModel(application), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        // Do nothing by default
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        // Do nothing by default
    }

}

