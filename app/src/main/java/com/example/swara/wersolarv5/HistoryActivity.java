package com.example.swara.wersolarv5;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);







        final ArrayList<Person> peopleList = new ArrayList<>();
        ListView mListView = (ListView)findViewById(R.id.historyPageListViewID);
        for(int i=0;i<StaticDataMembers.clientFirstNamesArray.length;i++){
            Log.d("client_first_names:", StaticDataMembers.clientFirstNamesArray[i]);
            Person data = new Person(StaticDataMembers.clientFirstNamesArray[i],StaticDataMembers.clientLastNamesArray[i],
                                     StaticDataMembers.clientInputDateArray[i]);
            peopleList.add(data);
        }
        PersonListAdapter adapter = new PersonListAdapter(this,R.layout.listview_relativelayout_items,peopleList);
        mListView.setAdapter(adapter);










        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //from position,get name and address and pass that array to the edit page
                Log.d("listItem: ",peopleList.get(position).getName());
                Snackbar.make(view, "works!!",Snackbar.LENGTH_SHORT).show();
            }
        });








    }

}
