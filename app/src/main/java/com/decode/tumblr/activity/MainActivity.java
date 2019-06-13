package com.decode.tumblr.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.decode.tumblr.R;
import com.decode.tumblr.interfaces.OnPostClickListener;
import com.decode.tumblr.model.Post;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        // Set title
        toolbar.setTitle(getString(R.string.android_onboarding_project));


    }


    @Override
    public void onPostClick(Post post, View view) {

        // Open Details Activity on click
//        Intent intent = new Intent(this, DetailsActivity.class);
//        intent.putExtra(POST_INTENT, post);
//        try {
//            intent.putExtra("imgLink", post.photos.get(0).altSizes.get(2).url);
//        } catch (Exception e) {
//            intent.putExtra("imgLink", R.drawable.no_image_available);
//        }
//
//        startActivity(intent);
    }
}
