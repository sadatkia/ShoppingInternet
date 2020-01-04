package com.example.shoppinginternet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinginternet.R;
import com.example.shoppinginternet.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private Context mContext;
    private List<Product> mItems;


    public void setItems(List<Product> items) {
        mItems = items;
    }

    public PhotoAdapter(Context context, List<Product> items) {
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
        //holder.bindResponse(mItems.get(position));
        holder.bindResponse(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {

        private ImageView mPhoto;
        private Product mProduct;
        private TextView mTextView;
        private TextView mTextView2;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);

            mPhoto = itemView.findViewById(R.id.image_view_photo);
            mTextView=itemView.findViewById(R.id.textView);
            mTextView2=itemView.findViewById(R.id.textView2);
        }

        public void bindResponse(Product  mProductt) {

         /* *//* mProduct = Product;
*//*
           Drawable drawable= mContext.getResources().getDrawable(R.drawable.ax2);

           mPhoto.setImageDrawable(drawable);*/

            mProduct = mProductt;
mTextView.setText(mProduct.getName());
mTextView2.setText(mProduct.getPrice());
            Picasso.with(mContext)
                    .load(mProduct.getImages().get(0).getSrc())
                    .placeholder(R.drawable.ax2)
                    .into(mPhoto);


        }
    }
}
