package com.decode.tumblr.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.decode.tumblr.R;
import com.decode.tumblr.model.Post;
import com.decode.tumblr.viewmodel.FragmentMainViewModel;
import com.decode.tumblr.viewmodel.PostViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
    private PostViewModel mPostViewModel;

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
        mainViewModel.getMainHeaderMutableLiveData().observe(this, mainHeader -> {
            txtBlogTitle.setText(mainHeader.getTitle());
            txtTotalPosts.setText(mainHeader.getTotalPost());
            txtUpdated.setText(mainHeader.getUpdated());
        });


        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mPostViewModel.getmAllPosts();



    }
}
