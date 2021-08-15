package com.example.instacart.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.instacart.ProductItems;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insert(ProductItems items);

    @Delete
    void delete(ProductItems items);

    @Update
    void update(ProductItems items);

    @Query("DELETE FROM product_table")
    void deleteAllItems();

    @Query("SELECT * FROM product_table")
    LiveData<List<ProductItems>> getAllProductInCart();

    @Query("SELECT sum(numberOfProduct*price) FROM product_table")
    LiveData<Double> getTotalPrice();

}
