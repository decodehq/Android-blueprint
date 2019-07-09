package com.decode.tumblr.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.decode.tumblr.R;
import com.decode.tumblr.model.PostObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentMain extends Fragment implements RecycleViewAdapter.OnPostClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private RecycleViewAdapter adapter;
    private PostViewModel postViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Setup recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Setup adapter
        adapter = new RecycleViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnPostClickListener(FragmentMain.this);

        // Get data
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        subscribePostData();

        // On swipe - get new data from server
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            postViewModel.fetchPosts();
        });
    }

    @Override
    public void onPostClick(PostObject post, View view) {
        FragmentMainDirections.ActionFragmentMainToFragmentDetails action =
                FragmentMainDirections.actionFragmentMainToFragmentDetails(post);
        Navigation.findNavController(view).navigate(action);
    }

    private void subscribePostData() {
        postViewModel.getPosts().observe(this, posts -> {
            adapter.setPostList(posts);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        });

        postViewModel.getLoadingFailed().observe(this, error ->
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show());
    }
}
