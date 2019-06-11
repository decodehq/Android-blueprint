package com.tumblrdecode.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post implements Parcelable {

    @SerializedName("type")
    public String type;
    @SerializedName("blog_name")
    public String blogName;
    @SerializedName("id")
    public Long id;
    @SerializedName("post_url")
    public String postUrl;
    @SerializedName("slug")
    public String slug;
    @SerializedName("date")
    public String date;
    @SerializedName("timestamp")
    public Integer timestamp;
    @SerializedName("state")
    public String state;
    @SerializedName("format")
    public String format;
    @SerializedName("reblog_key")
    public String reblogKey;
    @SerializedName("tags")
    public List<String> tags = null;
    @SerializedName("short_url")
    public String shortUrl;
    @SerializedName("summary")
    public String summary;
    @SerializedName("is_blocks_post_format")
    public Boolean isBlocksPostFormat;
    @SerializedName("recommended_source")
    public Object recommendedSource;
    @SerializedName("recommended_color")
    public Object recommendedColor;
    @SerializedName("note_count")
    public Integer noteCount;
    @SerializedName("source_url")
    public String sourceUrl;
    @SerializedName("source_title")
    public String sourceTitle;
    @SerializedName("title")
    public String title;
    @SerializedName("body")
    public String body;
    @SerializedName("caption")
    public String caption;
    @SerializedName("can_like")
    public String canLike;
    @SerializedName("can_reblog")
    public String canReblog;
    @SerializedName("can_send_in_message")
    public String canSendInMessage;
    @SerializedName("can_reply")
    public String canReply;
    @SerializedName("display_avatar")
    public String displayAvatar;
    @SerializedName("image_permalink")
    public String imagePermalink;
    @SerializedName("reblog")
    public Reblog reblog;
    @SerializedName("trail")
    public List<Trail> trail = null;
    @SerializedName("notes")
    public List<Note> notes = null;
    @SerializedName("photos")
    public List<Photo> photos = null;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.blogName);
        dest.writeValue(this.id);
        dest.writeString(this.postUrl);
        dest.writeString(this.slug);
        dest.writeString(this.date);
        dest.writeValue(this.timestamp);
        dest.writeString(this.state);
        dest.writeString(this.format);
        dest.writeString(this.reblogKey);
        dest.writeStringList(this.tags);
        dest.writeString(this.shortUrl);
        dest.writeString(this.summary);
        dest.writeValue(this.isBlocksPostFormat);
        dest.writeValue(this.noteCount);
        dest.writeString(this.sourceUrl);
        dest.writeString(this.sourceTitle);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeString(this.caption);
        dest.writeString(this.canLike);
        dest.writeString(this.canReblog);
        dest.writeString(this.canSendInMessage);
        dest.writeString(this.canReply);
        dest.writeString(this.displayAvatar);
        dest.writeString(this.imagePermalink);
    }

    protected Post(Parcel in) {
        this.type = in.readString();
        this.blogName = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.postUrl = in.readString();
        this.slug = in.readString();
        this.date = in.readString();
        this.timestamp = (Integer) in.readValue(Integer.class.getClassLoader());
        this.state = in.readString();
        this.format = in.readString();
        this.reblogKey = in.readString();
        this.tags = in.createStringArrayList();
        this.shortUrl = in.readString();
        this.summary = in.readString();
        this.isBlocksPostFormat = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.noteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sourceUrl = in.readString();
        this.sourceTitle = in.readString();
        this.title = in.readString();
        this.body = in.readString();
        this.caption = in.readString();
        this.canLike = in.readString();
        this.canReblog = in.readString();
        this.canSendInMessage = in.readString();
        this.canReply = in.readString();
        this.displayAvatar = in.readString();
        this.imagePermalink = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}

