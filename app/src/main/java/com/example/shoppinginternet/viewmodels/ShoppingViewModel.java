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

    public MutableLiveData<List<Product>> getProductLiveDataNew(){
        return productFetcher.getmProductLiveDataNews();
    }
    public MutableLiveData<List<Product>> getProductLiveDataPopular(){
        return productFetcher.getmProductLiveDataPopular();
    }
    public MutableLiveData<List<Product>> getProductLiveDataRate(){
        return productFetcher.getmProductLiveDataRate();
    }

    public void getProductListNew(){
        productFetcher.getProductListNew();
    }
    public void getProductListRate(){
        productFetcher.getProductListRate();
    }
    public void getProductListPopular(){
        productFetcher.getProductListPopular();
    }

}
