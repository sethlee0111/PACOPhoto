package com.example.sethlee.pacophoto;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private int count;
    private Bitmap[] thumbnails;
    private String[] arrPath;
    //private ImageAdapter imageAdapter;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    public void openCamera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
    private GridView gridView;
    private ListView listView;
    private GridViewAdapter gridAdapter;
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        //View = (ListView) findViewById(R.id.list_view);
        //listView.setAdapter(new GridViewAdapter(MainActivity.this, getListPaths(getFilesDir())));
        gridView = (GridView) findViewById(R.id.pacogallery);
        gridAdapter = new GridViewAdapter(this, getListPaths(getFilesDir()));
        gridView.setAdapter(gridAdapter);
    }
    
    private List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".jpg")){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    //private int fileNum;
    private List<String> getListPaths(File parentDir) {
        List<String> Paths = new ArrayList<>();
        File[] files = parentDir.listFiles();
        ArrayList<File> inFiles = new ArrayList<File>();
        String directoryPath = parentDir.getPath();
        for (File file : files) {
            /* previous code including all the files in lower directory */
            Log.d("file_tag",file.getPath());
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".jpg")){
                    inFiles.add(file);
                }
            }
        }
        for(File files2 : inFiles) {
            Paths.add(files2.getPath());
            Log.d("jpg_file_tag",files2.getPath());
        }
        return Paths;
    }
}
