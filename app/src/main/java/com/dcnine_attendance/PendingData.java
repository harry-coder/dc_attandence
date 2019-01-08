package com.dcnine_attendance;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.sqlite_adapter.SQLiteAdapter1;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class PendingData extends AsyncTask<String, String,String> {
     String response;
	SQLiteAdapter1 sqLiteAdapter;
	Context context;
    SessionManager sessionManager;
	int id;
	String strpermit_id,strproject,strgeographic, strsubarea_one,strsubarea_two,strmain_item,strsubitem_code,struom_no,strpole_item_yes,strpole_item_cancel,strcheck_input,strcheck_na,
			strlatt,strlongg,strmobile_time,strremark,strpass_rework,strimage1_name,strimage2_name,strimage3_name,strimage4_name,strimage5_name,strimage1,strimage2,strimage3,strimage4,strimage5;
	
	public PendingData(Context c, int activity_id,String permit_id,String project,String geographic, String subarea_one,String subarea_two,String main_item,String subitem_code,String uom_no,String pole_item_yes,String pole_item_cancel,String check_input,String check_na,
					   String latt,String longg,String mobile_time,String remark,String pass_rework,String image1_name,String image2_name,String image3_name,String image4_name,String image5_name,String image1,String image2,String image3,String image4,String image5) {
		
	// TODO Auto-generated constructor stub
		try{
		context=c;
		sqLiteAdapter=new SQLiteAdapter1(context);
 //		adapter.openToRead();
		    sqLiteAdapter.openToWrite();
			id=activity_id;
			strpermit_id=  permit_id;
			strproject=    project;
			strgeographic= geographic;
			strsubarea_one=subarea_one;
			strsubarea_two=subarea_two;
			strmain_item=main_item;
			strsubitem_code=  subitem_code;
			struom_no=  uom_no;
			strpole_item_yes=  pole_item_yes;
			strpole_item_cancel=pole_item_cancel;
			strcheck_input=    check_input;
			strcheck_na=     check_na;
			strlatt=       latt;
			strlongg=      longg;
			strmobile_time=mobile_time;
			strremark=     remark;
			strpass_rework=     pass_rework;
			strimage1_name=      image1_name;
			strimage2_name=      image2_name;
			strimage3_name=      image3_name;
			strimage4_name=      image4_name;
			strimage5_name=      image5_name;
			strimage1=     image1;
			strimage2=     image2;
			strimage3=     image3;
			strimage4=     image4;
			strimage5=     image5;



		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("permit_id", strpermit_id));
		nameValuePairs.add(new BasicNameValuePair("project", strproject));
		nameValuePairs.add(new BasicNameValuePair("geographic",strgeographic ));
		nameValuePairs.add(new BasicNameValuePair("subarea_one",strsubarea_one ));
		nameValuePairs.add(new BasicNameValuePair("subarea_two",strsubarea_two ));
		nameValuePairs.add(new BasicNameValuePair("main_item",strmain_item ));
		nameValuePairs.add(new BasicNameValuePair("sub_item", strsubitem_code));
		nameValuePairs.add(new BasicNameValuePair("uom_no", struom_no));
		nameValuePairs.add(new BasicNameValuePair("pole_item_yes", strpole_item_yes));
		nameValuePairs.add(new BasicNameValuePair("pole_item_cancel", strpole_item_cancel));
		nameValuePairs.add(new BasicNameValuePair("checkbox_input", strcheck_input));
		nameValuePairs.add(new BasicNameValuePair("checkbox_na", strcheck_na));
		nameValuePairs.add(new BasicNameValuePair("latt", strlatt));
		nameValuePairs.add(new BasicNameValuePair("longg", strlongg));
		nameValuePairs.add(new BasicNameValuePair("mobile_time", strmobile_time));
		nameValuePairs.add(new BasicNameValuePair("remark", strremark));
		nameValuePairs.add(new BasicNameValuePair("pass_rework", strpass_rework));
		nameValuePairs.add(new BasicNameValuePair("imagename1", strimage1_name));
		nameValuePairs.add(new BasicNameValuePair("imagename2", strimage2_name));
		nameValuePairs.add(new BasicNameValuePair("imagename3", strimage3_name));
		nameValuePairs.add(new BasicNameValuePair("imagename4", strimage4_name));
		nameValuePairs.add(new BasicNameValuePair("imagename5", strimage5_name));
		nameValuePairs.add(new BasicNameValuePair("image1", strimage1));
		nameValuePairs.add(new BasicNameValuePair("image2", strimage2));
		nameValuePairs.add(new BasicNameValuePair("image3", strimage3));
		nameValuePairs.add(new BasicNameValuePair("image4", strimage4));
		nameValuePairs.add(new BasicNameValuePair("image5", strimage5));


		Log.e("nameValuePairs", "" + nameValuePairs);
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://monitorpm.feedbackinfra.com/dcnine_honeywell/embc_app/insert_pmc_data");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = httpclient.execute(httppost, responseHandler);

        } catch (Exception e) {
		}
		return response;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		try{
			if(response.trim().equalsIgnoreCase("1")){

				sqLiteAdapter.openToWrite();
				sqLiteAdapter.delete_value_byID(id);

				response=null;
			}
			else {
				Toast.makeText(context,"server error", Toast.LENGTH_SHORT).show();

			}
		}catch(Exception e){}
		finally{
			
			sqLiteAdapter.close();
		}
		}
		
	

	
}
