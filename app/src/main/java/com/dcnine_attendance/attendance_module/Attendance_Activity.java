package com.dcnine_attendance.attendance_module;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dcnine_attendance.CustomClasses.UserLockBottomSheetBehaviour;
import com.dcnine_attendance.Home_Activity;
import com.dcnine_attendance.R;
import com.dcnine_attendance.authentication.ConnectionDetector;
import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.data_holder.DataHolder_SiteInspection;
import com.dcnine_attendance.location.FusedLocationService;
import com.dcnine_attendance.service.GeoFenceTransitionService;
import com.dcnine_attendance.sqlite_adapter.SQLiteAdapter1;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nitinb on 27-01-2016.
 */
public class Attendance_Activity extends Activity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    ImageButton btn_punchIn, btn_punchOut, btn_punchOD;
    Button back_btn, pending_btn, od_cancel, od_punchin;
    LinearLayout pop_od;
    EditText ed_remark, ed_place;
    String str_remark, str_place;
    int meterInDec_in, meterInDec_out, meterInDec_od;

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

    TextView tv_timer;
    CountDownTimer timer;

    //Initialisation for location api
    Location lastLocation;
    LocationRequest mLocationRequest;
    LocationCallback locationCallback;
    private FusedLocationProviderClient mFusedLocationClient;

    private MapFragment mapFragment;
    private GoogleMap map;

    private Marker locationMarker;
    private Marker geoFenceMarker;

    private static final long GEO_DURATION = 60 * 60 * 1000;
    private static final String GEOFENCE_REQ_ID = "My Geofence";
    private static final float GEOFENCE_RADIUS = 200.0f; // in meters

    private PendingIntent geoFencePendingIntent;
    private final int GEOFENCE_REQ_CODE = 0;


    UserLockBottomSheetBehaviour bottomSheetBehavior;
    View bottomSheet;
    public static GeoFenceResultReceiver geoFenceResultReceiver;

    public static boolean isPounchOut, isPounchIn;

    String lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.attendance );

        geoFenceResultReceiver = new GeoFenceResultReceiver ( new Handler ( ) );


        sqLiteAdapter = new SQLiteAdapter1 ( Attendance_Activity.this );


        back_btn = findViewById ( R.id.backpageId2 );
        back_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

               /* Intent i = new Intent(Attendance_Activity.this, Home_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
            }
        } );

        fusedLocationService = new FusedLocationService ( this );
        mLocation = fusedLocationService.getLocation ( );
        connectionDetector = new ConnectionDetector ( Attendance_Activity.this );
        sessionManager = new SessionManager ( Attendance_Activity.this );
        permit_id = sessionManager.GET_EMP_ID ( );

        pending_btn = findViewById ( R.id.pending );
        pop_od = findViewById ( R.id.pop_od );

        //pending_btn.setVisibility(View.INVISIBLE);
        pop_od.setVisibility ( View.GONE );


        bottomSheet = findViewById ( R.id.map );
        bottomSheetBehavior = (UserLockBottomSheetBehaviour) UserLockBottomSheetBehaviour.from ( bottomSheet );

        tv_timer = findViewById ( R.id.tv_timer );

//        floatingActionButtonTimer=findViewById ( R.id.fab );
        // floatingActionButtonTimer.setT


        //---------------for OD-------------------//
        //------------ For Punch In Button--------------------------//
        btn_punchOD = findViewById ( R.id.punch_od );

        btn_punchOD.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                if (!isMockSettingsON ( Attendance_Activity.this, mLocation )) {


                   // startActivity ( new Intent ( Attendance_Activity.this, Signature_ODActivity.class ) );


                    pop_od.setVisibility ( View.VISIBLE );
                    ed_remark = findViewById ( R.id.ed_remark );
                    ed_place = findViewById ( R.id.ed_place );

                 /*   od_cancel.setOnClickListener ( new View.OnClickListener ( ) {
                        @Override
                        public void onClick(View v) {


                        }
                    } );*/

                    od_punchin = findViewById ( R.id.punch_yes );
                    od_punchin.setOnClickListener ( new View.OnClickListener ( ) {
                        @Override
                        public void onClick(View v) {

                            if (TextUtils.isEmpty ( ed_remark.getText ( ).toString ( ).trim ( ) ) || TextUtils.isEmpty ( ed_place.getText ( ).toString ( ).trim ( ) )) {
                                final Dialog dialog = new Dialog ( Attendance_Activity.this );
                                dialog.getWindow ( ).setBackgroundDrawable ( new ColorDrawable ( android.graphics.Color.TRANSPARENT ) );
                                dialog.getWindow ( ).setGravity ( Gravity.CENTER | Gravity.CENTER_VERTICAL );
                                dialog.setContentView ( R.layout.custom );
                                TextView text = dialog.findViewById ( R.id.text );
                                text.setText ( "*Remark is mandatory" );
                                Button dialogButton = dialog.findViewById ( R.id.dialogButtonOK );
                                // if button is clicked, close the custom dialog
                                dialogButton.setOnClickListener ( new View.OnClickListener ( ) {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss ( );

                                    }
                                } );
                                dialog.show ( );

                            } else {
                                str_remark = ed_remark.getText ( ).toString ( ).trim ( );
                                str_place = ed_place.getText ( ).toString ( ).trim ( );
                                setLatLonToHolder ( );

                                DataHolder_SiteInspection.getInstance ( ).setStr_place ( str_place );
                                DataHolder_SiteInspection.getInstance ( ).setStr_remark ( ed_remark.getText ( ).toString ( ).trim ( ) );
/*
                                DataHolder_SiteInspection.getInstance ( ).setCamera_lat ( String.valueOf ( mLocation.getLatitude ( ) ) );
                                DataHolder_SiteInspection.getInstance ( ).setCamera_long ( String.valueOf ( mLocation.getLongitude ( ) ) );
*/
                                DataHolder_SiteInspection.getInstance ( ).setCamera_time ( str_timestamp );
                         //       DataHolder_SiteInspection.getInstance ( ).setLoc_distance ( String.valueOf ( meterInDec_od ) );

                                // Toast.makeText(getApplicationContext(), String.valueOf(str_place), Toast.LENGTH_LONG).show();

                                Intent i = new Intent ( Attendance_Activity.this, Signature_ODActivity.class );
                                startActivity ( i );
                                //startService();
                                overridePendingTransition ( R.anim.right_in, R.anim.left_out );

                            }

                        }
                    } );

                } else {
                    showAlertMock ( );
                }
            }
        } );


        //------------ For Punch In Button--------------------------//
        btn_punchIn = findViewById ( R.id.punch_in );

        btn_punchIn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                isPounchIn = true;
                isPounchOut = false;

                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_EXPANDED );


                //  requestLocationUpdates ( );


                //   startGeoFenceForPunchInOut ( );


            }
        } );


        //-------For logout Button--------------------------//

        btn_punchOut = findViewById ( R.id.punch_out );


        btn_punchOut.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {


                // mLocation = fusedLocationService.getLocation ( );
                if (isMockSettingsON ( Attendance_Activity.this, mLocation ) == false) {

                    Intent intent1 = new Intent ( Attendance_Activity.this, Signature_OUTActivity.class );
                    startActivity ( intent1 );

                    setLatLonToHolder ( );


                } else {

                    showAlertMock ( );
                }


            }
        } );

/*
            isPounchOut = true;

                isPounchIn = false;

                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_EXPANDED );
*/


        // requestLocationUpdates ( );


        // initGMaps ( );

        //    startGeoFenceForPunchInOut ( );


        //New Location Client for fetching location


        bottomSheetBehavior.setBottomSheetCallback ( new BottomSheetBehavior.BottomSheetCallback ( ) {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

                switch (i) {

                    case BottomSheetBehavior.STATE_EXPANDED: {


                        System.out.println ( "Inside expanded" );
                        requestLocationUpdates ( );

                        break;
                    }
                    case BottomSheetBehavior.STATE_COLLAPSED: {


                        System.out.println ( "Inside collapsed" );
                        if (mFusedLocationClient != null) {

                            if (locationCallback != null) {
                                mFusedLocationClient.removeLocationUpdates ( locationCallback );

                            }


                        }


                        break;
                    }

                }


            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        } );


        setUpFusedLocationClient ( this );
        checkForLocationRequestSetting ( Attendance_Activity.this );


    }

    public void requestLocationUpdates() {


        // createLocationRequest ( );


        //    setUpFusedLocationClient ( Attendance_Activity.this );

        //   checkForLocationRequestSetting ( Attendance_Activity.this );

        initGMaps ( );

        countDownTimer ( );

    }


    public void countDownTimer() {


        timer = new CountDownTimer ( 120000, 1000 ) {


            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText ( "" + (millisUntilFinished / 1000) );
            }

            @Override
            public void onFinish() {

                bottomSheetBehavior.setHideable ( true );
                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_COLLAPSED );


                showNoLocationDialog ( );

            }
        }.start ( );
    }

    public void showNoLocationDialog() {

        new AlertDialog.Builder ( this )
                .setTitle ( "No Location?" )
                .setMessage ( "Couldn't able to fetch location please try again." )
                .setPositiveButton ( "Ok", new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish ( );
                    }
                } )

                .show ( );

    }

    public void setLatLonToHolder() {


        System.out.println ( "This is lat and lon " + lat + " " + lon );

        str_timestamp = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.getDefault ( ) ).format ( new Date ( ) );

        DataHolder_SiteInspection.getInstance ( ).setCamera_lat ( lat );
        DataHolder_SiteInspection.getInstance ( ).setCamera_long ( lon );
        DataHolder_SiteInspection.getInstance ( ).setCamera_time ( str_timestamp );


    }


    @Override
    protected void onDestroy() {
        super.onDestroy ( );


        if (mFusedLocationClient != null) {

            if (locationCallback != null) {
                mFusedLocationClient.removeLocationUpdates ( locationCallback );

            }


        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
        finish ( );
        this.overridePendingTransition ( R.anim.right_in, R.anim.left_out );
    }


    public static boolean isMockSettingsON(Context context, Location location) {
        // returns true if mock location enabled, false if not enabled.
        boolean isMock = false;
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                isMock = location.isFromMockProvider ( );
            } catch (Exception ex) {
                // Toast.makeText(context,ex.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        } else {
            isMock = Settings.Secure.getString ( context.getContentResolver ( ), Settings.Secure.ALLOW_MOCK_LOCATION ).equals ( "0" );
        }

        /*if (Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ALLOW_MOCK_LOCATION).equals("0")) {
            return false;
        }
        else
            return true;*/
        return isMock;

    }


    public void showAlertMock() {


        AlertDialog.Builder builder = new AlertDialog.Builder ( Attendance_Activity.this );
        builder.setMessage ( "Please OFF Mock Location from setting" );
        //builder.setCancelable(true);


        builder
                //  .setMessage("Click yes to exit!")
                .setCancelable ( true )
                .setPositiveButton ( "Yes", new DialogInterface.OnClickListener ( ) {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        startActivity ( new Intent ( Attendance_Activity.this, Home_Activity.class ) );
                        Attendance_Activity.this.finish ( );
                    }
                } );

        AlertDialog alert = builder.create ( );
        alert.show ( );

    }


    //---------- for sending punch in data to the server------------------//


    //--------------------- Alert for Logout---------------------//


    @SuppressLint("MissingPermission")
    protected void createLocationRequest(LocationRequest mLocationRequest) {
        //  mLocationRequest = new LocationRequest ( );
        mLocationRequest.setInterval ( 300 );
        mLocationRequest.setFastestInterval ( 300 );
        mLocationRequest.setPriority ( LocationRequest.PRIORITY_HIGH_ACCURACY );


        locationCallback = new LocationCallback ( ) {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                } else {


                    lastLocation = locationResult.getLastLocation ( );

                    markerLocation ( new LatLng ( lastLocation.getLatitude ( ), lastLocation.getLongitude ( ) ) );


                    System.out.println ( "This is lat " + lastLocation.getLatitude ( ) );
                    System.out.println ( "This is lng" + lastLocation.getLongitude ( ) );


                    lat = String.valueOf ( lastLocation.getLatitude ( ) );
                    lon = String.valueOf ( lastLocation.getLongitude ( ) );

                }
            }

        };
        mFusedLocationClient.requestLocationUpdates ( mLocationRequest,
                locationCallback,
                null /* Looper */ );


    }


    private void initGMaps() {
        mapFragment = (MapFragment) getFragmentManager ( ).findFragmentById ( R.id.map );
        mapFragment.getMapAsync ( this );
    }


    @SuppressLint("MissingPermission")
    public void setUpFusedLocationClient(Context context) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient ( context );


    }

    public void checkForLocationRequestSetting(final Context context) {

        final LocationRequest mLocationRequest = new LocationRequest ( );

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder ( )
                .addLocationRequest ( mLocationRequest );

        SettingsClient client = LocationServices.getSettingsClient ( context );
        Task <LocationSettingsResponse> task = client.checkLocationSettings ( builder.build ( ) );

        task.addOnSuccessListener ( new OnSuccessListener <LocationSettingsResponse> ( ) {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {


                createLocationRequest ( mLocationRequest );

                // startLocationUpdates ();
                //System.out.println ("This is update "+isUpdateRequired );
                Toast.makeText ( context, "Location service Enabled", Toast.LENGTH_SHORT ).show ( );

            }
        } );


        task.addOnFailureListener ( (Activity) context, new OnFailureListener ( ) {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println ( "inside location setting fail 1" );

                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().

                        System.out.println ( "inside location setting fail" );

                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult ( Attendance_Activity.this,
                                300 );
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        } );


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == 300) {

                Toast.makeText ( this, "Location service activated", Toast.LENGTH_SHORT ).show ( );
                checkForLocationRequestSetting ( Attendance_Activity.this );

            }

        } else if (resultCode == RESULT_CANCELED) {

            checkForLocationRequestSetting ( Attendance_Activity.this );


        }


    }


    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        System.out.println ( "This is marker pos " + marker.getPosition ( ) );
        return false;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        googleMap.getUiSettings ( ).setIndoorLevelPickerEnabled ( true );
        googleMap.getUiSettings ( ).setZoomControlsEnabled ( true );
        googleMap.getUiSettings ( ).setMapToolbarEnabled ( true );
        googleMap.getUiSettings ( ).setZoomGesturesEnabled ( true );
        googleMap.getUiSettings ( ).setScrollGesturesEnabled ( true );
        googleMap.getUiSettings ( ).setTiltGesturesEnabled ( true );
        googleMap.getUiSettings ( ).setRotateGesturesEnabled ( true );

        googleMap.setMapType ( GoogleMap.MAP_TYPE_SATELLITE );

        googleMap.setMyLocationEnabled ( true );
        googleMap.getUiSettings ( ).setMyLocationButtonEnabled ( true );
        googleMap.getUiSettings ( ).setCompassEnabled ( true );
        map.setOnMapClickListener ( this );
        map.setOnMarkerClickListener ( this );


        double lat2 = Double.valueOf ( sessionManager.GET_PHONE ( ) ).doubleValue ( );
        double lon2 = Double.valueOf ( sessionManager.GET_CURRENT_URL ( ) ).doubleValue ( );


        markerForGeofence ( lat2, lon2 );

        startGeofence ( );

    }


    // Create a Location Marker
    private void markerLocation(LatLng latLng) {
        String title = latLng.latitude + ", " + latLng.longitude;
        MarkerOptions markerOptions = new MarkerOptions ( )
                .position ( latLng )
                .title ( title );
        if (map != null) {
            // Remove the anterior marker
            if (locationMarker != null)
                locationMarker.remove ( );
            locationMarker = map.addMarker ( markerOptions );
            float zoom = 15f;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom ( latLng, zoom );
            map.animateCamera ( cameraUpdate );
        }
    }

    // Create a marker for the geofence creation
    private void markerForGeofence(double lat, double lon) {
        //  String title = latLng.latitude + ", " + latLng.longitude;
        // Define marker options
        MarkerOptions markerOptions = new MarkerOptions ( )
                .position ( new LatLng ( lat, lon ) )
                .icon ( BitmapDescriptorFactory.defaultMarker ( BitmapDescriptorFactory.HUE_ORANGE ) )
                .title ( "Geofence title" );
        if (map != null) {
            // Remove last geoFenceMarker
            if (geoFenceMarker != null)
                geoFenceMarker.remove ( );

            geoFenceMarker = map.addMarker ( markerOptions );
        }
    }

    // Create a Geofence
    private Geofence createGeofence(LatLng latLng, float radius) {
        return new Geofence.Builder ( )
                .setRequestId ( GEOFENCE_REQ_ID )
                .setCircularRegion ( latLng.latitude, latLng.longitude, radius )
                .setExpirationDuration ( GEO_DURATION )
                .setTransitionTypes ( Geofence.GEOFENCE_TRANSITION_ENTER
                        | Geofence.GEOFENCE_TRANSITION_EXIT )
                .build ( );
    }

    // Create a Geofence Request
    private GeofencingRequest createGeofenceRequest(Geofence geofence) {
        return new GeofencingRequest.Builder ( )
                .setInitialTrigger ( GeofencingRequest.INITIAL_TRIGGER_ENTER )
                .addGeofence ( geofence )
                .build ( );
    }

    private PendingIntent createGeofencePendingIntent() {
        if (geoFencePendingIntent != null)
            return geoFencePendingIntent;

        Intent intent = new Intent ( this, GeoFenceTransitionService.class );

        return PendingIntent.getService (
                this, GEOFENCE_REQ_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT );


    }

    // Add the created GeofenceRequest to the device's monitoring list
    @SuppressLint("MissingPermission")
    private void addGeofence(GeofencingRequest request) {

        GeofencingClient geofencingClient = LocationServices.getGeofencingClient ( this );


        geofencingClient.addGeofences ( request, createGeofencePendingIntent ( ) ).addOnSuccessListener ( new OnSuccessListener <Void> ( ) {
            @Override
            public void onSuccess(Void aVoid) {

                drawGeofence ( );

            }
        } );


    }

    // Draw Geofence circle on GoogleMap
    private Circle geoFenceLimits;

    private void drawGeofence() {

        if (geoFenceLimits != null)
            geoFenceLimits.remove ( );

        CircleOptions circleOptions = new CircleOptions ( )
                .center ( geoFenceMarker.getPosition ( ) )

                .strokeColor ( Color.parseColor ( "#CCFF90" ) )
                .fillColor ( Color.parseColor ( "#33691E" ) )
                /*.strokeColor ( Color.argb ( 50, 70, 70, 70 ) )
                .fillColor ( Color.argb ( 100, 150, 150, 150 ) )
                */
                .radius ( GEOFENCE_RADIUS );


        geoFenceLimits = map.addCircle ( circleOptions );


        ValueAnimator valueAnimator = new ValueAnimator ( );
        valueAnimator.setRepeatCount ( ValueAnimator.INFINITE );
        valueAnimator.setRepeatMode ( ValueAnimator.RESTART );
        valueAnimator.setIntValues ( 0, 100 );
        valueAnimator.setDuration ( 3000 );
        valueAnimator.setEvaluator ( new IntEvaluator ( ) );
        valueAnimator.setInterpolator ( new AccelerateDecelerateInterpolator ( ) );
        valueAnimator.addUpdateListener ( new ValueAnimator.AnimatorUpdateListener ( ) {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction ( );


                // System.out.println ("Animated fractions "+animatedFraction );
                geoFenceLimits.setRadius ( animatedFraction * 100 );

                //  System.out.println ("Animated fractions "+animatedFraction*1000 );

            }
        } );

        valueAnimator.start ( );
    }

    // Start Geofence creation process
    private void startGeofence() {
        System.out.println ( "Start Geo Fence" );
        if (geoFenceMarker != null) {
            Geofence geofence = createGeofence ( geoFenceMarker.getPosition ( ), GEOFENCE_RADIUS );
            GeofencingRequest geofenceRequest = createGeofenceRequest ( geofence );
            addGeofence ( geofenceRequest );
        } else {

            System.out.println ( "Geo Fence marker is null" );
        }
    }


    public class GeoFenceResultReceiver extends ResultReceiver {


        public GeoFenceResultReceiver(Handler handler) {
            super ( handler );
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            switch (resultCode) {

                case GeoFenceTransitionService.GEOFENCE_SUCCESS: {

                    System.out.println ( "Inside success" );

                    timer.cancel ( );


                    bottomSheetBehavior.setHideable ( true );
                    bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_COLLAPSED );

                    //        timer.onFinish ( );

                    Intent intent1 = new Intent ( Attendance_Activity.this, SignatureActivity.class );
                    startActivity ( intent1 );


                 /*   if (isPounchIn) {
                        Intent intent1 = new Intent ( Attendance_Activity.this, SignatureActivity.class );
                        startActivity ( intent1 );

                    } else {
                        Intent intent1 = new Intent ( Attendance_Activity.this, Signature_OUTActivity.class );
                        startActivity ( intent1 );

                    }
*/
                    setLatLonToHolder ( );

                    break;

                }

                case GeoFenceTransitionService.GEOFENCE_ERROR: {

                    System.out.println ( "Inside Error" );
                    break;

                }


            }

        }
    }
}

