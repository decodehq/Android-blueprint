package com.decode.tumblr.di

import com.decode.tumblr.data.repository.PostRepository
import com.decode.tumblr.posts.PostViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { PostRepository(get(), get(), get(), get()) }

    viewModel { PostViewModel(get(), get()) }
}
