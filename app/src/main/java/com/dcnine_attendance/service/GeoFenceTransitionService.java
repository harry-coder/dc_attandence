package com.dcnine_attendance.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.dcnine_attendance.CustomInterface.OnGeoFenceEnterListener;
import com.dcnine_attendance.MainActivity;
import com.dcnine_attendance.attendance_module.Attendance_Activity;
import com.dcnine_attendance.attendance_module.SignatureActivity;
import com.dcnine_attendance.attendance_module.Signature_OUTActivity;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.dcnine_attendance.attendance_module.Attendance_Activity.isPounchIn;
import static com.dcnine_attendance.attendance_module.Attendance_Activity.isPounchOut;

public class GeoFenceTransitionService extends IntentService {

    public static final int GEOFENCE_SUCCESS = 1;
    public static final int GEOFENCE_ERROR = 2;


    OnGeoFenceEnterListener onGeoFenceEnterListener;
    private static final String TAG = GeoFenceTransitionService.class.getSimpleName ( );
    public static final int GEOFENCE_NOTIFICATION_ID = 0;

    Context context;
    int value;

    public GeoFenceTransitionService() {
        super ( "name" );


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Retrieve the Geofencing intent
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent ( intent );


        Bundle bundle = new Bundle ( );

        // Handling errors
        if (geofencingEvent.hasError ( )) {
            String errorMsg = getErrorString ( geofencingEvent.getErrorCode ( ) );

            bundle.putString ( "error", errorMsg );
            Attendance_Activity.geoFenceResultReceiver.send ( GEOFENCE_ERROR, bundle );


            System.out.println ( "Errors in geo fence service " + errorMsg );
            return;
        }

        // Retrieve GeofenceTrasition
        int geoFenceTransition = geofencingEvent.getGeofenceTransition ( );


        System.out.println ( "Geofence transition value " + geoFenceTransition );
        // Check if the transition type
        if (geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            // Get the geofence that were triggered

            System.out.println ( "Entered Geo Fence" );

            bundle.putBoolean ( "success", true );

            if(Attendance_Activity.geoFenceResultReceiver!=null){
                Attendance_Activity.geoFenceResultReceiver.send ( GEOFENCE_SUCCESS, bundle );

            }







            stopSelf ( );
           /* List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            // Create a detail message with Geofences received
            String geofenceTransitionDetails = getGeofenceTrasitionDetails(geoFenceTransition, triggeringGeofences );
            // Send notification details as a String
            sendNotification( geofenceTransitionDetails );*/
        } else if (geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            System.out.println ( "Exit Geo Fence" );

        }
    }

    /* // Create a detail message with Geofences received
     private String getGeofenceTrasitionDetails(int geoFenceTransition, List<Geofence> triggeringGeofences) {
         // get the ID of each geofence triggered
         ArrayList<String> triggeringGeofencesList = new ArrayList<>();
         for ( Geofence geofence : triggeringGeofences ) {
             triggeringGeofencesList.add( geofence.getRequestId() );
         }

         String status = null;
         if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER )
             status = "Entering ";
         else if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT )
             status = "Exiting ";
         return status + TextUtils.join( ", ", triggeringGeofencesList);
     }

     // Send a notification
     private void sendNotification( String msg ) {
         Log.i(TAG, "sendNotification: " + msg );

         // Intent to start the main Activity
         Intent notificationIntent = MainActivity.makeNotificationIntent(
                 getApplicationContext(), msg
         );

         TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
         stackBuilder.addParentStack(MainActivity.class);
         stackBuilder.addNextIntent(notificationIntent);
         PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

         // Creating and sending Notification
         NotificationManager notificatioMng =
                 (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
         notificatioMng.notify(
                 GEOFENCE_NOTIFICATION_ID,
                 createNotification(msg, notificationPendingIntent));
     }

     // Create a notification
     private Notification createNotification(String msg, PendingIntent notificationPendingIntent) {
         NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
         notificationBuilder
                 .setSmallIcon(R.drawable.ic_action_location)
                 .setColor(Color.RED)
                 .setContentTitle(msg)
                 .setContentText("Geofence Notification!")
                 .setContentIntent(notificationPendingIntent)
                 .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                 .setAutoCancel(true);
         return notificationBuilder.build();
     }
 */
    // Handle errors
    private static String getErrorString(int errorCode) {
        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "GeoFence not available";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many GeoFences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Too many pending intents";
            default:
                return "Unknown error.";
        }
    }


}
