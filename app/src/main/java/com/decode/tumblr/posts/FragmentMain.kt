package com.decode.tumblr.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.decode.tumblr.R
import com.decode.tumblr.data.db.model.PostObject
import kotlinx.android.synthetic.main.layout_fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentMain : Fragment(), PostsAdapter.OnPostClickListener {

    private val postViewModel: PostViewModel by viewModel()
    private var adapter: PostsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup recyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        // Setup adapter
        adapter = PostsAdapter()
        recyclerView!!.adapter = adapter
        adapter!!.setOnPostClickListener(this@FragmentMain)

        subscribePostData()

        // On swipe - get new data from server
        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.isRefreshing = true
            postViewModel.fetchPosts()
        }
    }

    override fun onPostClick(post: PostObject, view: View) {
        val action = FragmentMainDirections.actionFragmentMainToFragmentDetails(post)
        Navigation.findNavController(view).navigate(action)
    }

    private fun subscribePostData() {
        postViewModel.posts.observe(this, Observer { posts ->
            adapter!!.setPostList(posts)
            progressBar!!.visibility = View.GONE
            swipeRefreshLayout!!.isRefreshing = false
        })

        postViewModel.loadingFailed.observe(this, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }
}
