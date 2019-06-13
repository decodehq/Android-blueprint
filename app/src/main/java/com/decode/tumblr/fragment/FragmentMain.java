package com.decode.tumblr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import com.decode.tumblr.adapter.RecycleViewAdapter;
import com.decode.tumblr.interfaces.OnPostClickListener;
import com.decode.tumblr.model.Post;
import com.decode.tumblr.viewmodel.FragmentMainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentMain extends Fragment implements OnPostClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private RecycleViewAdapter adapter;
    private FragmentMainViewModel mFragmentMainViewModel;

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


        // Get data
        mFragmentMainViewModel = ViewModelProviders.of(this).get(FragmentMainViewModel.class);
        mFragmentMainViewModel.fetchPosts();

        subscribePostData();

        // On swipe - get new data from server
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            mFragmentMainViewModel.fetchPosts();
        });

    }

    private void subscribePostData() {
        mFragmentMainViewModel.getPostLiveData().observe(this, posts -> {
            adapter = new RecycleViewAdapter(posts);
            recyclerView.setAdapter(adapter);
            adapter.setOnPostClickListener(FragmentMain.this);

            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onPostClick(Post post, View view) {

        FragmentMainDirections.ActionFragmentMainToFragmentDetails action = FragmentMainDirections.actionFragmentMainToFragmentDetails(post);
        Navigation.findNavController(view).navigate(action);

    }
}
