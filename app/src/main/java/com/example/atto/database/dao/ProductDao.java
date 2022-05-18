package com.example.atto.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.atto.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("select * from product")
    List<Product> getAll();

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);
}
