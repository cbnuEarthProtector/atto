package com.example.atto.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Brand {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "photo_URL")
    public String photoURL;
}
