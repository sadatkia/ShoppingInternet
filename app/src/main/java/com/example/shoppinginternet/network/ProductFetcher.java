package com.example.shoppinginternet.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.shoppinginternet.model.Product;
import com.example.shoppinginternet.network.Retrofit.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFetcher {

    private static ProductFetcher instance;

    private MutableLiveData<List<Product>> mProductLiveDataNews = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mProductLiveDataPopular = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mProductLiveDataRate = new MutableLiveData<>();

    public static final String TAG = "FlickrFetcher";
    public static final String Customer_KEY="ck_ca237326f289cfbcfc1c2be0dec147ed53ca6d71";
    public static final String Cusomer_secret="cs_dd9277e750a9c8e832c2f175ee5d7eff2586f1c1";

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";

    private Map<String, String> mQueries;
    private Retrofit mRetrofit;
    private ProductService mFlickrService;

    public static ProductFetcher getInstance() {
        if (instance == null)
            instance = new ProductFetcher();
        return instance;
    }

    private ProductFetcher() {

        mQueries = new HashMap<String, String>() {{
            put("consumer_key", Customer_KEY);
            put("consumer_secret", Cusomer_secret);

        }};

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mFlickrService = mRetrofit
                .create(ProductService.class);
    }

    public MutableLiveData<List<Product>> getmProductLiveDataNews() {
        return mProductLiveDataNews;
    }

    public MutableLiveData<List<Product>> getmProductLiveDataPopular() {
        return mProductLiveDataPopular;
    }

    public MutableLiveData<List<Product>> getmProductLiveDataRate() {
        return mProductLiveDataRate;
    }
    /*
    public void searchPhotos(String query) {
//        String url = buildUrl(SEARCH_PHOTOS_METHOD, query);
//        return downloadGalleryItems(url);
        mQueries.put("method", SEARCH_PHOTOS_METHOD);
        mQueries.put("text", query);
        Call<FlickrBody> call = mFlickrService.getBody(mQueries);

        call.enqueue(getRetrofitCallback());
    }*/

    public void getProductListNew(){


        mFlickrService.getResponse(mQueries).enqueue(getRetrofitCallback("New"));

    }
    public void getProductListPopular(){
        HashMap<String ,String>mNewQueries=new HashMap<>();
        mNewQueries.putAll(mQueries);

        mNewQueries.put("orderby","popularity");
        mFlickrService.getResponse(mNewQueries).enqueue(getRetrofitCallback("popularity"));

    }
public void getProductListRate(){
    HashMap<String ,String>mNewQueries=new HashMap<>();
    mNewQueries.putAll(mQueries);
    mNewQueries.put("orderby","rating");
    mFlickrService.getResponse(mNewQueries).enqueue(getRetrofitCallback("rating"));

}

    private Callback<List<Product>> getRetrofitCallback(final String s) {
        return new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d(TAG, "onResponse: " + response.message());

                if (response.isSuccessful()) {
                    Log.d(TAG, "isSuccessful: ");

                    List<Product> products = response.body();
                    if(s.equals("New"))
                    mProductLiveDataNews.setValue(products);
                   else if (s.equals("popularity"))
                    mProductLiveDataPopular.setValue(products);
                   else if (s.equals("rating"))
                    mProductLiveDataRate.setValue(products);

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        };
    }


}
