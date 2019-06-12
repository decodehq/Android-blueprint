package com.decode.tumblr.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.decode.tumblr.R;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private List<Post> postList;
    private Context context;

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


    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    public RecycleViewAdapter(Context context, List<Post> postList) {
        this.context = context;
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

        for (int i = 0; i < postList.get(position).tags.size(); i++) {
            holder.txtTags.setText(holder.txtTags.getText() + " #" + postList.get(position).tags.get(i));
        }


        try {
            Glide.with(context).load(postList.get(position).photos.get(0).altSizes.get(2).url).into(holder.imgPost);
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.itemView.setOnClickListener(v -> {

//            Intent intent = new Intent(context, DetailsActivity.class);
//            intent.putExtra("Post", post);
//            try {
//                intent.putExtra("imgLink", postList.get(position).photos.get(0).altSizes.get(2).url);
//            } catch (Exception e) {
//                intent.putExtra("imgLink", R.drawable.no_image_available);
//            }
//
//            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, holder.imgPost, "simple_activity_transition");
//            context.startActivity(intent, options.toBundle());

        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
