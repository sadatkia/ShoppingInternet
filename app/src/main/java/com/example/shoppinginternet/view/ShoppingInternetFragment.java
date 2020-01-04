package com.example.shoppinginternet.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.shoppinginternet.R;
import com.example.shoppinginternet.adapter.PhotoAdapter;
import com.example.shoppinginternet.model.Product;
import com.example.shoppinginternet.viewmodels.ShoppingViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingInternetFragment extends Fragment {

    public static final String TAG = "This is URL Digikala";
    private ShoppingViewModel viewModel;
    private RecyclerView mRecyclerViewNew;
    private RecyclerView mRecyclerViewPopular;

    private RecyclerView mRecyclerViewRate;

    private List<Product> mItems = new ArrayList<>();
    private PhotoAdapter mAdapterNew;
    private PhotoAdapter mAdapterPopular;
    private PhotoAdapter mAdapterRate;
////////////////////////////////////////////////////////////////////////////////
    public static ShoppingInternetFragment newInstance() {

        Bundle args = new Bundle();

        ShoppingInternetFragment fragment = new ShoppingInternetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ShoppingInternetFragment() {
        // Required empty public constructor
    }

    ////////////////////////////Used FlickerFecher for geting Url from Digikala


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        viewModel = ViewModelProviders.of(this).get(ShoppingViewModel.class);
        viewModel.getProductListNew();
        viewModel.getProductListPopular();
        viewModel.getProductListRate();

       /* FlickrTask flickrTask=new FlickrTask();
         flickrTask.execute();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        List<Product> respons =new ArrayList<Product>();

        super.onCreate(savedInstanceState);
        SliderLayout mDemoSlider;


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shopping_internet, container, false);

        initUINew(view);
        initUIRate(view);
        initUIPopular(view);

        viewModel.getProductLiveDataNew().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                setupAdapterNew(products);
            }
        });
        viewModel.getProductLiveDataPopular().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                setupAdapterPopular(products);
            }
        });
        viewModel.getProductLiveDataRate().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                setupAdapterRate(products);
            }
        });


        // setupAdapter(respons);


       // setContentView(R.layout.activity_main);
        mDemoSlider = view.findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

      HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.ax);
        file_maps.put("Big Bang Theory",R.drawable.ax2);
        file_maps.put("House of Cards",R.drawable.ax3);
        file_maps.put("Game of Thrones", R.drawable.ax4);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
                    //.setOnSliderClickListener((BaseSliderView.OnSliderClickListener) getActivity());

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        mDemoSlider.setDuration(4000);

         return view;
    }

    private void initUINew(View view) {
        mRecyclerViewNew = view.findViewById(R.id.recycler_view_photo_Shopping);
       LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewNew.setLayoutManager(layoutManager);
    }
    private void initUIRate(View view) {
        mRecyclerViewRate = view.findViewById(R.id.recycler_view_photo_Shopping_Rate);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewRate.setLayoutManager(layoutManager);
    }
    private void initUIPopular(View view) {
        mRecyclerViewPopular = view.findViewById(R.id.recycler_view_photo_Shopping_Popular);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewPopular.setLayoutManager(layoutManager);
    }


    private void setupAdapterNew(List<Product> items) {
        if (mAdapterNew == null) {
            mAdapterNew = new PhotoAdapter(getContext(), items);
            mRecyclerViewNew.setAdapter(mAdapterNew);
        } else {
            mAdapterNew.setItems(items);
            mAdapterNew.notifyDataSetChanged();
        }
    }

    private void setupAdapterPopular(List<Product> items) {
        if (mAdapterPopular == null) {
            mAdapterPopular = new PhotoAdapter(getContext(), items);
            mRecyclerViewPopular.setAdapter(mAdapterPopular);
        } else {
            mAdapterPopular.setItems(items);
            mAdapterPopular.notifyDataSetChanged();
        }
    }

    private void setupAdapterRate(List<Product> items) {
        if (mAdapterRate == null) {
            mAdapterRate = new PhotoAdapter(getContext(), items);
            mRecyclerViewRate.setAdapter(mAdapterRate);
        } else {
            mAdapterRate.setItems(items);
            mAdapterRate.notifyDataSetChanged();
        }
    }

}


