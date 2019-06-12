package com.decode.tumblr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.decode.tumblr.R;
import com.decode.tumblr.adapter.RecycleViewAdapter;
import com.decode.tumblr.api.ApiClient;
import com.decode.tumblr.api.ApiInterface;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.interfaces.OnPostClickListener;
import com.decode.tumblr.model.Data;
import com.decode.tumblr.model.Post;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.decode.tumblr.App.API_KEY;

public class MainActivity extends AppCompatActivity implements OnPostClickListener {

    public static final String POST_INTENT = "PostObject";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_blog_title)
    TextView txtBlogTitle;
    @BindView(R.id.txt_total_posts)
    TextView txtTotalPosts;
    @BindView(R.id.txt_updated)
    TextView txtUpdated;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        // Set title
        toolbar.setTitle(getString(R.string.android_onboarding_project));

        // Setup recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecycleViewAdapter(this, postList);
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
                txtBlogTitle.setText(Objects.requireNonNull(response.body()).response.blog.title);
                txtTotalPosts.setText(String.valueOf(response.body().response.blog.total_posts));
                txtUpdated.setText(DateFunction.getDateCurrentTimeZone(Long.parseLong(response.body().response.blog.updated)));

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                // handle error
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);

                //Toast error on screen
                Toast.makeText(MainActivity.this, "Error getting data\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onPostClick(Post post) {

        // Open Details Activity on click
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(POST_INTENT, post);
        try {
            intent.putExtra("imgLink", post.photos.get(0).altSizes.get(2).url);
        } catch (Exception e) {
            intent.putExtra("imgLink", R.drawable.no_image_available);
        }

        startActivity(intent);
    }
}
