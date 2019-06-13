package com.decode.tumblr.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.decode.tumblr.R;
import com.decode.tumblr.activity.MainActivity;
import com.decode.tumblr.adapter.RecycleViewAdapter;
import com.decode.tumblr.api.ApiClient;
import com.decode.tumblr.api.ApiInterface;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.interfaces.OnPostClickListener;
import com.decode.tumblr.model.Data;
import com.decode.tumblr.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.decode.tumblr.App.API_KEY;

public class FragmentMain extends Fragment implements OnPostClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private List<Post> postList = new ArrayList<>();
    private int pageIndex = 0;
    private int pageLimit = 20;
    private int totalPosts = 0;
    private RecycleViewAdapter adapter;


    public FragmentMain() {
    }

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
        adapter = new RecycleViewAdapter(getContext(), postList);
        recyclerView.setAdapter(adapter);
        adapter.setOnPostClickListener(this);

        // Get data
        loadPosts();

        // On swipe - get new data from server
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            postList.clear();
            loadPosts();
        });

    }

    private void loadPosts() {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Data> call = apiService.getPosts(API_KEY, pageIndex, pageLimit);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                List<Post> results = fetchResults(response);

                if (results != null) {
                    postList.addAll(results);
                    totalPosts = Objects.requireNonNull(response.body()).response.blog.total_posts;

                    // Set result to adapter
                    adapter.setPostList(postList);
                }

                swipeRefreshLayout.setRefreshing(false);

                // Set header data
                //txtBlogTitle.setText(Objects.requireNonNull(response.body()).response.blog.title);
                //txtTotalPosts.setText(String.valueOf(response.body().response.blog.total_posts));
                //txtUpdated.setText(DateFunction.getDateCurrentTimeZone(Long.parseLong(response.body().response.blog.updated)));

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                // handle error
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);

                //Toast error on screen
                Toast.makeText(getContext(), "Error getting data\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private List<Post> fetchResults(Response<Data> response) {
        if (response.body() != null) {
            Data data = response.body();
            return data.response.posts;
        }
        return null;
    }

    @Override
    public void onPostClick(Post post, View view) {
        Navigation.findNavController(view).navigate(R.id.action_fragmentMain_to_fragmentDetails);

    }
}
