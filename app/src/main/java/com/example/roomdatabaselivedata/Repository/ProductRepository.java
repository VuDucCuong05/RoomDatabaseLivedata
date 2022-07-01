package com.example.roomdatabaselivedata.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.roomdatabaselivedata.database.ProductDAO;
import com.example.roomdatabaselivedata.database.ProductDataBase;
import com.example.roomdatabaselivedata.model.Product;

import java.util.List;

public class ProductRepository {

    private ProductDAO mProductDAO;
    private LiveData<List<Product>> mAllProduct;

    public ProductRepository(Application application){
        ProductDataBase db = ProductDataBase.getInstance(application);
        mProductDAO = db.productDAO();
        mAllProduct = mProductDAO.getAllProduct();
    }

    public LiveData<List<Product>> getAllProduct(){
        return mAllProduct;
    }

    public void insertProdcut1(Product product){
        new insertAsyncTask(mProductDAO).execute(product);
    }

    public void updateProduct(Product product){
        new updateAsyncTask(mProductDAO).execute(product);
    }

    public void deleteProduct(Product product){
        new deleteAsyncTask(mProductDAO).execute(product);
    }

    public void deleteAllProduct() {
        new deleteAllAsyncTask(mProductDAO).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDAO mAsyncTaskDao;

        insertAsyncTask(ProductDAO dao){
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Product... products) {
            mAsyncTaskDao.insertProduct(products[0]);
            return null;
        }
    }


    private static class updateAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDAO mAsyncTaskDao;

        updateAsyncTask(ProductDAO dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            mAsyncTaskDao.updateProduct(products[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDAO mAsysncTaskDao;

        deleteAsyncTask(ProductDAO dao){
            mAsysncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            mAsysncTaskDao.deleteProduct(products[0].getName());
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProductDAO mAsysncTaskDao;
        // khởi tạo có đối
        deleteAllAsyncTask(ProductDAO dao){
            mAsysncTaskDao = dao;
        }

        // phần công việc chạy trong background
        @Override
        protected Void doInBackground(Void... voids) {
            mAsysncTaskDao.deleteAllProduct();
            return null;
        }


    }



}
