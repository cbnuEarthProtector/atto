package com.example.atto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("select Product.id,\n" +
            "       Product.name,\n" +
            "       Product.category,\n" +
            "       Brand.name as brandName,\n" +
            "       Product.price,\n" +
            "       Product.site_url as siteURL,\n" +
            "       Product.photo_url as photoURL,\n" +
            "       Product.is_bookmarked as isBookmarked,\n" +
            "       Product.memo\n" +
            "from Product,\n" +
            "     Brand\n" +
            "where Product.brand_id = Brand.id;")
    List<ProductWithBrandName> getAll();

    @Query("select product.id,\n" +
            "       product.name,\n" +
            "       product.category,\n" +
            "       brand.name as brandName,\n" +
            "       product.price,\n" +
            "       Product.site_url as siteURL,\n" +
            "       Product.photo_url as photoURL,\n" +
            "       Product.is_bookmarked as isBookmarked,\n" +
            "       product.memo\n" +
            "from product,\n" +
            "     brand\n" +
            "where product.brand_id = brand.id\n" +
            "  and brand.id = :brandId;")
    List<ProductWithBrandName> findAllByBrandId(Integer brandId);

    @Query("select product.id,\n" +
            "       product.name,\n" +
            "       product.category,\n" +
            "       brand.name as brandName,\n" +
            "       product.price,\n" +
            "       Product.site_url as siteURL,\n" +
            "       Product.photo_url as photoURL,\n" +
            "       Product.is_bookmarked as isBookmarked,\n" +
            "       product.memo\n" +
            "from product,\n" +
            "     brand\n" +
            "where product.brand_id = brand.id\n" +
            "  and brand.id = :brandId\n" +
            "  and product.category = :category;")
    List<ProductWithBrandName> findAllByBrandIdAndCategory(Integer brandId, String category);


    @Query("select product.id,\n" +
            "       product.name,\n" +
            "       product.category,\n" +
            "       brand.name as brandName,\n" +
            "       product.price,\n" +
            "       Product.site_url as siteURL,\n" +
            "       Product.photo_url as photoURL,\n" +
            "       Product.is_bookmarked as isBookmarked,\n" +
            "       product.memo\n" +
            "from product,\n" +
            "     brand\n" +
            "where product.brand_id = brand.id\n" +
            "  and product.category = :category;")
    List<ProductWithBrandName> findAllByCategory(String category);

    @Query("select product.id,\n" +
            "       product.name,\n" +
            "       product.category,\n" +
            "       brand.name as brandName,\n" +
            "       product.price,\n" +
            "       Product.site_url as siteURL,\n" +
            "       Product.photo_url as photoURL,\n" +
            "       Product.is_bookmarked as isBookmarked,\n" +
            "       product.memo\n" +
            "from product,\n" +
            "     brand\n" +
            "where product.brand_id = brand.id\n" +
            "  and product.is_bookmarked = 1;")
    List<ProductWithBrandName> findAllWhichBookmarked();

    @Query("update product\n" +
            "set is_bookmarked = 1,\n" +
            "    memo = :memo\n" +
            "where id = :productId;")
    void addBookmark(Integer productId, String memo);

    @Query("update product\n" +
            "set is_bookmarked = 0,\n" +
            "    memo = null\n" +
            "where id = :productId;")
    void deleteBookmark(Integer productId);
}
