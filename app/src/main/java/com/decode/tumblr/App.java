package com.decode.tumblr;

import android.app.Application;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.decode.tumblr.service.MyWorker;

import java.util.concurrent.TimeUnit;

public class App extends Application {
    public static String API_KEY = "si0PHNqV4V39DZyIxV82QFnxovHxcjXsVpzV9mTHlFHQjNivH1";
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();


        instance = this;
        startWorkManager();
    }


    private void startWorkManager() {
        PeriodicWorkRequest work = new PeriodicWorkRequest.Builder(MyWorker.class,
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


    public static App getInstance() {
        return instance;
    }
}
