package com.decode.tumblr.interfaces;

import android.view.View;

import com.decode.tumblr.model.Post;
import com.decode.tumblr.model.PostObject;

public interface OnPostClickListener {

    void onPostClick(PostObject post, View view);
}
