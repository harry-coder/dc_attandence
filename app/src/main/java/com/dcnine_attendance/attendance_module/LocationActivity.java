package com.dcnine_attendance.attendance_module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dcnine_attendance.Home_Activity;
import com.dcnine_attendance.R;
import com.dcnine_attendance.authentication.ConnectionDetector;
import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.location.FusedLocationService;
import com.dcnine_attendance.sqlite_adapter.SQLiteAdapter1;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LocationActivity extends Activity implements
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {



    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    ImageButton btnFusedLocation;
    TextView tvLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;

    ImageButton btn_punchIn, btn_punchOut;
    Button back_btn, pending_btn;
    ConnectionDetector connectionDetector;
    String internet_interrupt = null;

    Location mLocation = null;
    FusedLocationService fusedLocationService;
    String sending_latt, sending_longg;
    String permit_id, str_timestamp;

    double latt, longg;
    String response;
    ProgressDialog pd;

    SessionManager sessionManager;
    SQLiteAdapter1 sqLiteAdapter;

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ...............................");
        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();

        fusedLocationService = new FusedLocationService(this);
        mLocation = fusedLocationService.getLocation();
        connectionDetector = new ConnectionDetector(LocationActivity.this);
        sessionManager = new SessionManager(LocationActivity.this);
        permit_id = sessionManager.GET_EMP_ID();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        setContentView(R.layout.attendance);
        //tvLocation = (TextView) findViewById(R.id.tvLocation);

        btnFusedLocation = (ImageButton) findViewById(R.id.punch_in);
        btnFusedLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                updateUI();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        @SuppressLint("MissingPermission") PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }

    private void updateUI() {
        Log.d(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());
            tvLocation.setText("At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation.getProvider());
            new SendToServer(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()).execute();
            //startService();
        } else {
            Log.d(TAG, "location is null ...............");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }







public class SendToServer extends AsyncTask<String, String, String> {

    ProgressDialog pd;

    // public SendToServer() {
    public SendToServer(Double latitude, Double longitude) {
        // TODO Auto-generated constructor stub
        sending_latt = String.valueOf(latitude);
        sending_longg = String.valueOf(longitude);
//            Log.e("from", "sendtoserver");
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        pd = new ProgressDialog(LocationActivity.this);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        try {


            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/insert_punch_in");
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                nameValuePairs.add(new BasicNameValuePair("permit_id", sessionManager.GET_EMP_ID()));
                nameValuePairs.add(new BasicNameValuePair("zone_id", sessionManager.GET_CURRENT_URL()));
                nameValuePairs.add(new BasicNameValuePair("city_id", sessionManager.GET_PROJECT()));
                nameValuePairs.add(new BasicNameValuePair("mobile_date", str_timestamp));
                nameValuePairs.add(new BasicNameValuePair("latt", sending_latt));
                nameValuePairs.add(new BasicNameValuePair("longg", sending_longg));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                Log.e("name value pair", "" + nameValuePairs);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                response = httpclient.execute(httppost, responseHandler);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("response", "" + response);
                //   Log.e("response", response + "+" + e.getMessage().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        pd.hide();
        pd.dismiss();

        try {
            response = response.trim();
            Log.e("response", "" + response);
            response = response.trim();

            if (response != null && response.equals("1")) {

                // custom dialog
                final Dialog dialog = new Dialog(LocationActivity.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
                dialog.setContentView(R.layout.custom);
//                              dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText(" Punch In Marked ");
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pd.hide();
                        pd.dismiss();
                        dialog.dismiss();
                        startActivity(new Intent(LocationActivity.this, Home_Activity.class));
                        finish();


                    }
                });

                dialog.show();


            } else {

                // Toast.makeText(getApplicationContext(), "record saved due to server error", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Due to low internet connectivity this data would be saved in database", Toast.LENGTH_SHORT).show();
            response = null;
            Log.e("response exception", "" + e.getMessage());
        }
        response = null;
    }

}

}