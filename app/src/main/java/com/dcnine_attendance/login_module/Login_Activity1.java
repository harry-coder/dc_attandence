package com.dcnine_attendance.login_module;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dcnine_attendance.Home_Activity;
import com.dcnine_attendance.R;
import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.data_holder.DataHolderClass;
import com.dcnine_attendance.sqlite_adapter.SQLiteAdapter1;

/**
 * Created by nitinb on 03-02-2016.
 */
public class Login_Activity1 extends Activity {

    Button imageButton1;
    ImageButton submit_btn;
    EditText userID,passID;

    Context context;

    String str_userID,str_passID;

    SessionManager sessionManager;
    DataHolderClass dataHolderClass;

    Cursor curSearchdata;
    SQLiteAdapter1 sqLiteAdapter;
    String georaphiclevel, level1, level2, level3, level4;

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

        submit_btn = (ImageButton) findViewById(R.id.button_id);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if (passID.getText().toString().equalsIgnoreCase(sessionManager.GET_EMP_ID()) && userID.getText().toString().equalsIgnoreCase(sessionManager.GET_PHONE()))
                if (passID.getText().toString().equalsIgnoreCase(sessionManager.GET_EMP_ID()))

                {

                    str_userID =    sessionManager.GET_EMP_ID();
                    str_passID = sessionManager.GET_PHONE();
                    dataHolderClass.getInstance().setStr_user_code(str_userID);
                    dataHolderClass.getInstance().setStr_pin_code(str_passID);
                    Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                    //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                  }else{
                    // custom dialog
                    final Dialog dialog = new Dialog(Login_Activity1.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    dialog.setContentView(R.layout.custom);
//                              dialog.setTitle("Title...");

                    // set the custom dialog components - text, image and button
                    TextView text = (TextView) dialog.findViewById(R.id.text);
                    text.setText("Kindly check Pin Number");
//                              ImageView image = (ImageView) dialog.findViewById(R.id.image);
//                              image.setImageResource(R.drawable.ic_launcher);

                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                      /*  DataHolder_SiteInspection.getInstance().nullify_DataHolder_SiteInspection();
                        startActivity(new Intent(OpenPurchaseInspection_Item_Activity.this, Home_Activity.class));
                        finish();*/

                        }
                    });

                    dialog.show();

                }
            }
        });


       /* passID.addTextChangedListener(passwordWatcher);

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

                    if (passID.getText().toString().equalsIgnoreCase(sessionManager.GET_EMP_ID()) && userID.getText().toString().equalsIgnoreCase(sessionManager.GET_PHONE()))

                 {

                        str_userID =    sessionManager.GET_EMP_ID();
                        str_passID = sessionManager.GET_PHONE();
                       dataHolderClass.getInstance().setStr_user_code(str_userID);
                       dataHolderClass.getInstance().setStr_pin_code(str_passID);
                      Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                     //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                     startActivity(intent);
                     overridePendingTransition(R.anim.right_in, R.anim.left_out);
                  }

            }


        }*/
    };

}
