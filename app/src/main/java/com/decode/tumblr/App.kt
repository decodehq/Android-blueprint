package com.decode.tumblr

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.decode.tumblr.di.appModule
import com.decode.tumblr.service.PostsFetchWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {
        var API_KEY = "si0PHNqV4V39DZyIxV82QFnxovHxcjXsVpzV9mTHlFHQjNivH1"
    }

    override fun onCreate() {
        super.onCreate()
        startWorkManager()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun startWorkManager() {
        val work = PeriodicWorkRequest.Builder(PostsFetchWorker::class.java,
                2, TimeUnit.HOURS)
                .setConstraints(constraints())
                .build()
        WorkManager.getInstance().enqueue(work)
    }

    private fun constraints(): Constraints {
        return Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
    }

}
