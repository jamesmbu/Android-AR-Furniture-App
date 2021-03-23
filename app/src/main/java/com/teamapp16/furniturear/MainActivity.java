package com.teamapp16.furniturear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    // Attributes for RecyclerView
    //private RecyclerView recyclerView;
    ArrayList<String> itemList = new ArrayList();
    ArrayList<String> imageList = new ArrayList();
    //____________________________


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // test data for recyclerview
        for (int i = 0; i < 20; i++) {

        }
        InitializeBitmaps();


    }
    public void InitializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, itemList, imageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void InitializeBitmaps() {
        imageList.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemList.add("Chair");
        imageList.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemList.add("Table");
        imageList.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemList.add("Bed");
        InitializeRecyclerView();
    }
    /*public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
        private final ArrayList<String[]> itemList;
        private final ArrayList<Integer[]> imageList;
        //private final String[] itemList;
        //private final int[] imageList;
        private LayoutInflater inflater;

        public ItemListAdapter(Context context, ArrayList<String[]> itemList,  ArrayList<Integer[]> imageList) {
            inflater = LayoutInflater.from(context);
            this.itemList = itemList;
            this.imageList = imageList;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.furniture_item,parent,false);
            return new ItemViewHolder(itemView,this);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            *//*String[] nameList = itemList.get(position);
            int[] imgList = imageList.get(position);*//*
            String[] item = itemList.get(position);
            holder.nameTV.setText(item[0]);
            //holder.imageIV.setImageResource();

        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            public final TextView nameTV;
            public final ImageView imageIV;
            final ItemListAdapter adapter;

            public ItemViewHolder(@NonNull View itemView, ItemListAdapter adapter) {
                super(itemView);
                nameTV = itemView.findViewById(R.id.name);
                imageIV = itemView.findViewById(R.id.displayImage);
                this.adapter = adapter;
            }
        }*/

}



