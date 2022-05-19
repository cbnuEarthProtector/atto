package com.example.atto.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Brand {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "photo_url")
    public String photoURL;

    @ColumnInfo(name = "is_bookmarked")
    public Integer isBookmarked;
}
