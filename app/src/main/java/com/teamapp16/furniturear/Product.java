package com.teamapp16.furniturear;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;


public class Product {



    private String productName;
    private String productImage;
    private String ArURI;

    public Product(String productName,String productImage, String ArURI)
    {
        this.productName = productName;
        this.productImage= productImage;
        this.ArURI = ArURI;
    }


    // --------- Getter and setters -------------
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getArURI() {
        return ArURI;
    }

    public void setArURI(String arURI) {
        ArURI = arURI;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView view , String productImage){
        Glide.with(view)
                .load(productImage)
                .into(view);
    }

    @BindingAdapter("android:ArURI")
    public static void loadARURI(Button button, String ArURI){
        button.setOnClickListener(
                view -> {
                    Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                    Uri intentUri =
                            Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                            .appendQueryParameter("file", ArURI )
                            .appendQueryParameter("mode", "ar_preferred")
                            .build();
                 sceneViewerIntent.setData(intentUri);
                 sceneViewerIntent.setPackage("com.google.ar.core");
                // startActivity(sceneViewerIntent);
                });
    }
}
