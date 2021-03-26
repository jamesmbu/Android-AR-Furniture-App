package com.teamapp16.furniturear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamapp16.furniturear.databinding.ActivityItemScreenBinding;


public class ItemScreen extends AppCompatActivity {

    private  String ItemArURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityItemScreenBinding itemScreenBinding = ActivityItemScreenBinding.inflate(getLayoutInflater());
        setContentView(itemScreenBinding.getRoot());

        HandleIntent();

        Button ActiveAr_button = findViewById(R.id.Ar_Button);
        ActiveAr_button.setOnClickListener(
                view -> {
                    Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                    Uri intentUri =
                            Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                                    .appendQueryParameter("file", ItemArURI )
                                    .appendQueryParameter("mode", "ar_preferred")
                                    .build();

                    sceneViewerIntent.setData(intentUri);
                    sceneViewerIntent.setPackage("com.google.ar.core");
                    startActivity(sceneViewerIntent);
                }
        );
    }
    private void HandleIntent(){

        //Get Intents
        String ItemName = getIntent().getStringExtra("selected_itemName");
        String ItemImageURL = getIntent().getStringExtra("selected_itemImage");
        ItemArURI = getIntent().getStringExtra("selected_ArURI");
        String ItemDescription = getIntent().getStringExtra("selected_Description");
        Float ItemPrice = getIntent().getFloatExtra ("price",100);

        // Get View references
        TextView NameTV = findViewById(R.id.name);
        ImageView ItemImageView = findViewById(R.id.imageView);

        TextView descriptionTV = findViewById(R.id.DescriptionText);
        TextView Price = findViewById(R.id.PriceTag);

        // Link Intent data with Views
        NameTV.setText(ItemName);
        descriptionTV.setText(ItemDescription);
        Price.setText("£"+ItemPrice.toString());
        Glide.with(this)
                .asBitmap()
                .load(ItemImageURL)
                .into(ItemImageView);

    }
}
