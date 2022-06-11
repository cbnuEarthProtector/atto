package com.example.atto.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductBookmarkDao {
    @Query("select * from ProductBookmark")
    List<ProductBookmark> getAll();

    @Query("select * from ProductBookmark where product_id = :productId")
    ProductBookmark findByProductId(Integer productId);

    @Insert
    void insert(ProductBookmark productBookmark);
}
