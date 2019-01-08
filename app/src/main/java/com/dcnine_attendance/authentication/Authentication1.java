package com.dcnine_attendance.authentication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.telephony.TelephonyManager;
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

public class Authentication1 extends Activity {

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
        super.onCreate ( savedInstanceState );
        setRequestedOrientation ( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        setContentView ( R.layout.authentication );

        System.out.println ( "123467" );

        session = new SessionManager ( getApplicationContext ( ) );
        TelephonyManager tm = (TelephonyManager)
                getSystemService ( Context.TELEPHONY_SERVICE );

        isDualSimOrNot ( );
        try {
            //	cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            pInfo = getPackageManager ( ).getPackageInfo ( getPackageName ( ), 0 );
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace ( );
        }
        version = pInfo.versionName;


        if (session.isLoggedIn ( )) {
            StartFunction ( );
        } else {
            StartTask ( );
//			 ShowAlertagain();
        }
    }


    private void isDualSimOrNot() {
        TelephonyInfo telephonyInfo = TelephonyInfo.getInstance ( this );
        imeiSIM1 = telephonyInfo.getImeiSIM1 ( );
        imeiSIM2 = telephonyInfo.getImeiSIM2 ( );

    }

    public void getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService ( Context.CONNECTIVITY_SERVICE );

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo ( );
        if (null != activeNetwork) {
            if (activeNetwork.getType ( ) == ConnectivityManager.TYPE_WIFI) {
            }

        } else {

        }

    }

    public void StartTask() {

        CheckAPI ( );
    }


    public void StartFunction() {
        Thread timer = new Thread ( ) {
            public void run()

            {
                try

                {
                    sleep ( 20 );
                    runOnUiThread ( new Runnable ( ) {
                        public void run() {
                            //splash.setImageResource(R.drawable.splash3);
                        }
                    } );
                    sleep ( 20 );
                    runOnUiThread ( new Runnable ( ) {
                        public void run() {
                            if (session.isLoggedIn ( )) {
                                Intent intent = new Intent ( getApplicationContext ( ), Home_Activity.class );
                                startActivity ( intent );
                                finish ( );
                            } else {
                                System.out.println ( "12346789" );
                                new ConfigurationDevice ( ).execute ( );
                            }
                        }
                    } );
                } catch (Exception e) {
                    e.printStackTrace ( );
                }
            }
        };
        timer.start ( );
    }

    public void CheckAPI() {
        new BgAPI ( ).execute ( );
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        Debug.stopMethodTracing ( );
        super.onDestroy ( );
    }

    public class ConfigurationDevice extends AsyncTask <String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            //	ShowAlertagain();
            super.onPreExecute ( );
	      /*  pd=new ProgressDialog(Authentication.this);
	        pd.setMessage("Please Wait for configuration");
	        pd.setCancelable(false);
	        pd.show();*/
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            ArrayList <NameValuePair> nameValuePairs = new ArrayList <NameValuePair> ( );
            nameValuePairs.add ( new BasicNameValuePair ( "current_version", version ) );
            nameValuePairs.add ( new BasicNameValuePair ( "IMEI_One", imeiSIM1 ) );
            //nameValuePairs.add(new BasicNameValuePair("IMEI_Two",imeiSIM2));

            Log.e ( "NameValue", "" + nameValuePairs );
            try {
                HttpClient httpclient = new DefaultHttpClient ( );
                HttpPost httppost = new HttpPost ( "http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/mobile_authentiction" );
                httppost.setEntity ( new UrlEncodedFormEntity ( nameValuePairs ) );
                ResponseHandler <String> responseHandler = new BasicResponseHandler ( );
                response = httpclient.execute ( httppost, responseHandler );
            } catch (Exception e) {
//	        Log.e("log_tag", "Error in http connection "+e.toString());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute ( result );
	       /* pd.hide();
	        pd.dismiss();*/
            //  Log.e("Respp", response);
//	        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            try {

                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject ( response );

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray ( "resp" );

                        int fileLength = contacts.length ( );
                        // for (int i = 0; i < contacts.length(); i++) {
                        if (fileLength == 0) {
                            Toast.makeText ( getApplicationContext ( ), " device not configured ", Toast.LENGTH_LONG ).show ( );


                        } else {
                            JSONObject c = contacts.getJSONObject ( 0 );
                            String mr_codes = c.getString ( "emp_id" );
                            String phone = c.getString ( "office_lat" );
                            String current_url = c.getString ( "office_long" );
                            String project_id = c.getString ( "project_id" );
                            String range_limit = c.getString ( "range_limit" );
                            Log.e ( " EMP id", mr_codes );
                            Log.e ( " phone id", phone );
                            Log.e ( " current_url id", current_url );
                            Log.e ( " project id", project_id );
                            Log.e ( " range limit", range_limit );
                            session.createLoginSession ( mr_codes, phone, current_url, project_id, range_limit );
                            Intent intent = new Intent ( getApplicationContext ( ), Home_Activity.class );
                            startActivity ( intent );
                            finish ( );

                            //Toast.makeText(getApplicationContext(), mr_codes.toString()+"hello"+mr_codes.toString(), Toast.LENGTH_SHORT).show();
                        }
                        // }
                    } catch (JSONException e) {
                        e.printStackTrace ( );
                        Toast.makeText ( getApplicationContext ( ), " device not configured ", Toast.LENGTH_LONG ).show ( );


                        finish ( );
                    }
                } else {
                    //  Log.e("ServiceHandler", "Couldn't get any data from the url");
                }

            } catch (Exception e) {

                Toast.makeText ( getApplicationContext ( ), "please try again", Toast.LENGTH_LONG ).show ( );
                finish ( );
            }
        }
    }


    public class BgAPI extends AsyncTask <String, String, String> {
        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            try {
                HttpClient httpclient = new DefaultHttpClient ( );
                HttpPost httppost = new HttpPost ( "http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/mobile_authentiction" );
                ResponseHandler <String> responseHandler = new BasicResponseHandler ( );
                response_api = httpclient.execute ( httppost, responseHandler );
            } catch (Exception e) {
                e.printStackTrace ( );
            }
            return response_api;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute ( result );
//			Log.e("response_api", ""+response_api);
            if (response_api.equalsIgnoreCase ( "dd" )) {
                Toast.makeText ( getApplicationContext ( ), "Internet not connected / Server is not working", Toast.LENGTH_LONG ).show ( );
                finish ( );
            } else {
                response_api = "dd";
                StartFunction ( );
            }
        }
    }


}