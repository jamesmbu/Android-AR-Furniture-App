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

        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));

        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        productList.add(new Product(
                "Computer Desk",
                "https://www.nomadfoods.com/wp-content/uploads/2018/08/placeholder-1-e1533569576673.png",
                "https://github.com/oriconnage/GLTFModels/blob/Master/Simple_Desk/Desk.gltf?raw=true"));
        return productList;
    }
    private void InitializeBitmaps(){
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

    }
    private void InitializeRecyclerView(ActivityMainBinding activityMainBinding){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( getProductList(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
    }


    @Override
    public void onItemClick(int position) {
        //itemNames.get(position);
        //Toast.makeText(this, itemNames.get(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ItemScreen.class);
        intent.putExtra("selected_itemImage",getProductList().get(position).getProductImage());
        intent.putExtra("selected_itemName", getProductList().get(position).getProductName());
        intent.putExtra("Selected_ArURI", getProductList().get(position).getArURI());
        startActivity(intent);
    }
}
