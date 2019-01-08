package com.dcnine_attendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dcnine_attendance.attendance_module.Attendance_Activity;
import com.dcnine_attendance.authentication.ConnectionDetector;
import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.authentication.TelephonyInfo;
import com.dcnine_attendance.data_holder.DataHolderClass;
import com.dcnine_attendance.data_holder.DataHolder_SiteInspection;
import com.dcnine_attendance.login_module.Login_Activity;
import com.dcnine_attendance.sqlite_adapter.SQLiteAdapter1;
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


/**
 * Created by nitinb on 28-01-2016.
 */
public class Home_Activity extends Activity {
    ImageButton login_btn, attendance_btn;
    TextView tvView;
    DataHolderClass dataHolderClass;
    String permit_id;
    public static String imeiSIM1 ;
    public static String imeiSIM2 ;
    String version;
    PackageInfo pInfo;

    SessionManager sessionManager;
    String district_id;
    ConnectionDetector detector;


    String response = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = new SessionManager(Home_Activity.this);
        this.district_id = sessionManager.GET_CURRENT_URL();

        isDualSimOrNot();
        try {
            //	cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        }  catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        version=pInfo.versionName;
        attendance_btn = (ImageButton) findViewById(R.id.attendanceid1);
        //issue_btn = (ImageButton) findViewById(R.id.issueid);

        detector = new ConnectionDetector(Home_Activity.this);

        attendance_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                dataHolderClass.getInstance().getStr_user_code();
                dataHolderClass.getInstance().getStr_pin_code();


                Intent i = new Intent(Home_Activity.this, Attendance_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

           }
        });

    }



    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        // finish();
    }

    private void isDualSimOrNot(){
        TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);
        imeiSIM1 = telephonyInfo.getImeiSIM1();
        imeiSIM2 = telephonyInfo.getImeiSIM2();

    }



}