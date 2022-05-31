package com.example.atto.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BrandDao {
    @Query("select * from Brand")
    List<Brand> getAll();

    @Query("select * from Brand where id = :brandId")
    Brand findById(Integer brandId);

    @Query("update Brand\n" +
            "set is_bookmarked = 1 \n" +
            "where id = :brandId;")
    void addBookmark(Integer brandId);

    @Query("update Brand\n" +
            "set is_bookmarked = 0 \n" +
            "where id = :brandId;")
    void deleteBookmark(Integer brandId);

    @Query("select * from Brand where name = :brandName")
    int findByString(String brandName);
}
