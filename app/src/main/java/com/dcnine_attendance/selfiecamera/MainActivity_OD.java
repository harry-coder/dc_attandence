package com.dcnine_attendance.selfiecamera;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcnine_attendance.Home_Activity;
import com.dcnine_attendance.R;
import com.dcnine_attendance.authentication.ConnectionDetector;
import com.dcnine_attendance.authentication.HTTPURLConnection;
import com.dcnine_attendance.authentication.SessionManager;
import com.dcnine_attendance.data_holder.DataHolder_SiteInspection;
import com.dcnine_attendance.location.FusedLocationService;
import com.dcnine_attendance.service.CaptureService;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity_OD extends Activity {
	ImageView iv;
	//to call our own custom cam
	private final static int CAMERA_PIC_REQUEST1 = 0;
	Context con;
	int count = 0;
	SessionManager sessionManager;
	//String permit_id;
	Button use_picture;

	ConnectionDetector connectionDetector;
	String internet_interrupt = null;

	Location mLocation = null;
	FusedLocationService fusedLocationService;
	String sending_latt, sending_longg;
	String permit_id, str_timestamp;
	double latt, longg;
	String response;
	ProgressDialog pd;

	private ProgressDialog pDialog;
	private JSONObject json;
	private int success=0;
	private HTTPURLConnection service;
	private String strname ="", strMobile ="",strAddress="";
	//Initialize webservice URL
	private String path = "http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/insert_punch_od1";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.imageView1);
		con=this;

		service=new HTTPURLConnection();

        sessionManager = new SessionManager(MainActivity_OD.this);
		permit_id= sessionManager.GET_EMP_ID();
		fusedLocationService = new FusedLocationService(this);
		mLocation = fusedLocationService.getLocation();
		connectionDetector = new ConnectionDetector(MainActivity_OD.this);
		//sessionManager = new SessionManager(Attendance_Activity.this);
		//permit_id = sessionManager.GET_EMP_ID();

        use_picture = (Button) findViewById(R.id.use_picture_button);
		use_picture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//onBackPressed();
				if(TextUtils.isEmpty(DataHolder_SiteInspection.getInstance().getImageNmae6()))
				{
					final Dialog dialog = new Dialog(MainActivity_OD.this);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					dialog.getWindow().setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
					dialog.setContentView(R.layout.custom);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					text.setText("*Take Picture is mandatory");
					Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();

						}
					});
					dialog.show();

				}

				else {
					//new PostDataTOServer().execute();
					new SendToServer().execute();
					startService();
				}

			}
		});


	}

	public void onClick(View view) {
		if (getFrontCameraId() == -1) {
			Toast.makeText(getApplicationContext(),
					"Front Camera Not Detected", Toast.LENGTH_SHORT).show();
		} else {
			Intent cameraIntent = new Intent();
			cameraIntent.setClass(this, CameraActivity.class);
			startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST1);

			// startActivity(new
			// Intent(MainActivity.this,CameraActivity.class));
		}
	}

	int getFrontCameraId() {
		CameraInfo ci = new CameraInfo();
		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
			Camera.getCameraInfo(i, ci);
			if (ci.facing == CameraInfo.CAMERA_FACING_FRONT)
				return i;
		}
		return -1; // No front-facing camera found
	}

	Bitmap bitmapFrontCam;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST1) {
			if (resultCode == RESULT_OK) {
				try {

					bitmapFrontCam = (Bitmap) data.getParcelableExtra("BitmapImage");

					/*final Bitmap bitmapunpro = BitmapFactory.decodeFile(fileUri.getPath(),
							options);

					final Bitmap bitmap = getResizedBitmap(BitmapFactory.decodeFile(fileUri.getPath(),
							options), 80, 110);*/

					String lrgbitmapstring;
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					bitmapFrontCam.compress(Bitmap.CompressFormat.JPEG, 50, bytes);


					byte[] byteArray = bytes.toByteArray();
					lrgbitmapstring = Base64.encodeToString(byteArray, Base64.DEFAULT);
					if (DataHolder_SiteInspection.getInstance().getlrgImg1() == null) {

						DataHolder_SiteInspection.getInstance().setlrgImg6(lrgbitmapstring);
					} else {
						Toast.makeText(getApplicationContext(), "Maximum 1 Images", Toast.LENGTH_LONG).show();
					}
                 } catch (Exception e) {
				}

				iv.setImageBitmap(bitmapFrontCam);
				previewCapturedImage(count, bitmapFrontCam);
			}

		} else if (resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT)
					.show();
		}
	}


	/*
    * Display image from a path to ImageView
   */
	private void previewCapturedImage(int count, Bitmap data) {
		String bitmapstring;
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");data
		Bitmap bm = data.copy(Bitmap.Config.RGB_565, true);
//        bitmapstring=bm.toString();
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		data.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
		byte[] byteArray = bytes.toByteArray();
		bitmapstring = Base64.encodeToString(byteArray, Base64.DEFAULT);
		System.out.println("hyyyy" + count);


		System.out.println("AMIN " + DataHolder_SiteInspection.getInstance().getImg6());

		String str_timestamp1 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());


		if (DataHolder_SiteInspection.getInstance().getImg6() == null) {
			/*File destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Attendance/",
					permit_id+"_"+str_timestamp1+"."+"jpg");*/
			DataHolder_SiteInspection.getInstance().setImageNmae6(permit_id+"_"+str_timestamp1+"."+"jpg");
			//System.out.println("IMAGE NAME 1 " + permit_id+"_"+str_timestamp1+"."+"jpg");
			/*FileOutputStream fo;
			try {
				destination.createNewFile();
				fo = new FileOutputStream(destination);
				fo.write(bytes.toByteArray());
				fo.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			DataHolder_SiteInspection.getInstance().setImg6(bitmapstring);
			//System.out.println("AMIN1");

		}  else {
			Toast.makeText(getApplicationContext(), "Maximum 1 Images", Toast.LENGTH_LONG).show();
		}


	}


	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	//---------- for sending punch in data to the server------------------//

	public class SendToServer extends AsyncTask<String, String, String> {

		ProgressDialog pd;

		// public SendToServer() {
		public SendToServer() {
			// TODO Auto-generated constructor stub
			/*sending_latt = String.valueOf(latitude);
			sending_longg = String.valueOf(longitude);*/
//            Log.e("from", "sendtoserver");
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(MainActivity_OD.this);
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
					HttpPost httppost = new HttpPost("http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/insert_punch_od1");
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					// httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					nameValuePairs.add(new BasicNameValuePair("permit_id", sessionManager.GET_EMP_ID()));
					nameValuePairs.add(new BasicNameValuePair("project_id", sessionManager.GET_PROJECT()));
					nameValuePairs.add(new BasicNameValuePair("mobile_date", DataHolder_SiteInspection.getInstance().getCamera_time()));
					nameValuePairs.add(new BasicNameValuePair("latt", DataHolder_SiteInspection.getInstance().getCamera_lat()));
					nameValuePairs.add(new BasicNameValuePair("longg", DataHolder_SiteInspection.getInstance().getCamera_long()));
					nameValuePairs.add(new BasicNameValuePair("distance_od", DataHolder_SiteInspection.getInstance().getLoc_distance()));
					nameValuePairs.add(new BasicNameValuePair("od_remark", DataHolder_SiteInspection.getInstance().getStr_remark()));
					nameValuePairs.add(new BasicNameValuePair("od_place", DataHolder_SiteInspection.getInstance().getStr_place()));

					nameValuePairs.add(new BasicNameValuePair("selfie_name", DataHolder_SiteInspection.getInstance().getImageNmae6()));
					nameValuePairs.add(new BasicNameValuePair("selfie", DataHolder_SiteInspection.getInstance().getImg6()));
					nameValuePairs.add(new BasicNameValuePair("signature", DataHolder_SiteInspection.getInstance().getImg1()));



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
					final Dialog dialog = new Dialog(MainActivity_OD.this);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
					dialog.setContentView(R.layout.custom);
//                              dialog.setTitle("Title...");

					// set the custom dialog components - text, image and button
					TextView text = (TextView) dialog.findViewById(R.id.text);
					text.setText(" Punch OD Marked ");
					Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							pd.hide();
							pd.dismiss();
							dialog.dismiss();
                            DataHolder_SiteInspection.getInstance().nullify_DataHolder_SiteInspection();
							startActivity(new Intent(MainActivity_OD.this, Home_Activity.class));
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


	/*private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

		//String response = "";
		//Create hashmap Object to send parameters to web service
		HashMap<String, String> postDataParams;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(MainActivity_OD.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			postDataParams=new HashMap<String, String>();
			postDataParams.put("permit_id", sessionManager.GET_EMP_ID());
			postDataParams.put("project_id", sessionManager.GET_PROJECT());
			postDataParams.put("mobile_date", DataHolder_SiteInspection.getInstance().getCamera_time());
			postDataParams.put("latt", DataHolder_SiteInspection.getInstance().getCamera_lat());
			postDataParams.put("longg", DataHolder_SiteInspection.getInstance().getCamera_long());
			postDataParams.put("od_remark", DataHolder_SiteInspection.getInstance().getStr_remark());
            postDataParams.put("od_place", DataHolder_SiteInspection.getInstance().getStr_place());

			postDataParams.put("distance_od", DataHolder_SiteInspection.getInstance().getLoc_distance());
			postDataParams.put("selfie_name", DataHolder_SiteInspection.getInstance().getImageNmae6());
			postDataParams.put("selfie", DataHolder_SiteInspection.getInstance().getImg6());
			postDataParams.put("signature", DataHolder_SiteInspection.getInstance().getImg1());


			//Log.e("name value pair", "" + DataHolder_SiteInspection.getInstance().getStr_place());
			//Call ServerData() method to call webservice and store result in response
			response= service.ServerData(path,postDataParams);
			try {
				json = new JSONObject(response);
				//Get Values from JSONobject
				System.out.println("success=" + json.get("resp"));
				//success = json.getInt("response");

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			if (response != null && response.equals("1")) {
				//Toast.makeText(getApplicationContext(), "Employee Added successfully..!", Toast.LENGTH_LONG).show();
				final Dialog dialog = new Dialog(MainActivity_OD.this);
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
						pDialog.hide();
						pDialog.dismiss();
						dialog.dismiss();

						//Toast.makeText(getApplicationContext(), String.valueOf(DataHolder_SiteInspection.getInstance().getStr_place()), Toast.LENGTH_LONG).show();

						DataHolder_SiteInspection.getInstance().nullify_DataHolder_SiteInspection();
						DataHolder_SiteInspection.getInstance().setStr_punch_status("1");
						startActivity(new Intent(MainActivity_OD.this, Home_Activity.class));
						finish();

					}
				});

				dialog.show();
			}
		}
	}*/

	//----------------for background service----------------------------//
	public void startService() {
		startService(new Intent(getBaseContext(), CaptureService.class));
	}

	// Method to stop the service
	public void stopService() {
		stopService(new Intent(getBaseContext(), CaptureService.class));
	}
	
}
