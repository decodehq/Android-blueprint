package com.decode.tumblr.posts;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.decode.tumblr.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        // Set title
        toolbar.setTitle(getString(R.string.android_onboarding_project));

        PostViewModel postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.fetchPosts();

        // Set header data
        postViewModel.getHeader().observe(this, header -> {
            if (header != null) {
                txtBlogTitle.setText(header.getTitle());
                txtTotalPosts.setText(header.getTotalPost());
                txtUpdated.setText(header.getUpdated());
            }
        });
    }
}
