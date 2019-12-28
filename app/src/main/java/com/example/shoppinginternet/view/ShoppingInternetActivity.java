package com.example.shoppinginternet.view;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ShoppingInternetActivity extends SingleFragmentActivity  {

public  static Intent newIntent(Context context){
    return new Intent(context , ShoppingInternetActivity.class);
}
    @Override
    public Fragment createFragment() {
        return ShoppingInternetFragment.newInstance();
    }
}
