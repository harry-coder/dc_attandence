package com.dcnine_attendance.login_module;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dcnine_attendance.R;
import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.data_holder.DataHolderClass;

/**
 * Created by nitinb on 03-02-2016.
 */
public class LogOut_Activity extends Activity {

    Button logoutbtn;


    SessionManager sessionManager;
    DataHolderClass dataHolderClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.logout);
        sessionManager=new SessionManager(this);
        logoutbtn = (Button) findViewById(R.id.logout_id);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   Intent i = new Intent(LogOut_Activity.this, Login_Activity.class);
                startActivity(i);
                //finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                //onBackPressed();*/
                SharedPreferences preferences =getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });


    };



}
