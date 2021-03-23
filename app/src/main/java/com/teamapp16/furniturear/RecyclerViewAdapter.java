package com.teamapp16.furniturear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    private final ArrayList<String[]> itemList;
    private final ArrayList<Integer[]> imageList;
    private Context mContext;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, ArrayList<String[]> itemList,  ArrayList<Integer[]> imageList) {
        inflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.imageList = imageList;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_item,parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        String[] item = itemList.get(position);
        holder.nameTV.setText(item[0]);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"D",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameTV;
        public final ImageView imageIV;
        RelativeLayout parentLayout;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name);
            imageIV = itemView.findViewById(R.id.displayImage);
            parentLayout = itemView.findViewById(R.id.layout);
        }
    }
}
