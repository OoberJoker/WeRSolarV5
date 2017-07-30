package com.example.swara.wersolarv5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by swara on 7/30/2017.
 */
public class CustomListViewImageSetterAdapter extends ArrayAdapter<CustomerImages> {

    ArrayList<CustomerImages> images;
    Context context;
    int resource;

    public CustomListViewImageSetterAdapter(Context context, int resource, ArrayList<CustomerImages> images) {
        super(context, resource, images);
        this.images = images;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_installerform_items,null,true);
        }
        CustomerImages image = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.installerFormListViewImageID);
        Picasso.with(context).load(image.getImage()).into(imageView);

        return convertView;
    }
}
