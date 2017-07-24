package com.example.swara.wersolarv5;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;

//what would you like to do? Check your listings? Post a service?
public class PostAsync extends AsyncTask<String, String, JSONObject> {
    JSONParser jsonParser = new JSONParser();
    private String dataUrlEndPoint;
    private HashMap<String, String> inputParams;
    private Activity activity;

    public PostAsync(String dataUrl,HashMap<String, String> params,Activity activity){
        dataUrlEndPoint = dataUrl;
        inputParams = params;
        this.activity = activity;

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

    protected void onPostExecute(JSONObject json) {
        try {
            JSONObject jsonNew = (JSONObject) new JSONTokener(StaticDataMembers.jsonResults).nextValue();
            JSONObject loginResultObject = jsonNew.getJSONObject("results");
            StaticDataMembers.jsonResults = (String) loginResultObject.get("result");
            StaticDataMembers.currentUserPhone = (String) loginResultObject.get("phone");//better to just pull phone number for now....
            Log.i("reached statement: ",StaticDataMembers.jsonResults);
            Log.i("reached stage 1","yesssssssssssssssssssssss");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(StaticDataMembers.jsonResults.equals("success")){
            Log.i("reached stage 2", "yesssssssssssssssssssssss");
            Intent intent = new Intent(this.activity, InformationFormActivity.class);
            this.activity.startActivity(intent);
        }

    }




}
