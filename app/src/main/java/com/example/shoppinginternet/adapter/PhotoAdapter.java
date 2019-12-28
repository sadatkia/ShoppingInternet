package com.example.shoppinginternet.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinginternet.R;
import com.example.shoppinginternet.Model.GalleryItem;


import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private Context mContext;
    private List<GalleryItem> mItems;


    public void setItems(List<GalleryItem> items) {
        mItems = items;
    }

    public PhotoAdapter(Context context, List<GalleryItem> items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_photo, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        //holder.bindGalleryItem(mItems.get(position));
        holder.bindGalleryItem();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {

        private ImageView mPhoto;
        private GalleryItem mGalleryItem;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);

            mPhoto = itemView.findViewById(R.id.image_view_photo);
        }

        public void bindGalleryItem() {

           // mGalleryItem = GalleryItem;

           Drawable drawable= mContext.getResources().getDrawable(R.drawable.ax2);

           mPhoto.setImageDrawable(drawable);

        }
    }
}
