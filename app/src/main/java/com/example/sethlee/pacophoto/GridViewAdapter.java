package com.example.sethlee.pacophoto;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sethlee on 11/17/16.
 */
public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private int fileNum;
    private List<String> imagePaths;

    public GridViewAdapter(Context context, List<String> imagePaths) {
        super(context, R.layout.grid_item_layout, imagePaths);
        this.context = context;
        this.imagePaths = imagePaths;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.galleryitem, parent, false);
        }
            Picasso
                    .with(context)
                    .load(new File(imagePaths.get(position)))
                    .fit() // will explain later
                    .into((ImageView) convertView);
            return convertView;

    }
}