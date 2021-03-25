package com.teamapp16.furniturear;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teamapp16.furniturear.databinding.ActivityItemScreenBinding;
import com.teamapp16.furniturear.databinding.LayoutListeditemBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";
    private List<Product> furnitureList;


    public RecyclerViewAdapter(List<Product>furnitureList) {
        this.furnitureList = furnitureList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutListeditemBinding layoutListeditemBinding = LayoutListeditemBinding.inflate(layoutInflater,parent,false);
        return new ItemViewHolder(layoutListeditemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {

        final Product product = furnitureList.get(position);
        holder.layoutListeditemBinding.setProduct(product);
        holder.layoutListeditemBinding.executePendingBindings();

        /*
        TODO: Testing uncomment this if it doesn't work!
        Glide.with(_Context)
                .asBitmap()
                .load(_ImageURLs.get(position))
                .into(holder.imageView);

        holder.nameTV.setText(_ImageNames.get(position));
        */

        //String[] item = itemList.get(position);
        //holder.nameTV.setText(item[0]);

        /*holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_Context,"D",Toast.LENGTH_SHORT).show();
            }
        });
        */
    }


    @Override
    public int getItemCount() {
        return furnitureList.size();
        //return _ImageNames.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LayoutListeditemBinding layoutListeditemBinding;
        OnItemListener onItemListener;
     public ItemViewHolder( @NonNull LayoutListeditemBinding layoutListeditemBinding) {
            super(layoutListeditemBinding.getRoot());
            this.layoutListeditemBinding = layoutListeditemBinding;

       /*     layoutListeditemBinding.image.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                   // Intent intent = new Intent(ItemScreen.class);
                }
            });*/
     }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemListener{
        void onItemClick(int position);
    }
}
