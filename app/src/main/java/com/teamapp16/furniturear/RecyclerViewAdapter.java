package com.teamapp16.furniturear;

import android.content.Context;
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

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    // Attributes
    private ArrayList<String> _ImageNames = new ArrayList<>(); // Stores a list of the names for the items
    private ArrayList<String> _ImageURLs = new ArrayList<>(); // Stores a list of URLs from which the Glide framework will use to access images
    private Context _Context;
    private OnItemListener _OnItemListener; // Listener which tracks clicks performed on each recycled view

    private static final String TAG = "RecyclerViewAdapter";

    // Constructor - initialises values
    public RecyclerViewAdapter(Context context, ArrayList<String> itemList,  ArrayList<String> imageList, OnItemListener onItemListener) {
        this._ImageNames = itemList;
        this._ImageURLs = imageList;
        _Context = context;
        this._OnItemListener = onItemListener;
    }
    // Inflation of View
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listeditem,parent,false);
        ItemViewHolder holder = new ItemViewHolder(view, _OnItemListener);
        return holder;
    }

    // Link relevant data to respective recycled Views
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        // Set item image
        Glide.with(_Context)
                .asBitmap()
                .load(_ImageURLs.get(position))
                .into(holder.imageView);
        // Set item name
        holder.nameTV.setText(_ImageNames.get(position));

        //String[] item = itemList.get(position);
        //holder.nameTV.setText(item[0]);

        /*holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_Context,"D",Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return _ImageNames.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTV;
        ImageView imageView;
        RelativeLayout parentLayout;
        OnItemListener onItemListener;

        public ItemViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            // Get references to the associated views that we need to supply data to
            nameTV = itemView.findViewById(R.id.image_name);
            imageView = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            // Set the listener, for handling click events
            this.onItemListener = onItemListener;
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
