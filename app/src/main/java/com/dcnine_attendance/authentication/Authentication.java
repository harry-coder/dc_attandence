package com.dcnine_attendance.authentication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dcnine_attendance.Home_Activity;
import com.dcnine_attendance.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Authentication extends AppCompatActivity {

    SessionManager session;
    String password;

    String response;
    public static String imeiSIM1;
    public static String imeiSIM2;


    String version;
    PackageInfo pInfo;
    String response_api = "dd";

    String function = "mobile_authentiction";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.authentication);
        //System.out.println("123467");

       session = new SessionManager(getApplicationContext());

        try {
            // cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        version = pInfo.versionName;

        runTimePermission();

        // isDualSimOrNot();
    }


    @SuppressLint({"MissingPermission"})
    private void isDualSimOrNot() {

        //System.out.println("Inside dual not");
        //just request the permission


        TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);
        imeiSIM1 = telephonyInfo.getImeiSIM1();
        imeiSIM2 = telephonyInfo.getImeiSIM2();
       // System.out.println("This is imei one " + imeiSIM1);
        //System.out.println("This is imei two " + imeiSIM2);


        if (session.isLoggedIn()) {
            StartFunction();

            System.out.println("Inside logged in");
        } else {
            System.out.println("Inside startTask");
            StartTask();
           //  ShowAlertagain();
        }

    }

    public void getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
            }

        } else {

        }

    }

    public void StartTask() {

        CheckAPI();
    }


    public void StartFunction() {
        Thread timer = new Thread() {
            public void run()

            {
                try

                {
                    sleep(20);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            //splash.setImageResource(R.drawable.splash3);
                        }
                    });
                    sleep(20);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (session.isLoggedIn()) {
                                Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //System.out.println("12346789");
                                new ConfigurationDevice().execute();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

    public void CheckAPI() {
        new BgAPI().execute();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        Debug.stopMethodTracing();
        super.onDestroy();
    }

    public class ConfigurationDevice extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            // ShowAlertagain();
            super.onPreExecute();
         /*  pd=new ProgressDialog(Authentication.this);
           pd.setMessage("Please Wait for configuration");
           pd.setCancelable(false);
           pd.show();*/
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("current_version", version));
            nameValuePairs.add(new BasicNameValuePair("IMEI_One", imeiSIM1));
            //nameValuePairs.add(new BasicNameValuePair("IMEI_Two",imeiSIM2));

            Log.e("NameValue", "" + nameValuePairs);
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/mobile_authentiction");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                response = httpclient.execute(httppost, responseHandler);
            } catch (Exception e) {
//         Log.e("log_tag", "Error in http connection "+e.toString());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
          /* pd.hide();
           pd.dismiss();*/
            //  Log.e("Respp", response);
//         Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
/*
            try {
*/


            System.out.println("This is first time");
            if (response != null) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("resp");

                    int fileLength = contacts.length();
                    // for (int i = 0; i < contacts.length(); i++) {
                    if (fileLength == 0) {
                        Toast.makeText(getApplicationContext(), " device not configured ", Toast.LENGTH_LONG).show();

                    } else {

                        JSONObject c = contacts.getJSONObject(0);
                        String mr_codes = c.getString("emp_id");
                        String phone = c.getString("office_lat");
                        String current_url = c.getString("office_long");
                        String project_id = c.getString("project_id");
                        String range_limit = c.getString("range_limit");
                        Log.e(" EMP id", mr_codes);
                        Log.e(" phone id", phone);
                        Log.e(" current_url id", current_url);
                        Log.e(" project id", project_id);
                        Log.e(" range limit", range_limit);
                        session.createLoginSession(mr_codes,phone,current_url,project_id,range_limit);

                        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                        startActivity(intent);
                        finish();



                        //Toast.makeText(getApplicationContext(), DataHolder_SiteInspection.getInstance().getC_date(), Toast.LENGTH_SHORT).show();
                    }
                    // }
                } catch (JSONException e) {
                    e.printStackTrace();



                  /*  Toast.makeText(getApplicationContext(), " device not configured  ", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                    startActivity(intent);
*/                    finish();

                    //                    finish();
                }
            } else {
                //  Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

        } /*catch (Exception e) {

                Toast.makeText(getApplicationContext(), "please try again", Toast.LENGTH_LONG).show();
                finish();
            }*/
    }


    public class BgAPI extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/mobile_authentiction");
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                response_api = httpclient.execute(httppost, responseHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response_api;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
//       Log.e("response_api", ""+response_api);
            if (response_api.equalsIgnoreCase("dd")) {
                Toast.makeText(getApplicationContext(), "Internet not connected / Server is not working", Toast.LENGTH_LONG).show();
                finish();
            } else {
                response_api = "dd";
                StartFunction();
            }
        }
    }

    //here we are specifying the permissions we have to ask to the user..
    public boolean runTimePermission() {

        System.out.println("Inside the permissons");
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED

                ) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_PHONE_STATE}, 100);

            System.out.println("This is inside per requested");
            return true;
        }
        System.out.println("This is inside per taken");
        isDualSimOrNot();
        return false;

    }

    //here we are checking if the permission is taken or not...
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED && grantResults[4] == PackageManager.PERMISSION_GRANTED && grantResults[5] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

                isDualSimOrNot();
                System.out.println("This is inside permission");
            } else {

                runTimePermission();

            }

        }

    }

}

