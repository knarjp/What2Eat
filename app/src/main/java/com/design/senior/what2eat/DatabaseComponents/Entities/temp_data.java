package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by KJ on 2/10/2018.
 */

@Entity
public class temp_data {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    private int key;

    @NonNull
    @ColumnInfo(name = "text")
    private String text;

    @NonNull
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public int getKey() {
        return this.key;
    }

    public void setKey(int newKey) {
        this.key = newKey;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] newImage) {
        this.image = newImage;
    }
}
