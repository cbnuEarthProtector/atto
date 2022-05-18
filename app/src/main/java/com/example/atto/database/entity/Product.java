package com.example.atto.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category")
    public String category;

//    @Embedded
//    public Brand brand;

    @ColumnInfo(name = "price")
    public Integer price;

    @ColumnInfo(name = "site_url")
    public String siteURL;

    @ColumnInfo(name = "photo_url")
    public String photoURL;
}
