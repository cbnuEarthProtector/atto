package com.example.atto.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"site_url"}, unique = true)},
        foreignKeys = {@ForeignKey(entity = Brand.class,
                parentColumns = "id", childColumns = "brand_id")})
public class Product {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "brand_id")
    public Integer brandId;

    @ColumnInfo(name = "price")
    public Integer price;

    @ColumnInfo(name = "site_url")
    public String siteURL;

    @ColumnInfo(name = "photo_url")
    public String photoURL;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brandId=" + brandId +
                ", price=" + price +
                ", siteURL='" + siteURL + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
