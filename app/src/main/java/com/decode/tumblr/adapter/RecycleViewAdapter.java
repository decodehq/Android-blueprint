package com.decode.tumblr.adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.decode.tumblr.R;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.interfaces.OnPostClickListener;
import com.decode.tumblr.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private static final String TAG = RecycleViewAdapter.class.getSimpleName();

    private List<Post> postList;
    private OnPostClickListener onPostClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_tags)
        TextView txtTags;
        @BindView(R.id.txt_updated)
        TextView txtUpdated;
        @BindView(R.id.img_post)
        ImageView imgPost;

        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public void setOnPostClickListener(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    public RecycleViewAdapter(List<Post> postList) {
        this.postList = postList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Post post = postList.get(position);
        holder.txtTitle.setText(post.summary);
        holder.txtUpdated.setText(DateFunction.getDateCurrentTimeZone(post.timestamp));
        holder.txtTags.setText("");

        for (int i = 0; i < post.tags.size(); i++) {
            holder.txtTags.setText(holder.txtTags.getText() + " #" + post.tags.get(i));
        }


        try {

            if (post.photos == null) {
                Glide.with(holder.itemView).load(R.drawable.no_image_available).into(holder.imgPost);

            } else {

                Glide.with(holder.itemView)
                        .load(post.photos.get(0).altSizes.get(2).url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Log.e(TAG, "Error loading image", e);
                                Glide.with(holder.itemView).load(R.drawable.no_image_available).into(holder.imgPost);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(holder.imgPost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.itemView.setOnClickListener(v -> {
            onPostClickListener.onPostClick(post, holder.itemView);

        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
