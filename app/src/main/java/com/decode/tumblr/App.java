package com.decode.tumblr;

import android.app.Application;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.decode.tumblr.service.PostsFetchWorker;

import java.util.concurrent.TimeUnit;

public class App extends Application {
    public static String API_KEY = "si0PHNqV4V39DZyIxV82QFnxovHxcjXsVpzV9mTHlFHQjNivH1";

    @Override
    public void onCreate() {
        super.onCreate();
        startWorkManager();
    }
    
    private void startWorkManager() {
        PeriodicWorkRequest work = new PeriodicWorkRequest.Builder(PostsFetchWorker.class,
                2, TimeUnit.HOURS)
                .setConstraints(constraints())
                .build();
        WorkManager.getInstance().enqueue(work);
    }

    private Constraints constraints() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        return constraints;
    }

}
