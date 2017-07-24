package com.example.swara.wersolarv5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by swara on 7/20/2017.
 */
public class PersonListAdapter extends ArrayAdapter<Person> {

    private Context mContext;
    int mResource;

    public PersonListAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       String name = getItem(position).getName();
       String address = getItem(position).getAddress();
       String date = getItem(position).getDate();

        Person person = new Person(name,address,date);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);


        TextView tvName = (TextView)convertView.findViewById(R.id.historyPageFullNameIDField);
        TextView tvAddress = (TextView)convertView.findViewById(R.id.historyPageAddressIDField);
        TextView tvDate = (TextView)convertView.findViewById(R.id.historyPageDateIDField);

        tvName.setText(name);
        tvAddress.setText(address);
        tvDate.setText(date);

        return convertView;
    }
}
