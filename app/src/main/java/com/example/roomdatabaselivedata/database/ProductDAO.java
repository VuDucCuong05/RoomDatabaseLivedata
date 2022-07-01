package com.example.roomdatabaselivedata.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabaselivedata.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM PRODUCT WHERE name=:product_name")
    List<Product> checkProduct(String product_name);

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM PRODUCT  ORDER BY id DESC")
    LiveData<List<Product>> getAllProduct();

    @Update
    void updateProduct(Product product);

    @Query("DELETE FROM PRODUCT WHERE name=:name_product")
    void deleteProduct(String name_product);

    @Query("DELETE FROM PRODUCT")
    void deleteAllProduct();

    @Query("SELECT * FROM PRODUCT WHERE name=:name_product")
    List<Product> searchProduct(String name_product);
}
