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
    private ArrayList<String> itemNames = new ArrayList();
    private ArrayList<String> imageURLs = new ArrayList();
    //____________________________


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // test data for recyclerview
        InitializeBitmaps();

    }

    private void InitializeBitmaps(){
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Chair");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Table");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Chair");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Chair");
        InitializeRecyclerView();
    }
    private void InitializeRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, itemNames, imageURLs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
