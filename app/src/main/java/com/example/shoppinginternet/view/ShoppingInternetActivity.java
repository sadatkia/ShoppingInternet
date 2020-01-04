package com.example.shoppinginternet.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.shoppinginternet.R;
import com.google.android.material.navigation.NavigationView;

public class ShoppingInternetActivity extends SingleFragmentActivity  {

public  static Intent newIntent(Context context){
    return new Intent(context , ShoppingInternetActivity.class);
}

private Toolbar mToolbar;
private DrawerLayout mDrawerLayout;
private NavigationView mNavigationView;

    @Override
    public Fragment createFragment() {
        return ShoppingInternetFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoping_internet_activity);
        initUi();
        setNavigationDrawer();

    }

    private void setNavigationDrawer(){
    setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initUi(){
        mToolbar=findViewById(R.id.toolbar_shopping_activity);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_drawer);
    }
}
