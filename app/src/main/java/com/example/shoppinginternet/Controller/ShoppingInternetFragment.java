package com.example.shoppinginternet.Controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.shoppinginternet.Model.GalleryItem;
import com.example.shoppinginternet.R;
import com.example.shoppinginternet.adapter.PhotoAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingInternetFragment extends Fragment {

    public static final String TAG = "This is URL Digikala";
    private RecyclerView mRecyclerView;
    private List<GalleryItem> mItems = new ArrayList<>();
    private PhotoAdapter mAdapter;
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

       /* FlickrTask flickrTask=new FlickrTask();
         flickrTask.execute();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<GalleryItem> galleryItems=new ArrayList<GalleryItem>();

        super.onCreate(savedInstanceState);
        SliderLayout mDemoSlider;


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shopping_internet, container, false);

        initUI(view);
        setupAdapter(galleryItems);


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
       // mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
       // mDemoSlider.addOnPageChangeListener(getActivity());
     /*   ListView l = view.findViewById(R.id.ax5);
        l.setAdapter(new TransformerAdapter(this));
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
                Toast.makeText(SingleFragmentActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
            }*/
      //  });





         return view;
    }

    private void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_photo_Shopping);
       LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setupAdapter(List<GalleryItem> items) {
        if (mAdapter == null) {
            mAdapter = new PhotoAdapter(getContext(), items);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setItems(items);
            mAdapter.notifyDataSetChanged();
        }
    }

}


