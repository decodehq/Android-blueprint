package com.decode.tumblr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.decode.tumblr.R;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.model.Post;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.decode.tumblr.activity.MainActivity.POST_INTENT;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    @BindView(R.id.img_post_large)
    ImageView imgPostLarge;
    @BindView(R.id.txt_title_details)
    TextView txtTitleDetails;
    @BindView(R.id.view_separator)
    View viewSeparator;
    @BindView(R.id.txt_paracelable_data)
    TextView txtParacelableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setupToolbar();

        // Get paracelable data
        Post post = getIntent().getParcelableExtra(POST_INTENT);
        if (post != null) {

            try {
                Objects.requireNonNull(getSupportActionBar()).setTitle(post.summary);
                txtTitleDetails.setText(post.summary);

                String tags = "";
                for (int i = 0; i < post.tags.size(); i++) {
                    tags = tags + " #" + post.tags.get(i);
                }

                String data = "<b> Blog name:</b> " + post.blogName +
                        "<br> <b> Can like:</b> " + post.canLike +
                        "<br> <b> Can reblog: </b> " + post.canReblog +
                        "<br> <b> Can reply: </b> " + post.canReply +
                        "<br> <b> Can send in message: </b> " + post.canSendInMessage +
                        "<br> <b> Caption: </b> " + post.caption +
                        "<br> <b> Date: </b> " + post.date +
                        "<br> <b> Format: </b> " + post.format +
                        "<br> <b> Id: </b> " + post.id +
                        "<br> <b> Image permalink: </b> " + post.imagePermalink +
                        "<br> <b> Is blocks post format: </b> " + post.isBlocksPostFormat +
                        "<br> <b> Note count: </b> " + post.noteCount +
                        "<br> <b> Post url: </b> " + post.postUrl +
                        "<br> <b> Reblog key: </b> " + post.reblogKey +
                        "<br> <b> Recommended color: </b> " + post.recommendedColor +
                        "<br> <b> Recommended source: </b> " + post.recommendedColor +
                        "<br> <b> Short url: </b> " + post.shortUrl +
                        "<br> <b> Slug: </b> " + post.slug +
                        "<br> <b> State: </b> " + post.state +
                        "<br> <b> Summary: </b> " + post.summary +
                        "<br> <b> Tags: </b> " + tags +
                        "<br> <b> Timestamp: </b> " + DateFunction.getDateCurrentTimeZone(post.timestamp) +
                        "<br> <b> Type: </b> " + post.type;

                txtParacelableData.setText(Html.fromHtml(data));

                Log.i(TAG, "onCreate: " + data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        // Set transition animation
        imgPostLarge.setTransitionName("simple_activity_transition");

        // Get image link from intent
        Intent intent = getIntent();
        if (intent.getStringExtra("imgLink") == null) {
            Glide.with(this).load(R.drawable.no_image_available).into(imgPostLarge);

        } else {

            try {
                Glide.with(this).load(getIntent().getStringExtra("imgLink")).into(imgPostLarge);
            } catch (Exception e) {
                Glide.with(this).load(R.drawable.no_image_available).into(imgPostLarge);
                e.printStackTrace();
            }
        }
    }

    private void setupToolbar() {
        // Add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
