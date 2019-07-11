package com.decode.tumblr.ui.helper

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel

/**
 * Lifecycle aware ViewModel. Use this when you need to handle your Rx subscriptions in ViewModel.
 */
open class LifecycleViewModel : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        // Do nothing by default
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        // Do nothing by default
    }

}
