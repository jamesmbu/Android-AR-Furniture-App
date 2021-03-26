package com.teamapp16.furniturear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import com.teamapp16.furniturear.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemListener {
    // Attributes for RecyclerView
    private ArrayList<String> itemNames = new ArrayList();
    private ArrayList<String> imageURLs = new ArrayList();
    private ArrayList<String>arURI = new ArrayList();
    //____________________________
    private static final String Tag = MainActivity.class.getSimpleName();

    private  boolean InstallRequested;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        // test data for recyclerview
        // InitializeBitmaps();
        InitializeRecyclerView(activityMainBinding);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(session == null){
            Exception exception = null;
            String message = null;
            try{
                switch (ArCoreApk.getInstance().requestInstall(this,!InstallRequested)){
                    case INSTALLED:
                        break;
                    case INSTALL_REQUESTED:
                        InstallRequested = true;
                        return;
                }

                // Check if App has Camera permissions
                if(!CameraPermissionHelper.hasCameraPermission(this)){
                    CameraPermissionHelper.requestCameraPermission(this);
                    return;
                }

                // Create a new session.
                session = new Session(this);
            }catch (UnavailableArcoreNotInstalledException | UnavailableUserDeclinedInstallationException e){
                message = "You need to Install ARCore!";
                exception = e;
            }
            catch (UnavailableApkTooOldException e){
                message = "You need to Update ARCore!";
                exception = e;
            }
            catch (UnavailableSdkTooOldException e){
                message = "You need to Update ARCore!";
                exception = e;
            }
            catch (UnavailableDeviceNotCompatibleException e) {
                message = "Your Device isn't compatible with ArCore!";
                exception = e;
            } catch (Exception e) {
                message = "Failed to create AR session";
                exception = e;
            }
            if (message != null)
            {
                Log.e(Tag, "Exception creating session", exception);
                return;
            }
        }//end of If

        try{
            // resume the session
            session.resume();
        }catch (CameraNotAvailableException e){
            session = null;
            return;
        }

    }// end of onStart

    private List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();

        //Sofa
        productList.add(new Product(
                "Arm Chair",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Arm%20Chair/armChair.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Arm%20Chair/c_chair_153.gltf?raw=true",
                "If you are looking for a distinctive piece of upholstered furniture which will transform your living room Ark chair will certianly be a good choice. \n" +
                        "Its comfortable, loose seat, as well as loose backrest cushions will ensure pleasant moments spent by the television with a cup of coffee or with your loved ones.\n " +
                        "Its classic form, supported on beech wood legs, will be an ideal addition to any interior." ,
                340.0f));
        productList.add(new Product(
                "Sofa Arm chair",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Sofa/SofaImage.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Sofa/sofa.gltf?raw=true",
                "a perfect compact sofa range with clean lines and tailored twin seam detail on the slim arms.",
                150.0f));
        //Table
        productList.add(new Product(
                "Antique Bedroom Table",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Antique_Bedroom_Table/1000_F_210890405_U6HKPczkTA20YjXgMyDDfX5lzrZSEiAY.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Antique_Bedroom_Table/antique_tall_bedroom_table_1320.gltf?raw=true",
                " Antique bedside table with no drawers with structure veneered in exotic wood and inlaid with precious wood essences. \n " +
                        "Surmounted by a wooden top with an inlay in the center depicting an amphora with flowers and surrounded by light wood reserves.\n ",
                560.00f));
        productList.add(new Product(
                "Computer Desk",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Simple_Desk/TraditionalDesk.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true",
                "Nifty and nice. What a clever streamlined concept this computer desk is with all the benefits of an everyday desk and more ",
                200.00f
        ));
        //Lamp
        productList.add(new Product(
                "Bed Side Lamp",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Lamp/BedSideLamp.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Lamp/a_bedside_lamp_167%20(1).gltf?raw=true",
                "Desk Lamp perfect for any room design",
                100.00f));

        productList.add(new Product(
                "Floor Lamp",
                "https://github.com/oriconnage/GLTFModels/raw/Master/floor%20Lamp/1000_F_182469774_pTfcLxE3f6assuyTZITqSz4yuRIf46Jj.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/floor%20Lamp/Tall_lamp.gltf?raw=true",
                "Versatile in design, this plain chrome floor lamp can be used with any light shade and will suit a host of interiors. " +
                        "Crafted with a simple stem design, it will offer a practical benefit for your home, as it can easily be placed in any living area. ",
                100.00f));
        //Cabinet
        productList.add(new Product(
                "Wood Cabinet",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Ebony_Wood_Cabinet/1000_F_232546420_zKf93vB7ZFBoG3wrlTCyWseC8c2D1tYr.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Ebony_Wood_Cabinet/wood_cabinet.glb?raw=true",
                " designed to be both stylish and super functional, this Cabinet can be positioned to match interiors that are traditional or contemporary. " +
                        "This cabinet is sure to be the centerpiece of any room.",
                1700.00f));
        productList.add(new Product(
                "Ottoman Storage Rectangle",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Ottoman_Storage_Rectangular/1000_F_210882180_iWVxfoqUOB1WIrL2lJ2DCvZibUxyjS0H.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Ottoman_Storage_Rectangular/rectangular_storage_ottoman_1444%20(1).gltf?raw=true",
                "Traditional design gets an on-trend upgrade with this stylish storage ottoman. ",
                456.00f));
        productList.add(new Product(
                "Small Ottoman Storage",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Small_Ottoman_Storage/1000_F_210890734_Id7TjLsWFe3MQMALjjvvTQDaxUeiqFXy.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Small_Ottoman_Storage/small_ottoman_1450.gltf?raw=true",
                "Traditional design gets an on-trend upgrade with this stylish storage ottoman. ",
                345.00f));

        productList.add(new Product(
                "Bookcase",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Bookcase/1000_F_212779774_ny1WCd5mbmsiuGpT3q0vpcu5KHKLETbs.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Bookcase/tall_tiered_bookcase_1383.gltf?raw=true",
                "This stunning large bookcase will provide ample room for your collection of books. ",
                400.00f));

        productList.add(new Product(
                "Vintage Cushion Chair",
                "https://github.com/oriconnage/GLTFModels/raw/Master/Vintage%20Cushioned_Chair/1000_F_210882464_ng6W5Ww13BBvcfgzWtvDELYy98rwOz41.jpg",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Vintage%20Cushioned_Chair/vintage_cushioned_chair_1318.gltf?raw=true",
                "Vintage cushion chair perfect for you room. ",
                100.00f));

        return productList;
    }
    // <-------not using this-------->
   /* private void InitializeBitmaps(){
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Computer Desk");
        arURI.add("https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true");

        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Table");
        arURI.add("");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Pot");
        arURI.add("");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Lamp");
        arURI.add("");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Chair");
        arURI.add("");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Table");
        arURI.add("");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Pot");
        arURI.add("");
        imageURLs.add("https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png");
        itemNames.add("Lamp");
        arURI.add("");

    }*/
    private void InitializeRecyclerView(ActivityMainBinding activityMainBinding){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( getProductList(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
    }


    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, itemNames.get(position), Toast.LENGTH_SHORT).show();

        // Initialise an Intent, supply data, start the new activity
        Intent intent = new Intent(this, ItemScreen.class);
        intent.putExtra("selected_itemImage",getProductList().get(position).getProductImage());
        intent.putExtra("selected_itemName", getProductList().get(position).getProductName());
        intent.putExtra("selected_ArURI", getProductList().get(position).getArURI());
        intent.putExtra("selected_Description",getProductList().get(position).getDescription());
        intent.putExtra("price",getProductList().get(position).getPrice());
        startActivity(intent);
    }
}
