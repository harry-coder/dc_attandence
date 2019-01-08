package com.dcnine_attendance.authentication;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dcnine_attendance.Home_Activity;
import com.dcnine_attendance.R;
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
import org.json.JSONObject;

import java.util.ArrayList;


public class Splash extends Activity {

    String response=null;
    SQLiteAdapter1 sqLiteAdapter;
    String network_interrupt=null;
    ConnectionDetector connectionDetector;
    SessionManager sessionManager;
    //UrlSessionManager urlsessionManager;
    String project_id;
    String district_id;

    String m_lat;
    String m_long;


    HttpClient httpclient;
    HttpPost httppost;

    boolean responseProject, responseGeographic, responseSubAreaOne,responseSubAreaTwo,
            responseSubAreaThree,responseInsp_ReInsp,responseTkc,response_Item,responseChoose_Item,responseSurvey,responseAsset,
            responseSurveySubstation,responseSurveyFeeder,responseSurveyDtrRating,responseSurveyDtrMouting,responseSurveySvoltage,
            responseSurveyinfeeder,responseSurveyoutfeeder,responseSurveypt,responseSurveyswitchgear,responseSurveylocation,
            responseSurveyLength,responseSurveyPhase,responseSurveyConsumerType,responseSurveyCategory,respPoleType,respPoleCondition,
            respPoleLocation,respInsulatorType,respInsulatorCondition,respStayType,respStayCondition,respGuardingType,respGuardingCondition,
            respEarthingType,respEarthingCondition,respCrossing,respsurveyscope,respConductorType,respConductorCondition,respMasterdropdown,respMatrialIssued;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sqLiteAdapter=new SQLiteAdapter1(Splash.this);
        connectionDetector=new ConnectionDetector(Splash.this);
        //urlsessionManager=new UrlSessionManager(Splash.this);

        sessionManager=new SessionManager(Splash.this);
        if(sessionManager.getdownload_completed()){
            startActivity( new Intent(Splash.this,Home_Activity.class));
            finish();
        }else {

            if (connectionDetector.isConnectingToInternet()) {

                Log.e("download", "" + sessionManager.getdownload_completed());
                // new Configuration().execute();

                new Configuration().execute();
                sessionManager.download_completed();

            } else {
                Toast.makeText(getApplicationContext(), "Internet not connected", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public  class Configuration extends AsyncTask<String, String,String>
    {

        @Override
        protected void onPreExecute()
        {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {
            // sqlAdapter=new SQLiteAdapter(Splash.this);
            try
            {

                m_lat=sessionManager.GET_PHONE();
                m_long=sessionManager.GET_CURRENT_URL();
                //  sqlAdapter.openToRead();
                sqLiteAdapter.openToRead();
                sqLiteAdapter.openToWrite();

                sqLiteAdapter.insert_internal_external(m_lat,m_long);
                //sqLiteAdapter.insert_internal_external("External","");

               /* sqLiteAdapter.insert_survey_substationvoltage("General","");
                sqLiteAdapter.insert_survey_substationvoltage("CCR","");
                sqLiteAdapter.insert_survey_substationvoltage("Junction","");*/

                httpclient = new DefaultHttpClient();
                // String jsonStr = sh.makeServiceCall(url + "/" + id, ServiceHandler.GET);
                httppost = new HttpPost("http://attendance.feedbackinfra.com/dcnine_attendance/embc_app/download");
                // httppost = new HttpPost(sessionManager.GET_CURRENT_URL()+"/DCNINE/embc_app/download");

                getMasterZone();
                getMasterCity();
                getGeographic();
                getSubAreaOne();
                getSubAreaTwo();
                getmaterial_Item();
               //getSubAreaThree();
                getInsp_ReInsp();
                getTkc();
                getType_work();
                getInspection_subitem();
                getmaterial_subitem();
                getChoose_Item();
                //getAsset();


                getWorkprogressScope();

                getMasterDropdown();
                getMaterialIssued();

            }
            catch(Exception e)
            {
                //  Log.e("log_tag", "Error in http connection " + e.toString());

            }
            return response;
        }
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);


            if(responseProject==true && responseGeographic== true && responseSubAreaOne==true && responseSubAreaTwo== true && response_Item==true && respMatrialIssued==true && responseTkc==true  ) {

                startActivity(new Intent(Splash.this, Home_Activity.class));
                finish();

            }
            else{
                Toast.makeText(getApplicationContext(), "Unable to start application", Toast.LENGTH_SHORT).show();
            }


        }
    }


    public void getMasterZone() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        project_id=sessionManager.GET_PROJECT();
        district_id=sessionManager.GET_CURRENT_URL();
        nameValuePairs.add(new BasicNameValuePair("tag", "m_zone"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        Log.e("NameValuepro", "" + nameValuePairs);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseProject=false;
            }
            else {

                //   Log.e("district", response);
                JSONObject json_project = new JSONObject(response);
                JSONArray array_project = json_project.getJSONArray("resp");
                Log.e("array master Zone", "" + array_project);

                for (int i = 0; i < array_project.length(); i++) {
                    JSONObject json_proj = array_project.getJSONObject(i);
                    String scheme_id = json_proj.getString("project_code");
                    String project_id = json_proj.getString("geographic_id");
                    String scheme_name = json_proj.getString("geographic_name");
                    sqLiteAdapter.insert_mgeographic(scheme_id, project_id, scheme_name);
                    // Log.e("dist id", dist_code);
                    responseProject = true;
                }
            }


        } catch (Exception e) {
            //   Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    public void getMasterCity() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "m_city"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSubAreaThree=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_sub_area_three = new JSONObject(response);
                JSONArray array_sub_area_three = json_sub_area_three.getJSONArray("resp");
                Log.e("array master_city", "" + array_sub_area_three);

                for (int i = 0; i < array_sub_area_three.length(); i++) {
                    JSONObject json_sub_area_three_obj = array_sub_area_three.getJSONObject(i);
                    String subareaone_geographic_code = json_sub_area_three_obj.getString("geographic_code");
                    String subarea_one_code = json_sub_area_three_obj.getString("subarea_one_id");
                    String subarea_one_name = json_sub_area_three_obj.getString("subarea_one_name");
                    String store_id = json_sub_area_three_obj.getString("store_id");
                    String stores_name = json_sub_area_three_obj.getString("stores_location");

                    //sqLiteAdapter.insert_scope(id,scope_code, scope_name);
                    sqLiteAdapter.insert_msubarea_one(subareaone_geographic_code, subarea_one_code, subarea_one_name,store_id,stores_name);

                    responseSubAreaThree=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    public void getGeographic() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "geographic"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseGeographic=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_geographic = new JSONObject(response);
                JSONArray array_geographic = json_geographic.getJSONArray("resp");

                Log.e("array geographic", "" + array_geographic);

                for (int i = 0; i < array_geographic.length(); i++) {
                    JSONObject json_geographic_obj = array_geographic.getJSONObject(i);
                    String geographic_project_code = json_geographic_obj.getString("project_code");
                    String geographic_code = json_geographic_obj.getString("geographic_id");
                    String geographic_name = json_geographic_obj.getString("geographic_name");

                    // sqLiteAdapter.insert_block(dist_block_code, block_code, block_name);
                    sqLiteAdapter.insert_geographic(geographic_project_code, geographic_code, geographic_name);

                           /* Log.e(" dist_block_code", dist_block_code);
                            Log.e("block_code", block_code);
                            Log.e("block_name", block_name);*/

                    responseGeographic=true;
                }
            }


        } catch (Exception e) {
            //  Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }




    public void getSubAreaOne() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "subarea_one"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSubAreaOne=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_sub_area_one = new JSONObject(response);
                JSONArray array_sub_area_one = json_sub_area_one.getJSONArray("resp");
                Log.e("array sub_area_one", "" + array_sub_area_one);

                for (int i = 0; i < array_sub_area_one.length(); i++) {
                    JSONObject json_sub_area_one_obj = array_sub_area_one.getJSONObject(i);
                    String subareaone_geographic_code = json_sub_area_one_obj.getString("geographic_code");
                    String subarea_one_code = json_sub_area_one_obj.getString("subarea_one_id");
                    String subarea_one_name = json_sub_area_one_obj.getString("subarea_one_name");

                    //sqLiteAdapter.insert_scope(id,scope_code, scope_name);
                    sqLiteAdapter.insert_subarea_one(subareaone_geographic_code, subarea_one_code, subarea_one_name);

                    responseSubAreaOne=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    public void getSubAreaTwo() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "subarea_two"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSubAreaTwo=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_sub_area_two = new JSONObject(response);
                JSONArray array_sub_area_two = json_sub_area_two.getJSONArray("resp");
                Log.e("array subarea_two", "" + array_sub_area_two);

                for (int i = 0; i < array_sub_area_two.length(); i++) {
                    JSONObject json_sub_area_two_obj = array_sub_area_two.getJSONObject(i);
                    String subareatwo_one_code = json_sub_area_two_obj.getString("subarea_one_code");
                    String subarea_two_code = json_sub_area_two_obj.getString("subarea_two_id");
                    String subarea_two_name = json_sub_area_two_obj.getString("subarea_two_name");

                    //sqLiteAdapter.insert_scope(id,scope_code, scope_name);
                    sqLiteAdapter.insert_subarea_two(subareatwo_one_code, subarea_two_code, subarea_two_name);

                    responseSubAreaTwo=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }





    public void getInsp_ReInsp() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "inspection_reinsp"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseInsp_ReInsp=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_Insp_ReInsp = new JSONObject(response);
                JSONArray array_Insp_ReInsp = json_Insp_ReInsp.getJSONArray("resp");
                Log.e("array Insp_ReInsp", "" + array_Insp_ReInsp);

                for (int i = 0; i < array_Insp_ReInsp.length(); i++) {
                    JSONObject json_Insp_ReInsp_obj = array_Insp_ReInsp.getJSONObject(i);
                    String subarea_three_code = json_Insp_ReInsp_obj.getString("subarea_three_code");
                    String inspection_reinsp_id = json_Insp_ReInsp_obj.getString("inspection_reinsp_id");
                    String inspection_reinspection_name = json_Insp_ReInsp_obj.getString("inspection_reinspection_name");

                    //sqLiteAdapter.insert_scope(id,scope_code, scope_name);
                    sqLiteAdapter.insert_inspection_reinspection(subarea_three_code, inspection_reinsp_id, inspection_reinspection_name);

                    responseInsp_ReInsp=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }



    public void getTkc() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "tkc"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseTkc=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_Insp_ReInsp = new JSONObject(response);
                JSONArray array_Insp_ReInsp = json_Insp_ReInsp.getJSONArray("resp");
                Log.e("array TKC", "" + array_Insp_ReInsp);

                for (int i = 0; i < array_Insp_ReInsp.length(); i++) {
                    JSONObject json_Insp_ReInsp_obj = array_Insp_ReInsp.getJSONObject(i);
                    String tkc_id = json_Insp_ReInsp_obj.getString("id");
                    String tkc_name = json_Insp_ReInsp_obj.getString("sub_area2");
                    String district_id = json_Insp_ReInsp_obj.getString("scope_id");
                    String store_id = json_Insp_ReInsp_obj.getString("qty");

                    sqLiteAdapter.insert_tkc(tkc_id, tkc_name, district_id, store_id);
                    responseTkc=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    //------------------Factory Item--------------------------------//
    public void getmaterial_Item() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "m_item"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){
                response_Item=false;
            }
            else {
                //   Log.e("block", response);
                JSONObject json_Item = new JSONObject(response);
                JSONArray array_Item = json_Item.getJSONArray("resp");
                Log.e("array Material item", "" + array_Item);

                for (int i = 0; i < array_Item.length(); i++) {
                    JSONObject json_Item_obj = array_Item.getJSONObject(i);
                    String item_id = json_Item_obj.getString("id");
                    String item_name = json_Item_obj.getString("item_name");
                    String specs_no = json_Item_obj.getString("unit");

                    sqLiteAdapter.insert_item(item_id, item_name,specs_no);
                    response_Item=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for  factory Material Sub Item--------------------------------//
    public void getmaterial_subitem() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "material_subitem"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyinfeeder=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyinfeeder = new JSONObject(response);
                JSONArray array_Surveyinfeeder = json_surveyinfeeder.getJSONArray("resp");
                Log.e("array MaterialSubItem", "" + array_Surveyinfeeder);

                for (int i = 0; i < array_Surveyinfeeder.length(); i++) {
                    JSONObject json_surveyinfeeder_obj = array_Surveyinfeeder.getJSONObject(i);
                    String id = json_surveyinfeeder_obj.getString("id");
                    String itemid = json_surveyinfeeder_obj.getString("item_id");
                    String subitem_name = json_surveyinfeeder_obj.getString("subitem_name");
                    sqLiteAdapter.insert_survey_infeeder(id, itemid, subitem_name);

                    responseSurveyinfeeder=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }



    // --------------for supplier for factory Inspection --------------------------------//
    public void getType_work() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "type_work"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        nameValuePairs.add(new BasicNameValuePair("mdistrict", district_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurvey=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_Survey = new JSONObject(response);
                JSONArray array_Survey = json_Survey.getJSONArray("resp");
                Log.e("array type_work", "" + array_Survey);

                for (int i = 0; i < array_Survey.length(); i++) {
                    JSONObject json_Survey_obj = array_Survey.getJSONObject(i);
                    String id = json_Survey_obj.getString("id");
                    String stage_id = json_Survey_obj.getString("stage_id");
                    String work_name = json_Survey_obj.getString("work_name");
                    String units = json_Survey_obj.getString("units");
                    String check_list = json_Survey_obj.getString("check_list");
                    String mobile_status = json_Survey_obj.getString("mobile_status");

                    sqLiteAdapter.insert_survey_id(id, stage_id, work_name,units,check_list,mobile_status);

                    responseSurvey=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }



    // --------------for Choose item--------------------------------//
    public void getChoose_Item() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "choose_item"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseChoose_Item=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_Choose_Item = new JSONObject(response);
                JSONArray array_Choose_Item = json_Choose_Item.getJSONArray("resp");
                Log.e("array choose_item", "" + array_Choose_Item);

                for (int i = 0; i < array_Choose_Item.length(); i++) {
                    JSONObject json_Choose_Item_obj = array_Choose_Item.getJSONObject(i);

                    String id = json_Choose_Item_obj.getString("id");
                    String para_name = json_Choose_Item_obj.getString("parameter_name");
                    String work_id = json_Choose_Item_obj.getString("work_id");
                    String activity_id = json_Choose_Item_obj.getString("activity_id");

                    //sqLiteAdapter.insert_scope(id,scope_code, scope_name);
                    sqLiteAdapter.insert_choose_item(id, para_name, work_id,activity_id);

                    responseChoose_Item=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for asset Type--------------------------------//
   /* public void getAsset() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "asset_type"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseAsset=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_asset = new JSONObject(response);
                JSONArray array_Asset = json_asset.getJSONArray("resp");
                Log.e("array asset_type", "" + array_Asset);

                for (int i = 0; i < array_Asset.length(); i++) {
                    JSONObject json_Asset_obj = array_Asset.getJSONObject(i);
                    String asset_name = json_Asset_obj.getString("asset_name");

                    sqLiteAdapter.insert_asset_type(asset_name);

                    responseAsset=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }*/


    // --------------for Inspection SubItem--------------------------------//
    public void getInspection_subitem() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "inspection_subitem"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveySubstation=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveysubs = new JSONObject(response);
                JSONArray array_Surveysubs = json_surveysubs.getJSONArray("resp");
                Log.e("array insp_subitem1", "" + array_Surveysubs);

                for (int i = 0; i < array_Surveysubs.length(); i++) {
                    JSONObject json_surveysubs_obj = array_Surveysubs.getJSONObject(i);
                    String id = json_surveysubs_obj.getString("id");
                    String subitem_name = json_surveysubs_obj.getString("activity_name");
                    String main_item_id = json_surveysubs_obj.getString("work_id");
                    String units = json_surveysubs_obj.getString("units");
                    String check_list = json_surveysubs_obj.getString("check_list");

                    sqLiteAdapter.insert_inspection_subitem(id, subitem_name,main_item_id,units,check_list);

                    responseSurveySubstation=true;
                   /*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*/

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }



    // --------------for Survey feeder--------------------------------//
    /*public void getSurveyFeeder() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_feeder"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyFeeder=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyfeeder = new JSONObject(response);
                JSONArray array_Surveyfeeder = json_surveyfeeder.getJSONArray("resp");
                Log.e("array survey_feeder", "" + array_Surveyfeeder);

                for (int i = 0; i < array_Surveyfeeder.length(); i++) {
                    JSONObject json_surveyfeeder_obj = array_Surveyfeeder.getJSONObject(i);
                    String substation_id = json_surveyfeeder_obj.getString("substation_id");
                    String feeder_name = json_surveyfeeder_obj.getString("feeder_name");

                    sqLiteAdapter.insert_surveyfeeder(substation_id, feeder_name);

                    responseSurveyFeeder=true;
                     *//*Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }
*/

    // --------------for Survey feeder--------------------------------//
  /*  public void getSurveyDtrRating() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_dtr_rating"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyDtrRating=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyrating = new JSONObject(response);
                JSONArray array_Surveyrating = json_surveyrating.getJSONArray("resp");
                Log.e("array survey_rating", "" + array_Surveyrating);

                for (int i = 0; i < array_Surveyrating.length(); i++) {
                    JSONObject json_surveyrating_obj = array_Surveyrating.getJSONObject(i);
                    String dtr_rating = json_surveyrating_obj.getString("dtr_rating");

                    sqLiteAdapter.insert_survey_dtrrating(dtr_rating);

                    responseSurveyDtrRating=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey feeder--------------------------------//
    public void getSurveyDtrMouting() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_dtr_mouting"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyDtrMouting=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveymouting = new JSONObject(response);
                JSONArray array_Surveymouting = json_surveymouting.getJSONArray("resp");
                Log.e("array survey_mouting", "" + array_Surveymouting);

                for (int i = 0; i < array_Surveymouting.length(); i++) {
                    JSONObject json_surveymouting_obj = array_Surveymouting.getJSONObject(i);
                    String dtr_mouting = json_surveymouting_obj.getString("dtr_mouting");

                    sqLiteAdapter.insert_survey_dtrmouting(dtr_mouting);

                    responseSurveyDtrMouting=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey substation voltage--------------------------------//
    public void getSurveySvoltage() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_svoltage"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveySvoltage=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveysvolatge = new JSONObject(response);
                JSONArray array_Surveysvoltage = json_surveysvolatge.getJSONArray("resp");
                Log.e("array Surveysvoltage", "" + array_Surveysvoltage);

                for (int i = 0; i < array_Surveysvoltage.length(); i++) {
                    JSONObject json_surveysvoltage_obj = array_Surveysvoltage.getJSONObject(i);
                    String svolatge_id = json_surveysvoltage_obj.getString("id");
                    String substation_voltage = json_surveysvoltage_obj.getString("substation_voltage");
                    sqLiteAdapter.insert_survey_substationvoltage(svolatge_id, substation_voltage);

                    responseSurveySvoltage=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey OUTfeeder--------------------------------//
    public void getSurveyoutfeeder() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_outfeeder"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyoutfeeder=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyoutfeeder = new JSONObject(response);
                JSONArray array_Surveyoutfeeder = json_surveyoutfeeder.getJSONArray("resp");
                Log.e("array Outgoingfeeder", "" + array_Surveyoutfeeder);

                for (int i = 0; i < array_Surveyoutfeeder.length(); i++) {
                    JSONObject json_surveyoutfeeder_obj = array_Surveyoutfeeder.getJSONObject(i);
                    String infeeder_id = json_surveyoutfeeder_obj.getString("id");
                    String substation_id = json_surveyoutfeeder_obj.getString("substation_id");
                    String feeder_name = json_surveyoutfeeder_obj.getString("feeder_name");
                    sqLiteAdapter.insert_survey_outfeeder(infeeder_id, substation_id, feeder_name);

                    responseSurveyoutfeeder=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey PT Capacity--------------------------------//
    public void getSurveypt() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_ptcapacity"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveypt=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveypt = new JSONObject(response);
                JSONArray array_Surveypt = json_surveypt.getJSONArray("resp");
                Log.e("array Pt Capacity", "" + array_Surveypt);

                for (int i = 0; i < array_Surveypt.length(); i++) {
                    JSONObject json_surveypt_obj = array_Surveypt.getJSONObject(i);
                    String ptcapacity_id = json_surveypt_obj.getString("id");
                    String pt_capacity = json_surveypt_obj.getString("pt_capacity");
                    sqLiteAdapter.insert_survey_ptcapacity(ptcapacity_id, pt_capacity);

                    responseSurveypt=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey SwitchGear--------------------------------//
    public void getSurveySwitchgear() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_switchgear"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyswitchgear=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyswitchgear = new JSONObject(response);
                JSONArray array_Surveyswitchgear = json_surveyswitchgear.getJSONArray("resp");
                Log.e("array Switch Gear", "" + array_Surveyswitchgear);

                for (int i = 0; i < array_Surveyswitchgear.length(); i++) {
                    JSONObject json_surveyswitch_obj = array_Surveyswitchgear.getJSONObject(i);
                    String switchgear_id = json_surveyswitch_obj.getString("id");
                    String switchgear_type = json_surveyswitch_obj.getString("switchgear_type");
                    sqLiteAdapter.insert_survey_switchgear(switchgear_id, switchgear_type);

                    responseSurveyswitchgear=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey Location--------------------------------//
    public void getSurveyLocation() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_location"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveylocation=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveylocation = new JSONObject(response);
                JSONArray array_Surveylocation = json_surveylocation.getJSONArray("resp");
                Log.e("array Location", "" + array_Surveylocation);

                for (int i = 0; i < array_Surveylocation.length(); i++) {
                    JSONObject json_surveylocation_obj = array_Surveylocation.getJSONObject(i);
                    String location_id = json_surveylocation_obj.getString("id");
                    String location_name = json_surveylocation_obj.getString("location_name");
                    sqLiteAdapter.insert_survey_location(location_id, location_name);

                    responseSurveylocation=true;
                   *//*  Log.e(" scope_code", scope_code);
                            Log.e("scope_name", scope_name);*//*

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey Cable Length--------------------------------//
    public void getSurveyLength() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_length"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyLength=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveylength = new JSONObject(response);
                JSONArray array_Surveylength = json_surveylength.getJSONArray("resp");
                Log.e("array Cable length", "" + array_Surveylength);

                for (int i = 0; i < array_Surveylength.length(); i++) {
                    JSONObject json_surveylength_obj = array_Surveylength.getJSONObject(i);
                    String cable_id = json_surveylength_obj.getString("id");
                    String cable_length = json_surveylength_obj.getString("cable_length");
                    sqLiteAdapter.insert_survey_length(cable_id, cable_length);

                    responseSurveyLength=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey Phase--------------------------------//
    public void getSurveyPhase() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_phase"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyPhase=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyPhase = new JSONObject(response);
                JSONArray array_Surveyphase = json_surveyPhase.getJSONArray("resp");
                Log.e("array phase", "" + array_Surveyphase);

                for (int i = 0; i < array_Surveyphase.length(); i++) {
                    JSONObject json_surveyphase_obj = array_Surveyphase.getJSONObject(i);
                    String phase_id = json_surveyphase_obj.getString("id");
                    String phase = json_surveyphase_obj.getString("phase");
                    sqLiteAdapter.insert_survey_phase(phase_id, phase);
                    responseSurveyPhase=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey Consumer Type--------------------------------//
    public void getSurveyConsumerType() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_consumertype"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyConsumerType=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyconsumert = new JSONObject(response);
                JSONArray array_Surveyconsumert = json_surveyconsumert.getJSONArray("resp");
                Log.e("array Consumer Type", "" + array_Surveyconsumert);

                for (int i = 0; i < array_Surveyconsumert.length(); i++) {
                    JSONObject json_surveyconsumert_obj = array_Surveyconsumert.getJSONObject(i);
                    String consumer_type_id = json_surveyconsumert_obj.getString("id");
                    String consumer_type = json_surveyconsumert_obj.getString("consumer_type");
                    sqLiteAdapter.insert_survey_consumertype(consumer_type_id, consumer_type);

                    responseSurveyConsumerType=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey Category--------------------------------//
    public void getSurveyCategory() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "si_item"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                responseSurveyCategory=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveycategory = new JSONObject(response);
                JSONArray array_Surveycategory = json_surveycategory.getJSONArray("resp");
                Log.e("array Category", "" + array_Surveycategory);

                for (int i = 0; i < array_Surveycategory.length(); i++) {
                    JSONObject json_surveycategory_obj = array_Surveycategory.getJSONObject(i);
                    String category_id = json_surveycategory_obj.getString("id");
                    String category = json_surveycategory_obj.getString("activity_name");
                    sqLiteAdapter.insert_survey_category(category_id, category);

                    responseSurveyCategory=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }*/

    // --------------for Survey PoleType--------------------------------//
   /* public void getSurveyPoleType() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_poletype"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respPoleType=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveypoletype = new JSONObject(response);
                JSONArray array_Surveypoletype = json_surveypoletype.getJSONArray("resp");
                Log.e("array POLE TYPE", "" + array_Surveypoletype);

                for (int i = 0; i < array_Surveypoletype.length(); i++) {
                    JSONObject json_surveypoletype_obj = array_Surveypoletype.getJSONObject(i);
                    String id = json_surveypoletype_obj.getString("id");
                    String pole_type = json_surveypoletype_obj.getString("pole_type");
                    sqLiteAdapter.insert_survey_poletype(id, pole_type);

                    respPoleType=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey PoleCondition--------------------------------//
    public void getSurveyPoleCondition() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_polecondition"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respPoleCondition=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveypolecondition = new JSONObject(response);
                JSONArray array_Surveypolecondition = json_surveypolecondition.getJSONArray("resp");
                Log.e("array POLE condition", "" + array_Surveypolecondition);

                for (int i = 0; i < array_Surveypolecondition.length(); i++) {
                    JSONObject json_surveypolecondition_obj = array_Surveypolecondition.getJSONObject(i);
                    String id = json_surveypolecondition_obj.getString("id");
                    String pole_condition = json_surveypolecondition_obj.getString("pole_condition");
                    sqLiteAdapter.insert_survey_poletcondition(id, pole_condition);

                    respPoleCondition=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey PoleLocation--------------------------------//
    public void getSurveyPoleLocation() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_polelocation"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respPoleLocation=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveypolelocation = new JSONObject(response);
                JSONArray array_Surveypolelocation = json_surveypolelocation.getJSONArray("resp");
                Log.e("array POLE Location", "" + array_Surveypolelocation);

                for (int i = 0; i < array_Surveypolelocation.length(); i++) {
                    JSONObject json_surveypolelocation_obj = array_Surveypolelocation.getJSONObject(i);
                    String id = json_surveypolelocation_obj.getString("id");
                    String pole_location = json_surveypolelocation_obj.getString("pole_location");
                    sqLiteAdapter.insert_survey_poletlocation(id, pole_location);

                    respPoleLocation=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey Insulator Type--------------------------------//
    public void getSurveyInsulatorType() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_insulatortype"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respInsulatorType=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyinsulatortype = new JSONObject(response);
                JSONArray array_Surveyinsulatortype = json_surveyinsulatortype.getJSONArray("resp");
                Log.e("array Insulator Type", "" + array_Surveyinsulatortype);

                for (int i = 0; i < array_Surveyinsulatortype.length(); i++) {
                    JSONObject json_surveyinsulatortype_obj = array_Surveyinsulatortype.getJSONObject(i);
                    String id = json_surveyinsulatortype_obj.getString("id");
                    String insulator_type = json_surveyinsulatortype_obj.getString("insulator_type");
                    sqLiteAdapter.insert_survey_insulatortype(id, insulator_type);

                    respInsulatorType=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey Insulator Condition--------------------------------//
    public void getSurveyInsulatorCondition() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_insulatorcondition"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respInsulatorCondition=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyinsulatorcondition = new JSONObject(response);
                JSONArray array_Surveyinsulatorcondition = json_surveyinsulatorcondition.getJSONArray("resp");
                Log.e("array Insulator Condit", "" + array_Surveyinsulatorcondition);


                for (int i = 0; i < array_Surveyinsulatorcondition.length(); i++) {
                    JSONObject json_surveyinsulatorcondition_obj = array_Surveyinsulatorcondition.getJSONObject(i);
                    String id = json_surveyinsulatorcondition_obj.getString("id");
                    String insulator_condition = json_surveyinsulatorcondition_obj.getString("insulator_condition");
                    sqLiteAdapter.insert_survey_insulatorcondition(id, insulator_condition);

                    respInsulatorCondition=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }



    // --------------for Survey Stay Type--------------------------------//
    public void getSurveyStayType() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_staytype"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respStayType=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveystaytype = new JSONObject(response);
                JSONArray array_Surveystaytype = json_surveystaytype.getJSONArray("resp");
                Log.e("array Stay Type", "" + array_Surveystaytype);

                for (int i = 0; i < array_Surveystaytype.length(); i++) {
                    JSONObject json_surveystaytype_obj = array_Surveystaytype.getJSONObject(i);
                    String id = json_surveystaytype_obj.getString("id");
                    String stay_type = json_surveystaytype_obj.getString("stay_type");
                    sqLiteAdapter.insert_survey_staytype(id, stay_type);
                    respStayType=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey Stay Condition--------------------------------//
    public void getSurveyStayCondition() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_staycondition"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respStayCondition=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveystaycondition = new JSONObject(response);
                JSONArray array_Surveystaycondition = json_surveystaycondition.getJSONArray("resp");
                Log.e("array Stay Condition", "" + array_Surveystaycondition);
                for (int i = 0; i < array_Surveystaycondition.length(); i++) {
                    JSONObject json_surveystaycondition_obj = array_Surveystaycondition.getJSONObject(i);
                    String id = json_surveystaycondition_obj.getString("id");
                    String stay_condition = json_surveystaycondition_obj.getString("stay_condition");
                    sqLiteAdapter.insert_survey_staycondition(id, stay_condition);

                    respStayCondition=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }




    // --------------for Survey Guarding Type--------------------------------//
    public void getSurveyGuardingType() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_guardingtype"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respGuardingType=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyguardingtype = new JSONObject(response);
                JSONArray array_Surveyguardingtype = json_surveyguardingtype.getJSONArray("resp");
                Log.e("array guarding type", "" + array_Surveyguardingtype);

                for (int i = 0; i < array_Surveyguardingtype.length(); i++) {
                    JSONObject json_surveyguardingtype_obj = array_Surveyguardingtype.getJSONObject(i);
                    String id = json_surveyguardingtype_obj.getString("id");
                    String guarging_type = json_surveyguardingtype_obj.getString("guarging_type");
                    sqLiteAdapter.insert_survey_guardingtype(id, guarging_type);

                    respGuardingType=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey Guarding Condition--------------------------------//
    public void getSurveyGuardingCondition() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_guardingcondition"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respGuardingCondition=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyguardingcondition = new JSONObject(response);
                JSONArray array_Surveyguardingcondition = json_surveyguardingcondition.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array guarding condi", "" + array_Surveyguardingcondition);

                for (int i = 0; i < array_Surveyguardingcondition.length(); i++) {
                    JSONObject json_surveyguardingcondition_obj = array_Surveyguardingcondition.getJSONObject(i);
                    String id = json_surveyguardingcondition_obj.getString("id");
                    String guarding_condition = json_surveyguardingcondition_obj.getString("guarding_condition");
                    sqLiteAdapter.insert_survey_guardingcondition(id, guarding_condition);

                    respGuardingCondition=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }



    // --------------for Survey Earthing Type--------------------------------//
    public void getSurveyEarthingType() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_earthingtype"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respEarthingType=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyearthingtype = new JSONObject(response);
                JSONArray array_Surveyearthingtype = json_surveyearthingtype.getJSONArray("resp");
                Log.e("array Earthing type", "" + array_Surveyearthingtype);

                for (int i = 0; i < array_Surveyearthingtype.length(); i++) {
                    JSONObject json_surveyearthingtype_obj = array_Surveyearthingtype.getJSONObject(i);
                    String id = json_surveyearthingtype_obj.getString("id");
                    String earthing_type = json_surveyearthingtype_obj.getString("earthing_type");
                    sqLiteAdapter.insert_survey_earthingtype(id, earthing_type);

                    respEarthingType=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey Earthing Condition--------------------------------//
    public void getSurveyEarthingCondition() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_earthingcondition"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respEarthingCondition=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyearthingcondition = new JSONObject(response);
                JSONArray array_Surveyearthingcondition = json_surveyearthingcondition.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array Earthing condi", "" + array_Surveyearthingcondition);

                for (int i = 0; i < array_Surveyearthingcondition.length(); i++) {
                    JSONObject json_surveyearthingcondition_obj = array_Surveyearthingcondition.getJSONObject(i);
                    String id = json_surveyearthingcondition_obj.getString("id");
                    String earthing_condition = json_surveyearthingcondition_obj.getString("earthing_condition");
                    sqLiteAdapter.insert_survey_earthingcondition(id, earthing_condition);

                    respEarthingCondition=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey Crossing--------------------------------//
    public void getSurveyCrossing() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_crossing"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respCrossing=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveycrossing = new JSONObject(response);
                JSONArray array_Surveycrossing = json_surveycrossing.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array Crossing", "" + array_Surveycrossing);

                for (int i = 0; i < array_Surveycrossing.length(); i++) {
                    JSONObject json_surveycrossing_obj = array_Surveycrossing.getJSONObject(i);
                    String id = json_surveycrossing_obj.getString("id");
                    String crossing = json_surveycrossing_obj.getString("crossing");
                    sqLiteAdapter.insert_survey_crossing(id, crossing);

                    respCrossing=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }*/


    // --------------for Workprogress scope of work--------------------------------//
    public void getWorkprogressScope() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "workprogress_scope"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respsurveyscope=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_workprogressscope = new JSONObject(response);
                JSONArray array_Workprogressscope = json_workprogressscope.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array Workprogres Scope", "" + array_Workprogressscope);

                for (int i = 0; i < array_Workprogressscope.length(); i++) {
                    JSONObject json_workprogressscope_obj = array_Workprogressscope.getJSONObject(i);
                    String id = json_workprogressscope_obj.getString("id");
                    String name_of_work = json_workprogressscope_obj.getString("name_of_work");
                    sqLiteAdapter.insert_workprogress_scope(id, name_of_work);

                    respsurveyscope=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for Survey Conductor Type--------------------------------//
   /* public void getSurveyConductorType() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_conductortype"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respConductorType=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyconductortype = new JSONObject(response);
                JSONArray array_Surveyconductortype = json_surveyconductortype.getJSONArray("resp");
                Log.e("array conductor type", "" + array_Surveyconductortype);

                for (int i = 0; i < array_Surveyconductortype.length(); i++) {
                    JSONObject json_surveyconductortype_obj = array_Surveyconductortype.getJSONObject(i);
                    String id = json_surveyconductortype_obj.getString("id");
                    String conductor_type = json_surveyconductortype_obj.getString("conductor_type");
                    sqLiteAdapter.insert_survey_conductortype(id, conductor_type);

                    respConductorType=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }

    // --------------for Survey Conductor Condition--------------------------------//
    public void getSurveyConductorCondition() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "survey_conductorcondition"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respConductorCondition=false;
            }
            else {

                //   Log.e("block", response);
                JSONObject json_surveyconductorcondition = new JSONObject(response);
                JSONArray array_Surveyconductorcondition = json_surveyconductorcondition.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array conductor condi", "" + array_Surveyconductorcondition);

                for (int i = 0; i < array_Surveyconductorcondition.length(); i++) {
                    JSONObject json_surveyconductorcondition_obj = array_Surveyconductorcondition.getJSONObject(i);
                    String id = json_surveyconductorcondition_obj.getString("id");
                    String conductor_condition = json_surveyconductorcondition_obj.getString("conductor_condition");
                    sqLiteAdapter.insert_survey_conductorcondition(id, conductor_condition);

                    respConductorCondition=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }*/


    // --------------for get Master Dropdown--------------------------------//
    public void getMasterDropdown() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "LEVEL"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respMasterdropdown=false;
            }
            else {

                Log.e("level", response);
                JSONObject json_masterdropdown = new JSONObject(response);
                JSONArray array_masterdropdown = json_masterdropdown.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array masterDropdown", "" + array_masterdropdown);

                for (int i = 0; i < array_masterdropdown.length(); i++) {
                    JSONObject json_masterdropdown_obj = array_masterdropdown.getJSONObject(i);
                    String level1 = json_masterdropdown_obj.getString("level1");
                    String level2 = json_masterdropdown_obj.getString("level2");
                    String level3 = json_masterdropdown_obj.getString("level3");
                    String level4 = json_masterdropdown_obj.getString("level4");



                    sqLiteAdapter.insert_masterdropdown(level1, level2, level3, level4);
                    respMasterdropdown=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }


    // --------------for get Material Issued--------------------------------//
    public void getMaterialIssued() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag", "issue"));
        nameValuePairs.add(new BasicNameValuePair("mproject", project_id));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            if(response.equals(null)){

                respMatrialIssued=false;
            }
            else {

                //Log.e("material", response);
                JSONObject json_materialissued = new JSONObject(response);
                JSONArray array_materialissued = json_materialissued.getJSONArray("resp");
                //Log.e("array guarding Condition", "" + array_Surveyguardingcondition);
                Log.e("array Issue", "" + array_materialissued);

                for (int i = 0; i < array_materialissued.length(); i++) {
                    JSONObject json_materialissued_obj = array_materialissued.getJSONObject(i);
                    String issue_id = json_materialissued_obj.getString("id");
                    String issue_name = json_materialissued_obj.getString("issues_name");
                    String project_id = json_materialissued_obj.getString("project_id");
                    sqLiteAdapter.insert_material_issued(issue_id, issue_name, project_id);
                    respMatrialIssued=true;

                }
            }


        } catch (Exception e) {
            // Log.e("log_tag", "Error in http connection " + e.toString());

        }

    }





}



