package com.decode.tumblr.fragment;

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
import com.decode.tumblr.adapter.RecycleViewAdapter;
import com.decode.tumblr.interfaces.OnPostClickListener;
import com.decode.tumblr.model.PostObject;
import com.decode.tumblr.viewmodel.FragmentMainViewModel;
import com.decode.tumblr.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class FragmentMain extends Fragment implements OnPostClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private RecycleViewAdapter adapter;
    private FragmentMainViewModel fragmentMainViewModel;
    private PostViewModel postViewModel;
    private List<PostObject> postList = new ArrayList<>();
    private Disposable disposable;

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
        fragmentMainViewModel = ViewModelProviders.of(getActivity()).get(FragmentMainViewModel.class);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        subscribePostData();

        // On swipe - get new data from server
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            fragmentMainViewModel.fetchPosts();
        });
    }


    private void subscribePostData() {
        postViewModel.getPosts().observe(this, posts -> {
            postList.clear();
            postList.addAll(posts);
            adapter.setPostList(postList);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        });


        // Subscribe -> Single live error event
        fragmentMainViewModel.loadErrorEvent().observe(this, s -> Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onPostClick(PostObject post, View view) {
        FragmentMainDirections.ActionFragmentMainToFragmentDetails action = FragmentMainDirections.actionFragmentMainToFragmentDetails(post);
        Navigation.findNavController(view).navigate(action);
    }
}
