package com.decode.tumblr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "post_table")
public class PostObject implements Parcelable {
    @PrimaryKey()
    private long id;
    @NonNull
    private String title;
    @NonNull
    private int photoId;
    @Ignore
    private PhotoObject photoObject;

    protected PostObject(Parcel in) {
        id = in.readLong();
        title = in.readString();
        photoId = in.readInt();
    }


    public PostObject() {
    }

    public static final Creator<PostObject> CREATOR = new Creator<PostObject>() {
        @Override
        public PostObject createFromParcel(Parcel in) {
            return new PostObject(in);
        }

        @Override
        public PostObject[] newArray(int size) {
            return new PostObject[size];
        }
    };

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public PhotoObject getPhotoObject() {
        return photoObject;
    }

    public void setPhotoObject(PhotoObject photoObject) {
        this.photoObject = photoObject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeInt(photoId);
    }

}
