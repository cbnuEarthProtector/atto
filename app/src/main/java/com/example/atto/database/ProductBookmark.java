package com.example.atto.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity
public class ProductBookmark {
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    public Integer productId;

    public String memo;

    public ProductBookmark() {
    }

    public ProductBookmark(Integer productId, String memo) {
        this.productId = productId;
        this.memo = memo;
    }
}
