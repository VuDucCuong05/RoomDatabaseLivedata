package com.example.roomdatabaselivedata.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabaselivedata.Repository.ProductRepository;
import com.example.roomdatabaselivedata.model.Product;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> mAllProduct;

    public ProductViewModel(Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllProduct = mRepository.getAllProduct();
    }


    public LiveData<List<Product>> getAllProduct() {
        return mAllProduct;
    }

    public void insertProduct(Product product) {
        mRepository.insertProdcut1(product);
    }

    public void updateProduct(Product product){
        mRepository.updateProduct(product);
    }

    public void deleteProduct(Product product){
        mRepository.deleteProduct(product);
    }

    public void deleteAllProduct(){
        mRepository.deleteAllProduct();
    }

}
