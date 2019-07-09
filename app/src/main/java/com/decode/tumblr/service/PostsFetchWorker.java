package com.decode.tumblr.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.decode.tumblr.data.repository.PostRepository;

import timber.log.Timber;

public class PostsFetchWorker extends Worker {
    private PostRepository postRepository;

    public PostsFetchWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        postRepository = new PostRepository(context);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {
        Timber.d("doWork called");
        postRepository.fetchPosts()
                .subscribe(posts -> Timber.d("Posts fetches"), Timber::e);
        return Worker.Result.success();
    }
}
