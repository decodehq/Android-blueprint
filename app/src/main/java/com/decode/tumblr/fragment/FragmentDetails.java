package com.decode.tumblr.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.decode.tumblr.R;
import com.decode.tumblr.helpers.DateFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDetails extends Fragment {
    @BindView(R.id.img_post_large)
    ImageView imgPostLarge;
    @BindView(R.id.txt_title_details)
    TextView txtTitleDetails;
    @BindView(R.id.view_separator)
    View viewSeparator;
    @BindView(R.id.txt_paracelable_data)
    TextView txtParacelableData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getParcelableData();
    }


    private void getParcelableData() {
        FragmentDetailsArgs args = null;

        if (getArguments() != null) {
            args = FragmentDetailsArgs.fromBundle(getArguments());

        } else {
            txtParacelableData.setText(getString(R.string.no_data));
            return;
        }

        String tags = "";
        for (int i = 0; i < args.getPostObject().tags.size(); i++) {
            tags = tags + " #" + args.getPostObject().tags.get(i);
        }

        String data = "<b> Blog name:</b> " + args.getPostObject().blogName +
                "<br> <b> Can like:</b> " + args.getPostObject().canLike +
                "<br> <b> Can reblog: </b> " + args.getPostObject().canReblog +
                "<br> <b> Can reply: </b> " + args.getPostObject().canReply +
                "<br> <b> Can send in message: </b> " + args.getPostObject().canSendInMessage +
                "<br> <b> Caption: </b> " + args.getPostObject().caption +
                "<br> <b> Date: </b> " + args.getPostObject().date +
                "<br> <b> Format: </b> " + args.getPostObject().format +
                "<br> <b> Id: </b> " + args.getPostObject().id +
                "<br> <b> Image permalink: </b> " + args.getPostObject().imagePermalink +
                "<br> <b> Is blocks post format: </b> " + args.getPostObject().isBlocksPostFormat +
                "<br> <b> Note count: </b> " + args.getPostObject().noteCount +
                "<br> <b> Post url: </b> " + args.getPostObject().postUrl +
                "<br> <b> Reblog key: </b> " + args.getPostObject().reblogKey +
                "<br> <b> Recommended color: </b> " + args.getPostObject().recommendedColor +
                "<br> <b> Recommended source: </b> " + args.getPostObject().recommendedColor +
                "<br> <b> Short url: </b> " + args.getPostObject().shortUrl +
                "<br> <b> Slug: </b> " + args.getPostObject().slug +
                "<br> <b> State: </b> " + args.getPostObject().state +
                "<br> <b> Summary: </b> " + args.getPostObject().summary +
                "<br> <b> Tags: </b> " + tags +
                "<br> <b> Timestamp: </b> " + DateFunction.getDateCurrentTimeZone(args.getPostObject().timestamp) +
                "<br> <b> Type: </b> " + args.getPostObject().type;

        txtParacelableData.setText(Html.fromHtml(data));

        try {
            Glide.with(this).load(args.getPostObject().photos.get(0).altSizes.get(2).url).into(imgPostLarge);
        } catch (Exception e) {
            Glide.with(this).load(R.drawable.no_image_available).into(imgPostLarge);
            e.printStackTrace();
        }

    }
}
