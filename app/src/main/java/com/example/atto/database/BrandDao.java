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

    @Query("select * from Brand where name = :brandName")
    int findByString(String brandName);
}
