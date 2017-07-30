package com.example.swara.wersolarv5;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by swara on 7/21/2017.
 */
public class PostAsyncHistoryPage extends AsyncTask<String, String, JSONObject> {
    JSONParser jsonParser = new JSONParser();
    private String dataUrlEndPoint;
    private HashMap<String, String> inputParams;
    private Activity activity;
    private boolean startIntent = false;


    public PostAsyncHistoryPage(String dataUrl,HashMap<String, String> params,Activity activity,boolean startIntent){
        dataUrlEndPoint = dataUrl;
        inputParams = params;
        this.activity = activity;
        this.startIntent = startIntent;

    }

    @Override
    protected JSONObject doInBackground(String... args) {
        try {
            jsonParser.makeHttpRequest(dataUrlEndPoint, "POST", inputParams);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


   /* protected void onPreExecute() {


    }*/

    protected void onPostExecute(JSONObject json) {
        try {
            String fname = new String();
            Log.d("StaticDataMembers.lo: ",StaticDataMembers.jsonResults);
            //get values from DB....
            JSONArray arr = new JSONArray(StaticDataMembers.jsonResults);
            String len = String.valueOf(arr.length());
            int jsonArrayLenght = Integer.parseInt(len);
            StaticDataMembers.clientFirstNamesArray = new String[jsonArrayLenght];
            StaticDataMembers.clientLastNamesArray = new String[jsonArrayLenght];
            StaticDataMembers.clientInputDateArray = new String[jsonArrayLenght];
            StaticDataMembers.clientTimeStampArray = new String[jsonArrayLenght];
            for(int i=0;i<jsonArrayLenght;i++){
                JSONObject jObj = arr.getJSONObject(i);
                StaticDataMembers.clientFirstNamesArray[i] = jObj.getString("CUSTOMER_FNAME");
                StaticDataMembers.clientLastNamesArray[i] = jObj.getString("CUSTOMER_LNAME");
                StaticDataMembers.clientInputDateArray[i] = jObj.getString("DATECREATED");
                StaticDataMembers.clientTimeStampArray[i] = jObj.getString("TIMESTAMP");
            }
            if(this.startIntent) {
                Intent intent = new Intent(this.activity, HistoryActivity.class);
                this.activity.startActivity(intent);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(StaticDataMembers.jsonResults.equals("success")){
            Log.i("reached stage 2", "yesssssssssssssssssssssss");
           /// Intent intent = new Intent(this.activity, InformationFormActivity.class);
            //this.activity.startActivity(intent);
        }

    }




}
