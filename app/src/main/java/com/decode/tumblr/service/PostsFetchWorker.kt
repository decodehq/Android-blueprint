package com.decode.tumblr.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.decode.tumblr.data.repository.PostRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class PostsFetchWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams), KoinComponent {

    private val postRepository: PostRepository by inject()

    override fun doWork(): Result {
        Timber.d("doWork called")
        postRepository.fetchPosts()
                .subscribe({ Timber.d("Posts fetches") }, { Timber.e(it) })
        return Result.success()
    }
}
