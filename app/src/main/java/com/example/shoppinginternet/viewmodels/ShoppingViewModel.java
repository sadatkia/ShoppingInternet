package com.example.shoppinginternet.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppinginternet.model.Product;
import com.example.shoppinginternet.network.ProductFetcher;

import java.util.List;

public class ShoppingViewModel extends AndroidViewModel {

    private ProductFetcher productFetcher = ProductFetcher.getInstance();

    public ShoppingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Product>> getProductLiveData(){
        return productFetcher.getmProductLiveData();
    }

    public void getProductList(){
        productFetcher.getProductList();
    }
}
