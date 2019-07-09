package com.decode.tumblr.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decode.tumblr.R
import com.decode.tumblr.data.db.model.PostObject
import kotlinx.android.synthetic.main.recyclerview_row.view.*
import java.util.*

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

    private var postList: List<PostObject> = ArrayList()
    private var onPostClickListener: OnPostClickListener? = null

    interface OnPostClickListener {
        fun onPostClick(post: PostObject, view: View)
    }

    fun setOnPostClickListener(onPostClickListener: OnPostClickListener) {
        this.onPostClickListener = onPostClickListener
    }

    fun setPostList(postList: List<PostObject>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = postList[position]
        holder.txtTitle!!.text = post.title

        Glide.with(holder.itemView)
                .load(post.photoObject?.url)
                .error(R.drawable.no_image_available)
                .into(holder.imgPost!!)

        holder.itemView.setOnClickListener {
            onPostClickListener!!.onPostClick(post, holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle: TextView? = view.txt_title
        var txtTags: TextView? = view.txt_tags
        var txtUpdated: TextView? = view.txt_updated
        var imgPost: ImageView? = view.img_post
    }
}
