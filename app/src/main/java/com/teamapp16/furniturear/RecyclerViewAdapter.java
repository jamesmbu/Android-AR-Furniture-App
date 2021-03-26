package com.teamapp16.furniturear;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamapp16.furniturear.databinding.ActivityItemScreenBinding;
import com.teamapp16.furniturear.databinding.LayoutListeditemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    private List<Product> furnitureList; // Stores a list of 'Products' where each product has a name, price, description, etc.
    private OnItemListener _OnItemListener;

    public RecyclerViewAdapter(List<Product>furnitureList, OnItemListener onItemListener) {
        this.furnitureList = furnitureList;
        this._OnItemListener = onItemListener;
    }

    // Inflation of view
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutListeditemBinding layoutListeditemBinding = LayoutListeditemBinding.inflate(layoutInflater,parent,false);
        ActivityItemScreenBinding itemScreenBinding = ActivityItemScreenBinding.inflate(layoutInflater,parent,false);
        return new ItemViewHolder(layoutListeditemBinding,itemScreenBinding,_OnItemListener);
    }

    // Binding data with a respective view holder child
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

    // Blueprint of an item's view and metadata
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ActivityItemScreenBinding activityItemScreenBinding;
        LayoutListeditemBinding layoutListeditemBinding;
        OnItemListener onItemListener;

        public ItemViewHolder( @NonNull LayoutListeditemBinding layoutListeditemBinding,@NonNull ActivityItemScreenBinding activityItemScreenBinding, OnItemListener _onItemListener) {
            super(layoutListeditemBinding.getRoot());
            this.layoutListeditemBinding = layoutListeditemBinding;
            this.onItemListener =_onItemListener;
            this.activityItemScreenBinding = activityItemScreenBinding;
            itemView.setOnClickListener(this);
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
