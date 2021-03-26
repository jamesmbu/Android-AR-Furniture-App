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
    private String Description;
    private float Price;

    public Product(String productName,String productImage, String ArURI, String description ,float price)
    {
        this.productName = productName;
        this.productImage= productImage;
        this.ArURI = ArURI;
        this.Description = description;
        this.Price = price;
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


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView , String productImage){
        Glide.with(imageView)
               // .asBitmap()
                .load(productImage)
                .into(imageView);
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
