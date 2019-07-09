package com.decode.tumblr.posts

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.decode.tumblr.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Lazy Inject ViewModel
    val postViewModel: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        postViewModel.fetchPosts()

        // Set title
        toolbar!!.title = getString(R.string.android_onboarding_project)

        // Set header data
        postViewModel.header.observe(this, Observer { header ->
            if (header != null) {
                txt_blog_title!!.text = header.title
                txt_total_posts!!.text = header.totalPost
                txt_updated!!.text = header.updated
            }
        })
    }
}
