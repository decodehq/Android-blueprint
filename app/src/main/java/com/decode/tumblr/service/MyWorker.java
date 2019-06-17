package com.decode.tumblr.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.decode.tumblr.App;
import com.decode.tumblr.repository.PostRepository;

public class MyWorker extends Worker {
    private static final String TAG = "MyWorker";
    private PostRepository postRepository;

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        postRepository = new PostRepository(App.getInstance());
    }

    @NonNull
    @Override
    public Worker.Result doWork() {
        Log.d(TAG, "doWork called");
        postRepository.fetchPosts();
        return Worker.Result.success();
    }
}
