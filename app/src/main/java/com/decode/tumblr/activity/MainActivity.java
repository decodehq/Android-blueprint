package com.decode.tumblr.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.decode.tumblr.R;
import com.decode.tumblr.repository.PostRepository;
import com.decode.tumblr.viewmodel.FragmentMainViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        // Set title
        toolbar.setTitle(getString(R.string.android_onboarding_project));

        FragmentMainViewModel mainViewModel = ViewModelProviders.of(this).get(FragmentMainViewModel.class);
        mainViewModel.fetchPosts();


        // Set header data
        mainViewModel.getMainHeaderLiveData().observe(this, header -> {

            if (header != null) {
                txtBlogTitle.setText(header.getTitle());
                txtTotalPosts.setText(header.getTotalPost());
                txtUpdated.setText(header.getUpdated());
            }
        });
    }
}
