package com.dcnine_attendance.login_module;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dcnine_attendance.Home_Activity;
import com.dcnine_attendance.R;
import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.data_holder.DataHolderClass;

/**
 * Created by nitinb on 03-02-2016.
 */
public class Login_Activity extends Activity {

    Button imageButton1;
    EditText userID,passID;
    LinearLayout sixbox;

    Context context;

    String str_userID,str_passID;

    SessionManager sessionManager;
    DataHolderClass dataHolderClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        sessionManager=new SessionManager(this);
        imageButton1 = (Button) findViewById(R.id.backpage_id);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userID = (EditText)findViewById(R.id.enter_code_id);
        passID = (EditText) findViewById(R.id.enter_pin_id);

        str_userID=userID.getText().toString().trim();
        str_passID=passID.getText().toString().trim();

/*       Code written by Goutam Singh  start {
        =============================*/
        sixbox=(LinearLayout)findViewById(R.id.sixbox);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // Do something for lollipop and above versions
            //Toast.makeText(Login_Activity.this, "lollipop and above versions ", Toast.LENGTH_SHORT).show();

        } else{
            // do something for phones running an SDK before lollipop
            //Toast.makeText(Login_Activity.this, "lollipop and before versions ", Toast.LENGTH_SHORT).show();
            sixbox.setVisibility(View.GONE);
            passID.setBackgroundResource(R.drawable.edittextstyle);
            passID.getLayoutParams().width=180;
            passID.setGravity(Gravity.CENTER);

        }
/*       }end
        =============================*/


        passID.addTextChangedListener(passwordWatcher);

        // Function to ON GPS Location before Login (If GPS Location is not ON)
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // Build the alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Location Services Not Active");
            builder.setMessage("Please enable GPS Location Services ");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Show location settings when the user acknowledges the alert dialog
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        public void afterTextChanged(Editable s) {

            if (passID.getText().toString().length()>0) {

                    if (passID.getText().toString().equalsIgnoreCase(sessionManager.GET_EMP_ID()) )

                 {

                        str_userID =    sessionManager.GET_EMP_ID();
                        str_passID = sessionManager.GET_PHONE();
                       dataHolderClass.getInstance().setStr_user_code(str_userID);
                       dataHolderClass.getInstance().setStr_pin_code(str_passID);
                      Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                     startActivity(intent);
                     overridePendingTransition(R.anim.right_in, R.anim.left_out);
                  }

            }


        }
    };

}
