package com.teamapp16.furniturear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class ItemScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);
        HandleIntent();

    }
    private void HandleIntent(){
        // Get Intents
        String ItemName = getIntent().getStringExtra("selected_itemName");
        String ItemImageURL = getIntent().getStringExtra("selected_itemImage");
        // Get View references
        TextView NameTV = findViewById(R.id.name);
        ImageView ItemImageView = findViewById(R.id.image);
        // Link Intent data with Views
        NameTV.setText(ItemName);
        Glide.with(this)
                .asBitmap()
                .load(ItemImageURL)
                .into(ItemImageView);

    }
}
