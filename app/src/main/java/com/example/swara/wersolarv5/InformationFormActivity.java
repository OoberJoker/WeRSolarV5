package com.example.swara.wersolarv5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InformationFormActivity extends AppCompatActivity {

    private ImageView button;
    private String encoded_string,image_name;
    private Bitmap bitmap;
    private File file;
    private Uri file_uri;
    int[] locationImage = {R.drawable.sun};
    int[] cancelImage = {R.drawable.cancel};
    //ArrayList<CustomerImages> customerImagesArrayList;
    ListView lv;
//get images in a bite array. store all bite arrays inside another array.
    //find how you can set imageview image using data in the bite arrays.

    class GetCustomerImagesCount extends AsyncTask<String,String,JSONObject>{
        JSONParser jsonParser = new JSONParser();
        private String dataUrlEndPoint;
        private HashMap<String, String> inputParams;

        public GetCustomerImagesCount(String dataUrl,HashMap<String, String> params){
            dataUrlEndPoint = dataUrl;
            inputParams = params;
        }
        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                jsonParser.makeHttpRequest(dataUrlEndPoint, "POST", inputParams);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject json){
            try {
                JSONObject jsonNew = (JSONObject) new JSONTokener(StaticDataMembers.jsonResults).nextValue();
                JSONObject imageCountObject = jsonNew.getJSONObject("pictureCount");
                StaticDataMembers.jsonCustomerImageCount = (String) imageCountObject.get("result");

//                Log.d("imageCount:->>>>>>>>>",  StaticDataMembers.jsonCustomerImageCount);
            }
            catch (JSONException e){

            }

            for(int i=1;i<Integer.parseInt(StaticDataMembers.jsonCustomerImageCount);i++) {
                StaticDataMembers.customerImagesArrayList.add(new CustomerImages("http://192.168.2.198/wersolar/images/" + Integer.toString(i) + ".jpg"));
            }
            lv = (ListView)findViewById(R.id.informationFormCustomerImagesListViewID);
            CustomListViewImageSetterAdapter customAdapter = new CustomListViewImageSetterAdapter(getApplicationContext(),R.layout.listview_installerform_items,StaticDataMembers.customerImagesArrayList);
            lv.setAdapter(customAdapter);



        }


    }

    public void createListView(){
        StaticDataMembers.customerImagesArrayList = new ArrayList<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("phone",StaticDataMembers.currentUserPhone);
        params.put("get_picture_count", "Y");
        try {
            new  GetCustomerImagesCount(StaticDataMembers.serverInsertPictureUrl,params).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_form);



        Spinner typeofCustomer = (Spinner)findViewById(R.id.installerFormTypeOfUserSpinnerID);
        String[] itemstypeofCustomer = new String[]{"Type of customer","Commercial", "Residential", "Industrial","Institutional"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemstypeofCustomer);
        typeofCustomer.setAdapter(adapter);


        Spinner loadType = (Spinner)findViewById(R.id.installerFormLoadTypeSpinnerID);
        String[] itemsloadType = new String[]{"Load Type","Single Phase","Three Phase"};
        ArrayAdapter<String>  adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsloadType);
        loadType.setAdapter(adapter2);

        Spinner sanctionLoad = (Spinner)findViewById(R.id.installerFormSanctionLoadSpinnerID);
        String[] itemsSanctionLoad = new String[]{"Sanction Load","KW","HP"};
        ArrayAdapter<String>   adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsSanctionLoad);
        sanctionLoad.setAdapter(adapter3);

        Spinner netMeetering = (Spinner)findViewById(R.id.installerFormNetMeteringSpinnerID);
        String[] netmeetering = new String[]{"Net Meetering","Yes","No"};
        ArrayAdapter<String>    adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, netmeetering);
        netMeetering.setAdapter(adapter4);


        Spinner typeOfRoof = (Spinner)findViewById(R.id.installerFormTypeOfRoofSpinnerID);
        String[] typeofRoof = new String[]{"Type of roof","RCC","Slab","Metal/GI sheet","Industrial shed"};
        ArrayAdapter<String>    adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typeofRoof);
        typeOfRoof.setAdapter(adapter5);


        createListView();//creating list view...





        ImageView submitButton = (ImageView)findViewById(R.id.submitIcon);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText informationFormCustomerFirtNameFieldID = (EditText) findViewById(R.id.informationFormCustomerFirstNameFieldID);
                EditText informationFormCustomerLastNameFieldID = (EditText) findViewById(R.id.informationFormCustomerLastNameFieldID);

                EditText informationFormCompanyPersonAddressFieldID = (EditText) findViewById(R.id.informationFormCompanyPersonAddressFieldID);
                EditText informationFormPhoneFieldID = (EditText) findViewById(R.id.informationFormPhoneFieldID);
                EditText informationFormEmailFieldID = (EditText) findViewById(R.id.informationFormEmailFieldID);
                EditText informationFormAvgBillFieldID = (EditText) findViewById(R.id.informationFormAvgBillFieldID);
                EditText installerFormSiteToMeterFieldID = (EditText) findViewById(R.id.installerFormSiteToMeterFieldID);

                Spinner installerFormTypeOfUserSpinnerID = (Spinner) findViewById(R.id.installerFormTypeOfUserSpinnerID);
                Spinner installerFormLoadTypeSpinnerID = (Spinner) findViewById(R.id.installerFormLoadTypeSpinnerID);
                Spinner installerFormSanctionLoadSpinnerID = (Spinner) findViewById(R.id.installerFormSanctionLoadSpinnerID);
                Spinner installerFormNetMeteringSpinnerID = (Spinner) findViewById(R.id.installerFormNetMeteringSpinnerID);
                Spinner installerFormTypeOfRoofSpinnerID = (Spinner) findViewById(R.id.installerFormTypeOfRoofSpinnerID);


                final String customerFirstName = informationFormCustomerFirtNameFieldID.getText().toString();
                final String customerLastName = informationFormCustomerLastNameFieldID.getText().toString();

                final String customerCompanyPersonAddress = informationFormCompanyPersonAddressFieldID.getText().toString();
                final String customerPhone = informationFormPhoneFieldID.getText().toString();
                final String customerEmail = informationFormEmailFieldID.getText().toString();
                final String avgBill = informationFormAvgBillFieldID.getText().toString();
                final String siteToMeter = installerFormSiteToMeterFieldID.getText().toString();

                final String typeOfUser = installerFormTypeOfUserSpinnerID.getSelectedItem().toString();
                final String loadType = installerFormLoadTypeSpinnerID.getSelectedItem().toString();
                final String sanctionLoad = installerFormSanctionLoadSpinnerID.getSelectedItem().toString();
                final String netMetering = installerFormNetMeteringSpinnerID.getSelectedItem().toString();
                final String typeOfRoof = installerFormTypeOfRoofSpinnerID.getSelectedItem().toString();

                final HashMap<String, String> params = new HashMap<>();
                params.put("installerPhone", StaticDataMembers.currentUserPhone);//save this in cache
                params.put("customerFirstName", customerFirstName);
                params.put("customerLastName", customerLastName);
                params.put("customerCompanyPersonAddress", customerCompanyPersonAddress);
                params.put("customerPhone", customerPhone);
                params.put("customerEmail", customerEmail);
                params.put("avgBill", avgBill);
                params.put("siteToMeterDistance", siteToMeter);
                params.put("typeOfUser", typeOfUser);
                params.put("loadType", loadType);
                params.put("sanctionLoad", sanctionLoad);
                params.put("netMetering", netMetering);
                params.put("typeOfRoof", typeOfRoof);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String strTime = sdf.format(c.getTime());
                params.put("deviceTime",strTime);

                sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = sdf.format(c.getTime());
                params.put("deviceDate",strDate);


                try {
                  new PostAsync(StaticDataMembers.serverInsertCustomerDataUrl, params, InformationFormActivity.this).execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
//show images in a list. have a delete option. on click, show image and give an option to delete it or go back.
        ImageView historyIcon = (ImageView)findViewById(R.id.historyIcon);
        historyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final HashMap<String, String> params = new HashMap<>();
                params.put("phone: ", StaticDataMembers.currentUserPhone);

                try {
                    Log.i("phone check: ", StaticDataMembers.currentUserPhone);

                    new PostAsyncHistoryPage(StaticDataMembers.serverTestUrl, params,InformationFormActivity.this,true).execute();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        button = (ImageView)findViewById(R.id.cameraIcon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getFileUri();
                i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                startActivityForResult(i, 10);
            }
        });



    }


    private void getFileUri(){
        image_name = "picture";//this is kind of irrelevant
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator+image_name);

        file_uri = Uri.fromFile(file);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == 10 && resultCode== RESULT_OK){
            new Encode_image().execute();
        }
    }

    private class Encode_image extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void...voids){
            bitmap = BitmapFactory.decodeFile(file_uri.getPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array, 0);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            makeRequest();
        }

        private void makeRequest() {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest request = new StringRequest(Request.Method.POST,StaticDataMembers.serverInsertPictureUrl,
                    new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response){

                        }
                    },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){

                }
            }) {
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    // return super.getParams();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("encoded_string",encoded_string);
                    map.put("image_name",image_name);
                    map.put("phone",StaticDataMembers.currentUserPhone);
                    return map;

                }
            };
            requestQueue.add(request);
        }
    }














}
