package com.example.roomdatabaselivedata.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabaselivedata.model.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDataBase extends RoomDatabase {

    private static final String DataBaseProduct = "DataBaseProduct";

    public abstract ProductDAO productDAO();

    private static ProductDataBase mInstance;

    public static synchronized ProductDataBase getInstance(Context context) {
        if (mInstance == null) {
            // Create database here
            mInstance = Room.databaseBuilder(context.getApplicationContext(), ProductDataBase.class, DataBaseProduct)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }

}
