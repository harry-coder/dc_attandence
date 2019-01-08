package com.dcnine_attendance.sqlite_adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nitinb on 09-02-2016.
 */
public class SQLiteAdapter1 {

    public static final String MYDATABASE_NAME ="EPMC";
    public static final int MYDATABASE_VERSION = 1;

    public static final String PROJECT_TABLE = "PROJECT_TABLE";
    public static final String PROJECT_TABLE_ID="id";
    public static final String PROJECT_ID="project_id";
    public static final String PROJECT_CODE = "project_code";
    public static final String PROJECT_NAME = "project_name";


    public static final String MGEOGRAPHIC_TABLE = "MGEOGRAPHIC_TABLE";
    public static final String MGEOGRAPHIC_TABLE_ID="id";
    public static final String MGEOGRAPHIC_PROJECT_CODE = "mgeographic_project_code";
    public static final String MGEOGRAPHIC_CODE = "mgeographic_code";
    public static final String MGEOGRAPHIC_NAME = "mgeographic_name";


    public static final String MSUBAREAONE_TABLE = "MSUBAREAONE_TABLE";
    public static final String MSUBAREAONE_TABLE_ID="id";
    public static final String MSUBAREAONE_GEOGRAPHIC_CODE="msubareaone_geographic_code";
    public static final String MSUBAREAONE_CODE = "msubareaone_code";
    public static final String MSUBAREAONE_NAME = "msubareaone_name";
    public static final String MSTORE_ID = "mstore_id";
    public static final String MSTORE_NAME = "mstore_name";



    public static final String GEOGRAPHIC_TABLE = "GEOGRAPHIC_TABLE";
    public static final String GEOGRAPHIC_TABLE_ID="id";
    public static final String GEOGRAPHIC_PROJECT_CODE = "geographic_project_code";
    public static final String GEOGRAPHIC_CODE = "geographic_code";
    public static final String GEOGRAPHIC_NAME = "geographic_name";


    public static final String SUBAREAONE_TABLE = "SUBAREAONE_TABLE";
    public static final String SUBAREAONE_TABLE_ID="id";
    public static final String SUBAREAONE_GEOGRAPHIC_CODE="subareaone_geographic_code";
    public static final String SUBAREAONE_CODE = "subareaone_code";
    public static final String SUBAREAONE_NAME = "subareaone_name";


    public static final String SUBAREATWO_TABLE = "SUBAREATWO_TABLE";
    public static final String SUBAREATWO_TABLE_ID="id";
    public static final String SUBAREATWO_ONE_CODE="subareatwo_one_code";
    public static final String SUBAREATWO_CODE = "subareatwo_code";
    public static final String SUBAREATWO_NAME = "subareatwo_name";


    public static final String SUBAREATHREE_TABLE = "SUBAREATHREE_TABLE";
    public static final String SUBAREATHREE_TABLE_ID="id";
    public static final String SUBAREATHREE_TWO_CODE="subareathree_two_code";
    public static final String SUBAREATHREE_CODE = "subareathree_code";
    public static final String SUBAREATHREE_NAME = "subareathree_name";


    public static final String INSPECTION_REINSPECTION_TABLE = "INSPECTION_REINSPECTION_TABLE";
    public static final String INSPECTION_REINSPECTION_TABLE_ID="id";
    public static final String INSPECTION_REINSPECTION_THREE_CODE="inspection_reinspection_three_code";
    public static final String INSPECTION_REINSPECTION_CODE = "inspection_reinspection_code";
    public static final String INSPECTION_REINSPECTION_NAME = "inspection_reinspection_name";


    public static final String TKC_TABLE = "TKC_TABLE";
    public static final String TKC_TABLE_ID="id";
    public static final String TKC_ID="tkc_id";
    public static final String TKC_PROJECT_ID="tkc_project_id";
    public static final String TKC_NAME = "tkc_name";
    public static final String STORE_ID = "store_id";


    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String ITEM_TABLE_ID="id";
    public static final String ITEM_ID="item_id";
    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_SPECS_NO = "item_specs_no";


    public static final String CHOOSE_ITEM_TABLE = "CHOOSE_ITEM_TABLE";
    public static final String CHOOSE_ITEM_TABLE_ID="id";
    public static final String CHOOSE_ITEM_CODE = "choose_item_code";
    public static final String CHOOSE_ITEM_NAME = "choose_item_name";
    public static final String CHOOSE_ITEM_MAPPING_CODE="choose_item_mapping_code";
    public static final String CHOOSE_ITEM_ACTIVITY_CODE="choose_item_activity_code";

    public static final String SURVEY_ID_TABLE = "SURVEY_ID_TABLE";
    public static final String SURVEY_ID_TABLE_ID="id";
    public static final String SURVEY_ID="survey_id";
    public static final String SURVEY_ID_PROJECT_CODE="survey_id_project_code";
    public static final String SURVEY_NAME_ID = "survey_name_id";
    public static final String SURVEY_ITEM_ID = "survey_item_id";
    public static final String SURVEY_SPEC_NO = "survey_specs_no";
    public static final String SURVEY_MOBILE_STATUS = "survey_mobile_status";

    public static final String ASSET_TYPE_TABLE = "ASSET_TYPE_TABLE";
    public static final String ASSET_TABLE_ID="id";
    public static final String ASSET_NAME = "asset_name";

    public static final String INSPECTION_SUBITEM_TABLE = "INSPECTION_SUBITEM_TABLE";
    public static final String INSPECTION_SUBITEM_TABLE_ID="id";
    public static final String INSPECTION_SUBITEM_ID="subitem_id";
    public static final String INSPECTION_SUBITEM_NAME = "subitem_name";
    public static final String INSPECTION_MAINITEM_ID = "mainitem_id";
    public static final String INSPECTION_UNITS_ID = "units_id";
    public static final String INSPECTION_CHECKLIST_ID = "checklist_id";

    public static final String SURVEY_FEEDER_TABLE = "SURVEY_FEEDER_TABLE";
    public static final String SURVEY_FEEDER_TABLE_ID="id";
    public static final String SURVEY_FEEDER_SUBSTATION_CODE="survey_feeder_substation_code";
    public static final String SURVEY_FEEDER_NAME = "survey_feeder_name";


    public static final String SURVEY_DTRRATING_TABLE = "SURVEY_DTRRATING_TABLE";
    public static final String SURVEY_DTRRATING_TABLE_ID="id";
    public static final String SURVEY_DTRRATING_NAME = "survey_dtrrating_name";

    public static final String SURVEY_DTRMOUTING_TABLE = "SURVEY_DTRMOUTING_TABLE";
    public static final String SURVEY_DTRMOUTING_TABLE_ID="id";
    public static final String SURVEY_DTRMOUTING_NAME = "survey_dtrmouting_name";

    public static final String SURVEY_SUBSTATIONVOLTAGE_TABLE = "SURVEY_SUBSTATIONVOLTAGE_TABLE";
    public static final String SURVEY_SVOLTAGE_TABLE_ID="id";
    public static final String SURVEY_SVOLATGE_ID="survey_svoltage_id";
    public static final String SURVEY_SVOLATGE_NAME = "survey_svoltage_name";


    public static final String SURVEY_INFEEDER_TABLE = "SURVEY_INFEEDER_TABLE";
    public static final String SURVEY_INFEEDER_TABLE_ID="id";
    public static final String SURVEY_INFEEDER_ID="survey_infeeder_id";
    public static final String SURVEY_SUBSTATION_INFEEDER_ID="survey_substation_infeeder_id";
    public static final String SURVEY_INFEEDER_NAME = "survey_infeeder_name";

    public static final String SURVEY_OUTFEEDER_TABLE = "SURVEY_OUTFEEDER_TABLE";
    public static final String SURVEY_OUTFEEDER_TABLE_ID="id";
    public static final String SURVEY_OUTFEEDER_ID="survey_outfeeder_id";
    public static final String SURVEY_SUBSTATION_OUTFEEDER_ID="survey_substation_outfeeder_id";
    public static final String SURVEY_OUTFEEDER_NAME = "survey_outfeeder_name";

    public static final String SURVEY_PTCAPACITY_TABLE = "SURVEY_PTCAPACITY_TABLE";
    public static final String SURVEY_PTCAPACITY_TABLE_ID="id";
    public static final String SURVEY_PTCAPACITY_ID="survey_ptcapacity_id";
    public static final String SURVEY_PTCAPACITY_NAME = "survey_ptcapacity_name";

    public static final String SURVEY_SWITCHGEAR_TABLE = "SURVEY_SWITCHGEAR_TABLE";
    public static final String SURVEY_SWITCHGEAR_TABLE_ID="id";
    public static final String SURVEY_SWITCHGEAR_ID="survey_switchgear_id";
    public static final String SURVEY_SWITCHGEAR_NAME = "survey_switchgear_name";

    public static final String SURVEY_LOCATION_TABLE = "SURVEY_LOCATION_TABLE";
    public static final String SURVEY_LOCATION_TABLE_ID="id";
    public static final String SURVEY_LOCATION_ID="survey_location_id";
    public static final String SURVEY_LOCATION_NAME = "survey_location_name";

    public static final String SURVEY_LENGTH_TABLE = "SURVEY_LENGTH_TABLE";
    public static final String SURVEY_LENGTH_TABLE_ID="id";
    public static final String SURVEY_LENGTH_ID="survey_length_id";
    public static final String SURVEY_LENGTH_NAME = "survey_length_name";

    public static final String SURVEY_PHASE_TABLE = "SURVEY_PHASE_TABLE";
    public static final String SURVEY_PHASE_TABLE_ID="id";
    public static final String SURVEY_PHASE_ID="survey_phase_id";
    public static final String SURVEY_PHASE_NAME = "survey_phase_name";

    public static final String SURVEY_CONSUMERTYPE_TABLE = "SURVEY_CONSUMERTYPE_TABLE";
    public static final String SURVEY_CONSUMERTYPE_TABLE_ID="id";
    public static final String SURVEY_CONSUMERTYPE_ID="survey_consumertype_id";
    public static final String SURVEY_CONSUMERTYPE_NAME = "survey_consumertype_name";

    public static final String SURVEY_CATEGORY_TABLE = "SURVEY_CATEGORY_TABLE";
    public static final String SURVEY_CATEGORY_TABLE_ID="id";
    public static final String SURVEY_CATEGORY_ID="survey_category_id";
    public static final String SURVEY_CATEGORY_NAME = "survey_category_name";

    public static final String SURVEY_POLETYPE_TABLE = "SURVEY_POLETYPE_TABLE";
    public static final String SURVEY_POLETYPE_TABLE_ID="id";
    public static final String SURVEY_POLETYPE_ID="survey_poletype_id";
    public static final String SURVEY_POLETYPE_NAME = "survey_poletype_name";

    public static final String SURVEY_POLECONDITION_TABLE = "SURVEY_POLECONDITION_TABLE";
    public static final String SURVEY_POLECONDITION_TABLE_ID="id";
    public static final String SURVEY_POLECONDITION_ID="survey_polecondition_id";
    public static final String SURVEY_POLECONDITION_NAME = "survey_polecondition_name";

    public static final String SURVEY_POLELOCATION_TABLE = "SURVEY_POLELOCATION_TABLE";
    public static final String SURVEY_POLELOCATION_TABLE_ID="id";
    public static final String SURVEY_POLELOCATION_ID="survey_polelocation_id";
    public static final String SURVEY_POLELOCATION_NAME = "survey_polelocation_name";

    public static final String SURVEY_INSULATORTYPE_TABLE = "SURVEY_INSULATORTYPE_TABLE";
    public static final String SURVEY_INSULATORTYPE_TABLE_ID="id";
    public static final String SURVEY_INSULATORTYPE_ID="survey_insulatortype_id";
    public static final String SURVEY_INSULATORTYPE_NAME = "survey_insulatortype_name";

    public static final String SURVEY_INSULATORCONDITION_TABLE = "SURVEY_INSULATORCONDITION_TABLE";
    public static final String SURVEY_INSULATORCONDITION_TABLE_ID="id";
    public static final String SURVEY_INSULATORCONDITION_ID="survey_insulatorcondition_id";
    public static final String SURVEY_INSULATORCONDITION_NAME = "survey_insulatorcondition_name";

    public static final String SURVEY_STAYTYPE_TABLE = "SURVEY_STAYTYPE_TABLE";
    public static final String SURVEY_STAYTYPE_TABLE_ID="id";
    public static final String SURVEY_STAYTYPE_ID="survey_staytype_id";
    public static final String SURVEY_STAYTYPE_NAME = "survey_staytype_name";

    public static final String SURVEY_STAYCONDITION_TABLE = "SURVEY_STAYCONDITION_TABLE";
    public static final String SURVEY_STAYCONDITION_TABLE_ID="id";
    public static final String SURVEY_STAYCONDITION_ID="survey_staycondition_id";
    public static final String SURVEY_STAYCONDITION_NAME = "survey_staycondition_name";


    public static final String SURVEY_GUARDINGTYPE_TABLE = "SURVEY_GUARDINGTYPE_TABLE";
    public static final String SURVEY_GUARDINGTYPE_TABLE_ID="id";
    public static final String SURVEY_GUARDINGTYPE_ID="survey_guardingtype_id";
    public static final String SURVEY_GUARDINGTYPE_NAME = "survey_guardingtype_name";

    public static final String SURVEY_GUARDINGCONDITION_TABLE = "SURVEY_GUARDINGCONDITION_TABLE";
    public static final String SURVEY_GUARDINGCONDITION_TABLE_ID="id";
    public static final String SURVEY_GUARDINGCONDITION_ID="survey_guardingcondition_id";
    public static final String SURVEY_GUARDINGCONDITION_NAME = "survey_guardingcondition_name";

    public static final String SURVEY_EARTHINGTYPE_TABLE = "SURVEY_EARTHINGTYPE_TABLE";
    public static final String SURVEY_EARTHINGTYPE_TABLE_ID="id";
    public static final String SURVEY_EARTHINGTYPE_ID="survey_earthingtype_id";
    public static final String SURVEY_EARTHINGTYPE_NAME = "survey_earthingtype_name";

    public static final String SURVEY_EARTHINGCONDITION_TABLE = "SURVEY_EARTHINGCONDITION_TABLE";
    public static final String SURVEY_EARTHINGCONDITION_TABLE_ID="id";
    public static final String SURVEY_EARTHINGCONDITION_ID="survey_earthingcondition_id";
    public static final String SURVEY_EARTHINGCONDITION_NAME = "survey_earthingcondition_name";

    public static final String SURVEY_CROSSING_TABLE = "SURVEY_CROSSING_TABLE";
    public static final String SURVEY_CROSSING_TABLE_ID="id";
    public static final String SURVEY_CROSSING_ID="survey_crossing_id";
    public static final String SURVEY_CROSSING_NAME = "survey_crossing_name";

    public static final String WORKPROGRESS_SCOPE_TABLE = "WORKPROGRESS_SCOPE_TABLE";
    public static final String WORKPROGRESS_SCOPE_TABLE_ID="id";
    public static final String WORKPROGRESS_SCOPE_ID="workprogress_scope_id";
    public static final String WORKPROGRESS_SCOPE_NAME = "workprogress_scope_name";


    public static final String SURVEY_CONDUCTORTYPE_TABLE = "SURVEY_CONDUCTOR_TABLE";
    public static final String SURVEY_CONDUCTORTYPE_TABLE_ID="id";
    public static final String SURVEY_CONDUCTORTYPE_ID="survey_conductortype_id";
    public static final String SURVEY_CONDUCTORTYPE_NAME = "survey_conductortype_name";

    public static final String SURVEY_CONDUCTORCONDITION_TABLE = "SURVEY_CONDUCTORCONDITION_TABLE";
    public static final String SURVEY_CONDUCTORCONDITION_TABLE_ID="id";
    public static final String SURVEY_CONDUCTORCONDITION_ID="survey_conductorcondition_id";
    public static final String SURVEY_CONDUCTORCONDITION_NAME = "survey_conductorcondition_name";

    public static final String MASTER_DROPDOWN_TABLE = "MASTER_DROPDOWN_TABLE";
    public static final String MASTER_DROPDOWN_TABLE_ID="id";
    public static final String MASTER_DROPDOWN_ID="master_dropdown_id";
    public static final String MASTER_DROPDOWN_GEOGRAPHIC = "master_dropdown_geographic";
    public static final String MASTER_DROPDOWN_LEVEL1 = "master_dropdown_level1";
    public static final String MASTER_DROPDOWN_LEVEL2 = "master_dropdown_level2";
    public static final String MASTER_DROPDOWN_LEVEL3 = "master_dropdown_level3";
    public static final String MASTER_DROPDOWN_LEVEL4 = "master_dropdown_level4";


    public static final String MATERIAL_ISSUED_TABLE = "MATERIAL_ISSUED_TABLE";
    public static final String MATERIAL_ISSUED_TABLE_ID="id";
    public static final String MATERIAL_ISSUED_TKC_ID="material_issued_tkc_id";
    public static final String MATERIAL_ISSUED_NAME = "material_issued_name";
    public static final String SCHEME_ID="scheme_id";




    public static final String NCR_TABLE = "NCR_TABLE";
    public static final String NCR_TABLE_ID="id";
    public static final String NCR_INSPECTION_ID="ncr_inspection_id";
    public static final String NCR_DISTRICT = "ncr_district";
    public static final String NCR_BLOCK = "ncr_block";
    public static final String NCR_VILLAGE = "ncr_village";
    public static final String NCR_REMARK = "ncr_remark";
    public static final String NCR_MOBILE_DATE = "ncr_mobiledate";
    public static final String NCR_NAME = "ncr_name";
    public static final String NCR_DISTRICTID = "ncr_districtid";
    public static final String NCR_SCHEME_ID = "ncr_scheme_id";
    public static final String NCR_ITEM_NAME = "ncr_item_name";
    public static final String NCR_TOLA = "ncr_tola";
    public static final String NCR_ITEM_ID = "ncr_item_id";


    // Create Site inspection table
    public static final String SI_TABLE = "SI_TABLE";
    public static final String SI_TABLE_ID="id";
    public static final String SI_EMP_ID="emp_id";
    public static final String SI_PROJECT = "si_project";
    public static final String SI_GEOGRAPHIC = "si_geographic";
    public static final String SI_SUBAREAONE = "si_subareaone";
    public static final String SI_SUBAREATWO = "si_subareatwo";
    public static final String SI_MAIN_ITEM = "si_main_item";
    public static final String SI_SUBITEM_CODE = "si_subitem_code";
    public static final String SI_UOM_STATUS = "si_uom_status";
    public static final String SI_ITEM_YES = "si_item_yes";
    public static final String SI_ITEM_CANCEL = "si_item_cancel";
    public static final String SI_CHECK_INPUT = "si_check_input";
    public static final String SI_CHECK_NA = "si_check_na";
    public static final String SI_LAT = "si_lat";
    public static final String SI_LONG = "si_long";
    public static final String SI_TIME = "si_time";
    public static final String SI_REMARK = "si_remark";
    public static final String SI_PASS_REWORK = "si_pass_rework";
    public static final String SI_IMAGE1_NAME = "si_image1_name";
    public static final String SI_IMAGE2_NAME = "si_image2_name";
    public static final String SI_IMAGE3_NAME = "si_image3_name";
    public static final String SI_IMAGE4_NAME = "si_image4_name";
    public static final String SI_IMAGE5_NAME = "si_image5_name";
    public static final String SI_IMAGE1 = "si_image1";
    public static final String SI_IMAGE2 = "si_image2";
    public static final String SI_IMAGE3 = "si_image3";
    public static final String SI_IMAGE4 = "si_image4";
    public static final String SI_IMAGE5 = "si_image5";

  //..........Site Inspection table............................//
    private static final String CREATE_SI_TABLE =
            "create table " + SI_TABLE + " ("
                    + SI_TABLE_ID + " integer primary key autoincrement, "
                    + SI_EMP_ID + " text, "
                    + SI_PROJECT + " text, "
                    + SI_GEOGRAPHIC + " text, "
                    + SI_SUBAREAONE + " text, "
                    + SI_SUBAREATWO + " text, "
                    + SI_MAIN_ITEM+ " text, "
                    + SI_SUBITEM_CODE + " text, "
                    + SI_UOM_STATUS + " text, "
                    + SI_ITEM_YES + " text, "
                    + SI_ITEM_CANCEL + " text, "
                    + SI_CHECK_INPUT + " text, "
                    + SI_CHECK_NA + " text, "
                    + SI_LAT + " text, "
                    + SI_LONG + " text, "
                    + SI_TIME + " text, "
                    + SI_REMARK + " text, "
                    + SI_PASS_REWORK + " text, "
                    + SI_IMAGE1_NAME + " text, "
                    + SI_IMAGE2_NAME + " text, "
                    + SI_IMAGE3_NAME+ " text, "
                    + SI_IMAGE4_NAME + " text, "
                    + SI_IMAGE5_NAME + " text, "
                    + SI_IMAGE1 + " text, "
                    + SI_IMAGE2 + " text, "
                    + SI_IMAGE3 + " text, "
                    + SI_IMAGE4 + " text, "
                    + SI_IMAGE5 + " text  ) ; ";


    //............ insert SI..............//
    public long insert_value(String content0, String content1, String content2, String content3,
                             String content4, String content5, String content6, String content7,
                             String content8, String content9, String content10, String content11, String content12, String content13, String content14, String content15, String content16, String content17, String content18, String content19,
                             String content20, String content21, String content22, String content23, String content24, String content25, String content26){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SI_EMP_ID, content0);
        contentValues.put(SI_PROJECT, content1);
        contentValues.put(SI_GEOGRAPHIC, content2);
        contentValues.put(SI_SUBAREAONE, content3);
        contentValues.put(SI_SUBAREATWO, content4);
        contentValues.put(SI_MAIN_ITEM, content5);
        contentValues.put(SI_SUBITEM_CODE, content6);
        contentValues.put(SI_UOM_STATUS, content7);
        contentValues.put(SI_ITEM_YES, content8);
        contentValues.put(SI_ITEM_CANCEL, content9);
        contentValues.put(SI_CHECK_INPUT, content10);
        contentValues.put(SI_CHECK_NA, content11);
        contentValues.put(SI_LAT, content12);
        contentValues.put(SI_LONG, content13);
        contentValues.put(SI_TIME, content14);
        contentValues.put(SI_REMARK, content15);
        contentValues.put(SI_PASS_REWORK, content16);
        contentValues.put(SI_IMAGE1_NAME, content17);
        contentValues.put(SI_IMAGE2_NAME, content18);
        contentValues.put(SI_IMAGE3_NAME, content19);
        contentValues.put(SI_IMAGE4_NAME, content20);
        contentValues.put(SI_IMAGE5_NAME, content21);
        contentValues.put(SI_IMAGE1, content22);
        contentValues.put(SI_IMAGE2, content23);
        contentValues.put(SI_IMAGE3, content24);
        contentValues.put(SI_IMAGE4, content25);
        contentValues.put(SI_IMAGE5, content26);

        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SI_TABLE, null, contentValues);
    }



    public int delete_value_All(){
        return sqLiteDatabase.delete(SI_TABLE, null, null);
    }

    public void delete_value_byID(int id){
        sqLiteDatabase.delete(SI_TABLE, SI_TABLE_ID + "=" + id, null);
    }

    public int countData(){

        String countQuery = "SELECT  * FROM " + SI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
//	    cursor.close();
        return cnt;
    }

    public Cursor getdataFixdata(){

        String countQuery = "SELECT  * FROM " + SI_TABLE+ " limit 0 , 25";
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
      /*  Log.e("getfixdata", ""+cursor.getCount());
        Log.e("getfixcolumn", ""+cursor.getColumnCount());*/
//	    int cnt = cursor.getCount();
//	    cursor.close();

        return cursor;
    }

    public Cursor queueAll(){

        String countQuery = "SELECT  * FROM " + SI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor;
    }


    //........ project table.......................//
    private static final String CREATE_PROJECT_TABLE =
            "create table " + PROJECT_TABLE + " ("
                    + PROJECT_TABLE_ID + " integer primary key autoincrement, "
                    + PROJECT_ID + " text, "
                    + PROJECT_CODE + " text, "
                    + PROJECT_NAME + " text  ) ; ";




    //.........geographic table.........................//
    private static final String CREATE_GEOGRAPHIC_TABLE =
            "create table " + GEOGRAPHIC_TABLE + " ("
                    + GEOGRAPHIC_TABLE_ID + " integer primary key autoincrement, "
                    + GEOGRAPHIC_PROJECT_CODE + " text, "
                    + GEOGRAPHIC_CODE + " text, "
                    + GEOGRAPHIC_NAME + " text  ) ; ";


    //.........subarea_one name.........................//
    private static final String CREATE_SUBAREAONE_TABLE =
            "create table " + SUBAREAONE_TABLE + " ("
                    + SUBAREAONE_TABLE_ID + " integer primary key autoincrement, "
                    + SUBAREAONE_GEOGRAPHIC_CODE + " text, "
                    + SUBAREAONE_CODE + " text, "
                    + SUBAREAONE_NAME + " text  ) ; ";


    //.........geographic table.........................//
    private static final String CREATE_MGEOGRAPHIC_TABLE =
            "create table " + MGEOGRAPHIC_TABLE + " ("
                    + MGEOGRAPHIC_TABLE_ID + " integer primary key autoincrement, "
                    + MGEOGRAPHIC_PROJECT_CODE + " text, "
                    + MGEOGRAPHIC_CODE + " text, "
                    + MGEOGRAPHIC_NAME + " text  ) ; ";


    //.........subarea_one name.........................//
    private static final String CREATE_MSUBAREAONE_TABLE =
            "create table " + MSUBAREAONE_TABLE + " ("
                    + MSUBAREAONE_TABLE_ID + " integer primary key autoincrement, "
                    + MSUBAREAONE_GEOGRAPHIC_CODE + " text, "
                    + MSUBAREAONE_CODE + " text, "
                    + MSUBAREAONE_NAME + " text, "
                    + MSTORE_ID + " text, "
                    + MSTORE_NAME + " text  ) ; ";



    //.........subarea_two .........................//
    private static final String CREATE_SUBAREATWO_TABLE =
            "create table " + SUBAREATWO_TABLE + " ("
                    + SUBAREATWO_TABLE_ID + " integer primary key autoincrement, "
                    + SUBAREATWO_ONE_CODE + " text, "
                    + SUBAREATWO_CODE + " text, "
                    + SUBAREATWO_NAME + " text  ) ; ";



    //.........subarea_three .........................//
    private static final String CREATE_SUBAREATHREE_TABLE =
            "create table " + SUBAREATHREE_TABLE + " ("
                    + SUBAREATHREE_TABLE_ID + " integer primary key autoincrement, "
                    + SUBAREATHREE_TWO_CODE + " text, "
                    + SUBAREATHREE_CODE + " text, "
                    + SUBAREATHREE_NAME + " text  ) ; ";



    //.........inspection reinspection  .........................//
    private static final String CREATE_INSPECTION_REINSPECTION_TABLE =
            "create table " + INSPECTION_REINSPECTION_TABLE + " ("
                    + INSPECTION_REINSPECTION_TABLE_ID + " integer primary key autoincrement, "
                    + INSPECTION_REINSPECTION_THREE_CODE + " text, "
                    + INSPECTION_REINSPECTION_CODE + " text, "
                    + INSPECTION_REINSPECTION_NAME + " text  ) ; ";


    //........ project table.......................//
    private static final String CREATE_TKC_TABLE =
            "create table " + TKC_TABLE + " ("
                    + TKC_TABLE_ID + " integer primary key autoincrement, "
                    + TKC_ID + " text, "
                    + TKC_NAME + " text, "
                    + TKC_PROJECT_ID + " text, "
                    + STORE_ID + " text  ) ; ";


    //........ ITEM table.......................//
    private static final String CREATE_ITEM_TABLE =
            "create table " + ITEM_TABLE + " ("
                    + ITEM_TABLE_ID + " integer primary key autoincrement, "
                    + ITEM_ID + " text, "
                    + ITEM_NAME + " text, "
                    + ITEM_SPECS_NO + " text  ) ; ";


    //.........choose item table  .........................//
    private static final String CREATE_CHOOSE_ITEM_TABLE =
            "create table " + CHOOSE_ITEM_TABLE + " ("
                    + CHOOSE_ITEM_TABLE_ID + " integer primary key autoincrement, "
                    + CHOOSE_ITEM_CODE + " text, "
                    + CHOOSE_ITEM_NAME + " text, "
                    + CHOOSE_ITEM_MAPPING_CODE + " text, "
                    + CHOOSE_ITEM_ACTIVITY_CODE + " text  ) ; ";


    //........ SURVEY_ID table.......................//
    private static final String CREATE_SURVEY_NAME_ID_TABLE =
            "create table " + SURVEY_ID_TABLE + " ("
                    + SURVEY_ID_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_ID + " text, "
                    + SURVEY_ID_PROJECT_CODE + " text, "
                    + SURVEY_NAME_ID + " text, "
                    + SURVEY_ITEM_ID + " text, "
                    + SURVEY_SPEC_NO + " text, "
                    + SURVEY_MOBILE_STATUS+ " text  ) ; ";

    //........ ASSET TYPE  table.......................//
    private static final String CREATE_ASSET_TYPE_TABLE =
            "create table " + ASSET_TYPE_TABLE + " ("
                    + ASSET_TABLE_ID + " integer primary key autoincrement, "
                    + ASSET_NAME + " text  ) ; ";

    //........ Substation name  table.......................//
    private static final String CREATE_INSPECTION_SUBITEM_TABLE =
            "create table " + INSPECTION_SUBITEM_TABLE + " ("
                    + INSPECTION_SUBITEM_TABLE_ID + " integer primary key autoincrement, "
                    + INSPECTION_SUBITEM_ID + " text, "
                    + INSPECTION_SUBITEM_NAME + " text, "
                    + INSPECTION_MAINITEM_ID + " text, "
                    + INSPECTION_UNITS_ID + " text, "
                    + INSPECTION_CHECKLIST_ID + " text  ) ; ";

    //........ Feeder name  table.......................//
    private static final String CREATE_SURVEY_FEEDER_TABLE =
            "create table " + SURVEY_FEEDER_TABLE + " ("
                    + SURVEY_FEEDER_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_FEEDER_SUBSTATION_CODE + " text, "
                    + SURVEY_FEEDER_NAME + " text  ) ; ";


    //........ dtr RATING name  table.......................//
    private static final String CREATE_SURVEY_DTRRATING_TABLE =
            "create table " + SURVEY_DTRRATING_TABLE + " ("
                    + SURVEY_DTRRATING_TABLE_ID + " integer primary key autoincrement, "
                   + SURVEY_DTRRATING_NAME + " text  ) ; ";


    //........ dtr MOUTING name  table.......................//
    private static final String CREATE_SURVEY_DTRMOUTING_TABLE =
            "create table " + SURVEY_DTRMOUTING_TABLE + " ("
                    + SURVEY_DTRMOUTING_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_DTRMOUTING_NAME + " text  ) ; ";


    //........ SUBSTATION VOLTAGE NAME  table.......................//
    private static final String CREATE_SURVEY_SUBSTATIONVOLTAGE_TABLE =
            "create table " + SURVEY_SUBSTATIONVOLTAGE_TABLE + " ("
                    + SURVEY_SVOLTAGE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_SVOLATGE_ID + " text, "
                    + SURVEY_SVOLATGE_NAME + " text  ) ; ";

    //........ INFEEDER  table.......................//
    private static final String CREATE_SURVEY_INFEEDER_TABLE =
            "create table " + SURVEY_INFEEDER_TABLE + " ("
                    + SURVEY_INFEEDER_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_INFEEDER_ID + " text, "
                    + SURVEY_SUBSTATION_INFEEDER_ID + " text, "
                    + SURVEY_INFEEDER_NAME + " text  ) ; ";

    //........ OUTFEEDER  table.......................//
    private static final String CREATE_SURVEY_OUTFEEDER_TABLE =
            "create table " + SURVEY_OUTFEEDER_TABLE + " ("
                    + SURVEY_OUTFEEDER_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_OUTFEEDER_ID + " text, "
                    + SURVEY_SUBSTATION_OUTFEEDER_ID + " text, "
                    + SURVEY_OUTFEEDER_NAME + " text  ) ; ";

    //........ PT Capacity NAME  table.......................//
    private static final String CREATE_SURVEY_PTCAPACITY_TABLE =
            "create table " + SURVEY_PTCAPACITY_TABLE + " ("
                    + SURVEY_PTCAPACITY_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_PTCAPACITY_ID + " text, "
                    + SURVEY_PTCAPACITY_NAME + " text  ) ; ";

    //........ SWITCHGEAR NAME  table.......................//
    private static final String CREATE_SURVEY_SWITCHGEAR_TABLE =
            "create table " + SURVEY_SWITCHGEAR_TABLE + " ("
                    + SURVEY_SWITCHGEAR_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_SWITCHGEAR_ID + " text, "
                    + SURVEY_SWITCHGEAR_NAME + " text  ) ; ";

    //........ Survey Location NAME  table.......................//
    private static final String CREATE_SURVEY_LOCATION_TABLE =
            "create table " + SURVEY_LOCATION_TABLE + " ("
                    + SURVEY_LOCATION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_LOCATION_ID + " text, "
                    + SURVEY_LOCATION_NAME + " text  ) ; ";

    //........ Survey Cable Length NAME  table.......................//
    private static final String CREATE_SURVEY_LENGTH_TABLE =
            "create table " + SURVEY_LENGTH_TABLE + " ("
                    + SURVEY_LENGTH_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_LENGTH_ID + " text, "
                    + SURVEY_LENGTH_NAME + " text  ) ; ";

    //........ Survey Phase  table.......................//
    private static final String CREATE_SURVEY_PHASE_TABLE =
            "create table " + SURVEY_PHASE_TABLE + " ("
                    + SURVEY_PHASE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_PHASE_ID + " text, "
                    + SURVEY_PHASE_NAME + " text  ) ; ";


    //........ Survey Consumer Type  table.......................//
    private static final String CREATE_SURVEY_CONSUMERTYPE_TABLE =
            "create table " + SURVEY_CONSUMERTYPE_TABLE + " ("
                    + SURVEY_CONSUMERTYPE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_CONSUMERTYPE_ID + " text, "
                    + SURVEY_CONSUMERTYPE_NAME + " text  ) ; ";

    //........ Survey Category  table.......................//
    private static final String CREATE_SURVEY_CATEGORY_TABLE =
            "create table " + SURVEY_CATEGORY_TABLE + " ("
                    + SURVEY_CATEGORY_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_CATEGORY_ID + " text, "
                    + SURVEY_CATEGORY_NAME + " text  ) ; ";


    //........ Survey Ploe Type  table.......................//
    private static final String CREATE_SURVEY_POLETYPE_TABLE =
            "create table " + SURVEY_POLETYPE_TABLE + " ("
                    + SURVEY_POLETYPE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_POLETYPE_ID + " text, "
                    + SURVEY_POLETYPE_NAME + " text  ) ; ";

    //........ Survey Ploe Conditon  table.......................//
    private static final String CREATE_SURVEY_POLECONDITION_TABLE =
            "create table " + SURVEY_POLECONDITION_TABLE + " ("
                    + SURVEY_POLECONDITION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_POLECONDITION_ID + " text, "
                    + SURVEY_POLECONDITION_NAME + " text  ) ; ";

    //........ Survey Ploe  Location table.......................//
    private static final String CREATE_SURVEY_POLELOCATION_TABLE =
            "create table " + SURVEY_POLELOCATION_TABLE + " ("
                    + SURVEY_POLELOCATION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_POLELOCATION_ID + " text, "
                    + SURVEY_POLELOCATION_NAME + " text  ) ; ";

    //........ Survey iNSULATOR tYPE table.......................//
    private static final String CREATE_SURVEY_INSULATORTYPE_TABLE =
            "create table " + SURVEY_INSULATORTYPE_TABLE + " ("
                    + SURVEY_INSULATORTYPE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_INSULATORTYPE_ID + " text, "
                    + SURVEY_INSULATORTYPE_NAME + " text  ) ; ";

    //........ Survey iNSULATOR condition table.......................//
    private static final String CREATE_SURVEY_INSULATORCONDITION_TABLE =
            "create table " + SURVEY_INSULATORCONDITION_TABLE + " ("
                    + SURVEY_INSULATORCONDITION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_INSULATORCONDITION_ID + " text, "
                    + SURVEY_INSULATORCONDITION_NAME + " text  ) ; ";


    //........ Survey stay tYPE table.......................//
    private static final String CREATE_SURVEY_STAYTYPE_TABLE =
            "create table " + SURVEY_STAYTYPE_TABLE + " ("
                    + SURVEY_STAYTYPE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_STAYTYPE_ID + " text, "
                    + SURVEY_STAYTYPE_NAME + " text  ) ; ";

    //........ Survey stay condition table.......................//
    private static final String CREATE_SURVEY_STAYCONDITION_TABLE =
            "create table " + SURVEY_STAYCONDITION_TABLE + " ("
                    + SURVEY_STAYCONDITION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_STAYCONDITION_ID + " text, "
                    + SURVEY_STAYCONDITION_NAME + " text  ) ; ";

    //........ Survey Guarding tYPE table.......................//
    private static final String CREATE_SURVEY_GUARDINGTYPE_TABLE =
            "create table " + SURVEY_GUARDINGTYPE_TABLE + " ("
                    + SURVEY_GUARDINGTYPE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_GUARDINGTYPE_ID + " text, "
                    + SURVEY_GUARDINGTYPE_NAME + " text  ) ; ";

    //........ Survey Guarding condition table.......................//
    private static final String CREATE_SURVEY_GUARDINGCONDITION_TABLE =
            "create table " + SURVEY_GUARDINGCONDITION_TABLE + " ("
                    + SURVEY_GUARDINGCONDITION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_GUARDINGCONDITION_ID + " text, "
                    + SURVEY_GUARDINGCONDITION_NAME + " text  ) ; ";

    //........ Survey Earthing tYPE table.......................//
    private static final String CREATE_SURVEY_EARTHINGTYPE_TABLE =
            "create table " + SURVEY_EARTHINGTYPE_TABLE + " ("
                    + SURVEY_EARTHINGTYPE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_EARTHINGTYPE_ID + " text, "
                    + SURVEY_EARTHINGTYPE_NAME + " text  ) ; ";

    //........ Survey Earthing condition table.......................//
    private static final String CREATE_SURVEY_EARTHINGCONDITION_TABLE =
            "create table " + SURVEY_EARTHINGCONDITION_TABLE + " ("
                    + SURVEY_EARTHINGCONDITION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_EARTHINGCONDITION_ID + " text, "
                    + SURVEY_EARTHINGCONDITION_NAME + " text  ) ; ";

    //........ Survey Crossing table.......................//
    private static final String CREATE_SURVEY_CROSSING_TABLE =
            "create table " + SURVEY_CROSSING_TABLE + " ("
                    + SURVEY_CROSSING_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_CROSSING_ID + " text, "
                    + SURVEY_CROSSING_NAME + " text  ) ; ";



    //........ Survey Conductor tYPE table.......................//
    private static final String CREATE_SURVEY_CONDUCTORTYPE_TABLE =
            "create table " + SURVEY_CONDUCTORTYPE_TABLE + " ("
                    + SURVEY_CONDUCTORTYPE_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_CONDUCTORTYPE_ID + " text, "
                    + SURVEY_CONDUCTORTYPE_NAME + " text  ) ; ";

    //........ Survey Conductor condition table.......................//
    private static final String CREATE_SURVEY_CONDUCTORCONDITION_TABLE =
            "create table " + SURVEY_CONDUCTORCONDITION_TABLE + " ("
                    + SURVEY_CONDUCTORCONDITION_TABLE_ID + " integer primary key autoincrement, "
                    + SURVEY_CONDUCTORCONDITION_ID + " text, "
                    + SURVEY_CONDUCTORCONDITION_NAME + " text  ) ; ";


    //........ master dropdown table name table.......................//
    private static final String CREATE_MASTER_DROPDOWN_TABLE =
            "create table " + MASTER_DROPDOWN_TABLE + " ("
                    + MASTER_DROPDOWN_TABLE_ID + " integer primary key autoincrement, "
                    + MASTER_DROPDOWN_LEVEL1 + " text, "
                    + MASTER_DROPDOWN_LEVEL2 + " text, "
                    + MASTER_DROPDOWN_LEVEL3 + " text, "
                    + MASTER_DROPDOWN_LEVEL4 + " text  ) ; ";


    //........ Material Issued  table.......................//
    private static final String CREATE_MATERIAL_ISSUED_TABLE =
            "create table " + MATERIAL_ISSUED_TABLE + " ("
                    + MATERIAL_ISSUED_TABLE_ID + " integer primary key autoincrement, "
                    + MATERIAL_ISSUED_TKC_ID + " text, "
                    + MATERIAL_ISSUED_NAME + " text, "
                    + SCHEME_ID + " text  ) ; ";


    //........ WORKpROGRESS SCOPE table.......................//
    private static final String CREATE_WORKPROGRESS_SCOPE_TABLE =
            "create table " + WORKPROGRESS_SCOPE_TABLE + " ("
                    + WORKPROGRESS_SCOPE_TABLE_ID + " integer primary key autoincrement, "
                    + WORKPROGRESS_SCOPE_ID + " text, "
                    + WORKPROGRESS_SCOPE_NAME + " text  ) ; ";


    //........ NCR TABLE.......................//
    private static final String CREATE_NCR_TABLE =
            "create table " + NCR_TABLE + " ("
                    + NCR_TABLE_ID + " integer primary key autoincrement, "
                    + NCR_INSPECTION_ID + " text, "
                    + NCR_DISTRICT + " text, "
                    + NCR_BLOCK + " text, "
                    + NCR_VILLAGE + " text, "
                    + NCR_REMARK + " text, "
                    + NCR_MOBILE_DATE + " text, "
                    + NCR_NAME + " text, "
                    + NCR_DISTRICTID + " text, "
                    + NCR_SCHEME_ID + " text, "
                    + NCR_ITEM_NAME + " text, "
                    + NCR_TOLA + " text, "
                    + NCR_ITEM_ID + " text   ) ; ";


    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SQLiteAdapter1(Context c)
    {
        context = c;

    }

    public SQLiteAdapter1 openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }
    public SQLiteAdapter1 openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        sqLiteHelper.close();
    }

    //#########################################  select ####################################################//
    public Cursor select_project_all(){

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+PROJECT_TABLE,null);
        // Log.e("Select district", ":" + cursor);

        return cursor;
    }


    public Cursor select_geographic_all(){
        // Log.e("Select id",":"+id);
        //String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+GEOGRAPHIC_TABLE,null);

        return cursor;
    }

    public Cursor select_mgeographic_all(){
        // Log.e("Select id",":"+id);
        //String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MGEOGRAPHIC_TABLE,null);

        return cursor;
    }

    public Cursor select_geographic_id_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+GEOGRAPHIC_TABLE+" where geographic_project_code = "+"'"+id1+"'",null);

        return cursor;
    }


    public Cursor select_subarea_one_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREAONE_TABLE+" where subareaone_geographic_code = "+"'"+id1+"'",null);

        return cursor;
    }

    public Cursor select_subarea_one_all_allocated(){
        // Log.e("Select id",":"+id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREAONE_TABLE,null);

        return cursor;
    }

    public Cursor select_msubarea_one_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MSUBAREAONE_TABLE+" where msubareaone_geographic_code = "+"'"+id1+"'",null);

        return cursor;
    }


    public Cursor select_msubarea_one_all_store(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MSUBAREAONE_TABLE+" where msubareaone_code = "+"'"+id1+"'",null);

        return cursor;
    }



    public Cursor select_subarea_two_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREATWO_TABLE+" where subareatwo_one_code = "+"'"+id1+"'",null);

        return cursor;
    }


    public Cursor select_subarea_three_all(int id){
        // Log.e("Select id",":"+id);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SUBAREATHREE_TABLE+" where subareathree_two_code = "+"'"+id1+"'",null);

        return cursor;
    }

    public Cursor select_inspection_reinspection_all(int id){
      //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INSPECTION_REINSPECTION_TABLE,null);
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INSPECTION_REINSPECTION_TABLE+" where inspection_reinspection_three_code = "+"'"+id1+"'",null);
        return cursor;
    }


    public Cursor select_tkc_all(int id,int jid){
        String id1= Integer.toString(id);
        String id2= Integer.toString(jid);
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+TKC_TABLE+" where tkc_project_id = "+"'"+id1+"' and tkc_id  = "+"'"+id2+"'",null);
       Cursor cursor=sqLiteDatabase.rawQuery("select * from TKC_TABLE where tkc_project_id="+id1+"" + " and tkc_name = "+"'"+id2+"'" , null);
        Log.e("content value scope ", "" + cursor);
        return cursor;
    }

    public Cursor select_tkc_all_limit(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+TKC_TABLE+" LIMIT "+"'"+id1+"'", null);
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+TKC_TABLE,null);
        return cursor;
    }

    public Cursor select_tkc_store_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+TKC_TABLE+" where store_id = "+"'"+id1+"'",null);

        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+TKC_TABLE,null);
        return cursor;
    }

    public Cursor select_materialtkc_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+TKC_TABLE+" where tkc_id = "+"'"+id1+"'",null);

        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+TKC_TABLE,null);
        return cursor;
    }

    public Cursor select_item_all(){
         Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+ITEM_TABLE,null);
        return cursor;
    }


    public Cursor select_in_out_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_FEEDER_TABLE,null);
        return cursor;
    }

    public Cursor select_gene_ccr_jun_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_DTRRATING_NAME,null);
        return cursor;
    }


    public Cursor select_choose_item_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+CHOOSE_ITEM_TABLE+" where choose_item_mapping_code = "+"'"+id1+"'",null);

       // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+CHOOSE_ITEM_TABLE,null);
        return cursor;
    }

    public Cursor select_choose_item_activity_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+CHOOSE_ITEM_TABLE+" where choose_item_activity_code = "+"'"+id1+"'",null);

        // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+CHOOSE_ITEM_TABLE,null);
        return cursor;
    }

    public Cursor select_choose_check_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+CHOOSE_ITEM_TABLE ,null);
        return cursor;
    }

    public Cursor select_supplier_all(){
        //String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_ID_TABLE ,null);
        return cursor;
    }


    public Cursor select_specsno_itemid_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_ID_TABLE+" where SURVEY_ITEM_ID = "+"'"+id1+"'",null);
        return cursor;
    }

    public Cursor select_survey_id_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_ID_TABLE+" where SURVEY_ID_PROJECT_CODE = "+"'"+id1+"'",null);
        return cursor;
    }

    public Cursor select_asset_type_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+ASSET_TYPE_TABLE,null);
        return cursor;
    }

    /*public Cursor select_inspection_subitem_all(){

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INSPECTION_SUBITEM_TABLE,null);
        return cursor;
    }*/

     public Cursor select_inspection_subitem_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INSPECTION_SUBITEM_TABLE+" where mainitem_id  = "+"'"+id1+"'",null);

        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INSPECTION_SUBITEM_TABLE,null);
        return cursor;
     }

    public Cursor select_inspection_asset_all(){
       // String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INSPECTION_SUBITEM_TABLE,null);

        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+INSPECTION_SUBITEM_TABLE,null);
        return cursor;
    }


    public Cursor select_surveyfeeder_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_FEEDER_TABLE+" where survey_feeder_substation_code = "+"'"+id1+"'",null);
      //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_FEEDER_TABLE,null);
        return cursor;
    }

    public Cursor select_surveydtrrating_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_DTRRATING_TABLE,null);
        return cursor;
    }

    public Cursor select_surveydtrmouting_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_DTRMOUTING_TABLE,null);
        return cursor;
    }

    public Cursor select_surveysubstationvoltage_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_SUBSTATIONVOLTAGE_TABLE,null);
        return cursor;
    }

    public Cursor select_surveyinfeeder_all(){
        //String id1= Integer.toString(id);
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_INFEEDER_TABLE+" where survey_substation_infeeder_id = "+"'"+id1+"'",null);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_INFEEDER_TABLE,null);
        return cursor;
    }

    public Cursor select_material_subitem_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_INFEEDER_TABLE+" where survey_substation_infeeder_id = "+"'"+id1+"'",null);
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_INFEEDER_TABLE,null);
        return cursor;
    }

    public Cursor select_surveyoutfeeder_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_OUTFEEDER_TABLE,null);
        return cursor;
    }

    public Cursor select_surveyptcapacity_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_PTCAPACITY_TABLE,null);
        return cursor;
    }

    public Cursor select_surveyswitchgear_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_SWITCHGEAR_TABLE,null);
        return cursor;
    }

    public Cursor select_surveylocation_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_LOCATION_TABLE,null);
        return cursor;
    }

    public Cursor select_surveylength_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_LENGTH_TABLE,null);
        return cursor;
    }

    public Cursor select_surveyphase_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_PHASE_TABLE,null);
        return cursor;
    }

    public Cursor select_surveyconsumertype_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_CONSUMERTYPE_TABLE,null);
        return cursor;
    }

    public Cursor select_surveycategory_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_CATEGORY_TABLE,null);
        return cursor;
    }

    public Cursor select_surveypoletype_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_POLETYPE_TABLE,null);
        return cursor;
    }
    public Cursor select_surveypolecondition_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_POLECONDITION_TABLE,null);
        return cursor;
    }

    public Cursor select_surveypolelocation_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_POLELOCATION_TABLE,null);
        return cursor;
    }

    public Cursor select_surveyinsulatortype_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_INSULATORTYPE_TABLE,null);
        return cursor;
    }
    public Cursor select_surveyinsulatorcondition_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_INSULATORCONDITION_TABLE,null);
        return cursor;
    }

    public Cursor select_surveystaytype_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_STAYTYPE_TABLE,null);
        return cursor;
    }
    public Cursor select_surveystaycondition_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_STAYCONDITION_TABLE,null);
        return cursor;
    }

    public Cursor select_guardingtype_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_GUARDINGTYPE_TABLE,null);
        return cursor;
    }
    public Cursor select_surveyguardingcondition_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_GUARDINGCONDITION_TABLE,null);
        return cursor;
    }

    public Cursor select_earthingtype_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_EARTHINGTYPE_TABLE,null);
        return cursor;
    }
    public Cursor select_surveyearthingcondition_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_EARTHINGCONDITION_TABLE,null);
        return cursor;
    }


    public Cursor select_conductortype_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_CONDUCTORTYPE_TABLE,null);
        return cursor;
    }
    public Cursor select_surveyconductorcondition_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_CONDUCTORCONDITION_TABLE,null);
        return cursor;
    }

    public Cursor select_surveycrossing_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+SURVEY_CROSSING_TABLE,null);
        return cursor;
    }

    public Cursor select_workprogressscope_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+WORKPROGRESS_SCOPE_TABLE,null);
        return cursor;
    }

    public Cursor select_ncr_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+NCR_TABLE,null);
        return cursor;
    }

      public Cursor select_ncr_byid(String id){
        //String id1= Integer.toString(id);
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+NCR_TABLE+" where ncr_inspection_id = "+"'"+id+"'",null);
          //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+NCR_TABLE+" where ncr_village like '%"+id+"%'",null);
          //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+NCR_TABLE+" where ncr_village like '"+id+"%'",null);
         // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+NCR_TABLE+" where ncr_inspection_id like '"+id+"%'",null);
          //String id1= Integer.toString(id);
          Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+NCR_TABLE+" where ncr_inspection_id = "+"'"+id+"'",null);

          return cursor;
    }


    public Cursor select_masterdropdown_all(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MASTER_DROPDOWN_TABLE,null);
        return cursor;
    }

    public Cursor select_material_issued_all(){

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MATERIAL_ISSUED_TABLE,null);
        //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MATERIAL_ISSUED_TABLE,null);
        return cursor;
    }

    public Cursor select_material_issued_id_all(int id){
        String id1= Integer.toString(id);
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MATERIAL_ISSUED_TABLE+" where scheme_id = "+"'"+id1+"'",null);
      //Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+MATERIAL_ISSUED_TABLE,null);
        return cursor;
    }

    //............ insert project..............//
    public long insert_project(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(PROJECT_ID, content0);
        contentValues.put(PROJECT_CODE, content1);
        contentValues.put(PROJECT_NAME, content2);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(PROJECT_TABLE, null, contentValues);
    }

    public int delete_project_all(){
        return sqLiteDatabase.delete(PROJECT_TABLE, null, null);
    }

    public void delete_project_byID(int id){
        sqLiteDatabase.delete(PROJECT_TABLE, PROJECT_ID+"="+id, null);
    }


    //............ insert geographic..............//
    public long insert_geographic(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(GEOGRAPHIC_PROJECT_CODE, content0);
        contentValues.put(GEOGRAPHIC_CODE, content1);
        contentValues.put(GEOGRAPHIC_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(GEOGRAPHIC_TABLE, null, contentValues);
    }

    public int delete_geographic_one_All(){
        return sqLiteDatabase.delete(GEOGRAPHIC_TABLE, null, null);
    }

    public void delete_geographic_one_byID(int id){
        sqLiteDatabase.delete(GEOGRAPHIC_TABLE, GEOGRAPHIC_TABLE_ID+"="+id, null);
    }


    //............ insert geographic..............//
    public long insert_mgeographic(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(MGEOGRAPHIC_PROJECT_CODE, content0);
        contentValues.put(MGEOGRAPHIC_CODE, content1);
        contentValues.put(MGEOGRAPHIC_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(MGEOGRAPHIC_TABLE, null, contentValues);
    }

    public int delete_mgeographic_one_All(){
        return sqLiteDatabase.delete(MGEOGRAPHIC_TABLE, null, null);
    }

    public void delete_mgeographic_one_byID(int id){
        sqLiteDatabase.delete(MGEOGRAPHIC_TABLE, MGEOGRAPHIC_TABLE_ID+"="+id, null);
    }



    //............ insert SUB AREA ONE..............//
    public long insert_subarea_one(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBAREAONE_GEOGRAPHIC_CODE, content0);
        contentValues.put(SUBAREAONE_CODE, content1);
        contentValues.put(SUBAREAONE_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(SUBAREAONE_TABLE, null, contentValues);
    }

    public int delete_subarea_one_All(){
        return sqLiteDatabase.delete(SUBAREAONE_TABLE, null, null);
    }

    public void delete_subarea_one_byID(int id){
        sqLiteDatabase.delete(SUBAREAONE_TABLE, SUBAREAONE_TABLE_ID+"="+id, null);
    }


    //............ insert SUB AREA ONE..............//
    public long insert_msubarea_one(String content0, String content1, String content2, String content3, String content4){

        ContentValues contentValues = new ContentValues();
        contentValues.put(MSUBAREAONE_GEOGRAPHIC_CODE, content0);
        contentValues.put(MSUBAREAONE_CODE, content1);
        contentValues.put(MSUBAREAONE_NAME, content2);
        contentValues.put(MSTORE_ID, content3);
        contentValues.put(MSTORE_NAME, content4);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(MSUBAREAONE_TABLE, null, contentValues);
    }

    public int delete_msubarea_one_All(){
        return sqLiteDatabase.delete(MSUBAREAONE_TABLE, null, null);
    }

    public void delete_msubarea_one_byID(int id){
        sqLiteDatabase.delete(MSUBAREAONE_TABLE, MSUBAREAONE_TABLE_ID+"="+id, null);
    }


    //............ insert SUB AREA TWO..............//
    public long insert_subarea_two(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBAREATWO_ONE_CODE, content0);
        contentValues.put(SUBAREATWO_CODE, content1);
        contentValues.put(SUBAREATWO_NAME, content2);

        //  Log.e("content value block", "" + contentValues);

        return sqLiteDatabase.insert(SUBAREATWO_TABLE, null, contentValues);
    }

    public int delete_subarea_two_All(){
        return sqLiteDatabase.delete(SUBAREATWO_TABLE, null, null);
    }

    public void delete_subarea_two_byID(int id){
        sqLiteDatabase.delete(SUBAREATWO_TABLE, SUBAREATWO_TABLE_ID+"="+id, null);
    }



    //............ insert SUB AREA Three..............//
    public long insert_subarea_three(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBAREATHREE_TWO_CODE, content0);
        contentValues.put(SUBAREATHREE_CODE, content1);
        contentValues.put(SUBAREATHREE_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(SUBAREATHREE_TABLE, null, contentValues);
    }

    public int delete_subarea_three_All(){
        return sqLiteDatabase.delete(SUBAREATHREE_TABLE, null, null);
    }

    public void delete_subarea_three_byID(int id){
        sqLiteDatabase.delete(SUBAREATHREE_TABLE, SUBAREATHREE_TABLE_ID+"="+id, null);
    }




    //............ insert inspection reinspection ..............//
    public long insert_inspection_reinspection(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(INSPECTION_REINSPECTION_THREE_CODE, content0);
        contentValues.put(INSPECTION_REINSPECTION_CODE, content1);
        contentValues.put(INSPECTION_REINSPECTION_NAME, content2);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(INSPECTION_REINSPECTION_TABLE, null, contentValues);
    }

    public int delete_inspection_reinspection_All(){
        return sqLiteDatabase.delete(INSPECTION_REINSPECTION_TABLE, null, null);
    }

    public void delete_inspection_reinspection_byID(int id){
        sqLiteDatabase.delete(INSPECTION_REINSPECTION_TABLE, INSPECTION_REINSPECTION_TABLE_ID+"="+id, null);
    }



    //............ insert TKC ..............//
    public long insert_tkc(String content0, String content1, String content2, String content3){

        ContentValues contentValues = new ContentValues();
        contentValues.put(TKC_ID, content0);
        contentValues.put(TKC_NAME, content1);
        contentValues.put(TKC_PROJECT_ID, content2);
        contentValues.put(STORE_ID, content3);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(TKC_TABLE, null, contentValues);
    }

    public int delete_tkc_all(){
        return sqLiteDatabase.delete(TKC_TABLE, null, null);
    }

    public void delete_tkc_byID(int id){
        sqLiteDatabase.delete(TKC_TABLE, TKC_ID+"="+id, null);
    }



    //............ insert ITEM ..............//
    public long insert_item(String content0, String content1, String content2){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_ID, content0);
        contentValues.put(ITEM_NAME, content1);
        contentValues.put(ITEM_SPECS_NO, content2);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(ITEM_TABLE, null, contentValues);
    }

    public int delete_item_all(){
        return sqLiteDatabase.delete(ITEM_TABLE, null, null);
    }

    public void delete_item_byID(int id){
        sqLiteDatabase.delete(ITEM_TABLE, ITEM_ID+"="+id, null);
    }



    //............ insert Choose item table ..............//
    public long insert_choose_item(String content0, String content1, String content2, String content3){

        ContentValues contentValues = new ContentValues();

        contentValues.put(CHOOSE_ITEM_CODE, content0);
        contentValues.put(CHOOSE_ITEM_NAME, content1);
        contentValues.put(CHOOSE_ITEM_MAPPING_CODE, content2);
        contentValues.put(CHOOSE_ITEM_ACTIVITY_CODE, content3);
        //  Log.e("content value block", "" + contentValues);
        return sqLiteDatabase.insert(CHOOSE_ITEM_TABLE, null, contentValues);
    }

    public int delete_choose_item_All(){
        return sqLiteDatabase.delete(CHOOSE_ITEM_TABLE, null, null);
    }

    public void delete_chosse_item_byID(int id){
        sqLiteDatabase.delete(CHOOSE_ITEM_TABLE, CHOOSE_ITEM_TABLE_ID+"="+id, null);
    }


    //............ insert ITEM ..............//
    public long insert_survey_id(String content0, String content1, String content2, String content3, String content4, String content5){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_ID, content0);
        contentValues.put(SURVEY_ID_PROJECT_CODE, content1);
        contentValues.put(SURVEY_NAME_ID, content2);
        contentValues.put(SURVEY_ITEM_ID, content3);
        contentValues.put(SURVEY_SPEC_NO, content4);
        contentValues.put(SURVEY_MOBILE_STATUS, content5);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_ID_TABLE, null, contentValues);
    }

    public int delete_survey_id_all(){
        return sqLiteDatabase.delete(SURVEY_ID_TABLE, null, null);
    }

    public void delete_survey_id_byID(int id){
        sqLiteDatabase.delete(SURVEY_ID_TABLE, SURVEY_ID_TABLE_ID+"="+id, null);
    }


    //............ insert Asset Type ..............//
    public long insert_asset_type(String content0){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ASSET_NAME, content0);

        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(ASSET_TYPE_TABLE, null, contentValues);
    }

    public int delete_asset_type_all(){
        return sqLiteDatabase.delete(ASSET_TYPE_TABLE, null, null);
    }

    public void delete_asset_type_byID(int id){
        sqLiteDatabase.delete(ASSET_TYPE_TABLE, ASSET_TABLE_ID+"="+id, null);
    }

    //............ insert SUBSTATION ..............//
    public long insert_inspection_subitem(String content0, String content1, String content2, String content3, String content4){

        ContentValues contentValues = new ContentValues();
        contentValues.put(INSPECTION_SUBITEM_ID, content0);
        contentValues.put(INSPECTION_SUBITEM_NAME, content1);
        contentValues.put(INSPECTION_MAINITEM_ID, content2);
        contentValues.put(INSPECTION_UNITS_ID, content3);
        contentValues.put(INSPECTION_CHECKLIST_ID, content4);

        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(INSPECTION_SUBITEM_TABLE, null, contentValues);
    }

    public int delete_inspection_subitem_all(){
        return sqLiteDatabase.delete(INSPECTION_SUBITEM_TABLE, null, null);
    }

    public void delete_inspection_subitem_byID(int id){
        sqLiteDatabase.delete(INSPECTION_SUBITEM_TABLE, INSPECTION_SUBITEM_TABLE_ID+"="+id, null);
    }


    //............ insert Feeder ..............//
    public long insert_internal_external(String content0, String content1){

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_FEEDER_SUBSTATION_CODE, content0);
        contentValues.put(SURVEY_FEEDER_NAME, content1);

        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_FEEDER_TABLE, null, contentValues);
    }

    public int delete_survey_feeder_all(){
        return sqLiteDatabase.delete(SURVEY_FEEDER_TABLE, null, null);
    }

    public void delete_survey_feeder_byID(int id){
        sqLiteDatabase.delete(SURVEY_FEEDER_TABLE, SURVEY_FEEDER_TABLE_ID+"="+id, null);
    }

    //............ insert DTR RATING ..............//


    public int delete_survey_dtrrating_all(){
        return sqLiteDatabase.delete(SURVEY_DTRRATING_TABLE, null, null);
    }

    public void delete_survey_dtrrating_byID(int id){
        sqLiteDatabase.delete(SURVEY_DTRRATING_TABLE, SURVEY_DTRRATING_TABLE_ID+"="+id, null);
    }

    //............ insert DTR MOUTING ..............//
    public long insert_survey_dtrmouting(String content0) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_DTRMOUTING_NAME, content0);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_DTRMOUTING_TABLE, null, contentValues);
    }

    public int delete_survey_dtrmouting_all(){
        return sqLiteDatabase.delete(SURVEY_DTRMOUTING_TABLE, null, null);
    }

    public void delete_survey_dtrmouting_byID(int id){
        sqLiteDatabase.delete(SURVEY_DTRMOUTING_TABLE, SURVEY_DTRMOUTING_TABLE_ID+"="+id, null);
    }


    //............ insert Substation Vlotage ..............//
    public long insert_survey_substationvoltage(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_SVOLATGE_ID, content0);
        contentValues.put(SURVEY_SVOLATGE_NAME, content1);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_SUBSTATIONVOLTAGE_TABLE, null, contentValues);
    }



    public int delete_survey_substationvoltage_all(){
        return sqLiteDatabase.delete(SURVEY_SUBSTATIONVOLTAGE_TABLE, null, null);
    }

    public void delete_survey_substationvoltage_byID(int id){
        sqLiteDatabase.delete(SURVEY_SUBSTATIONVOLTAGE_TABLE, SURVEY_SVOLTAGE_TABLE_ID+"="+id, null);
    }


    //............ insert Infeeder Name ..............//
    public long insert_survey_infeeder(String content0, String content1, String content2) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_INFEEDER_ID, content0);
        contentValues.put(SURVEY_SUBSTATION_INFEEDER_ID, content1);
        contentValues.put(SURVEY_INFEEDER_NAME, content2);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_INFEEDER_TABLE, null, contentValues);
    }

    public int delete_survey_infeeder_all(){
        return sqLiteDatabase.delete(SURVEY_INFEEDER_TABLE, null, null);
    }

    public void delete_survey_infeeder_byID(int id){
        sqLiteDatabase.delete(SURVEY_INFEEDER_TABLE, SURVEY_INFEEDER_TABLE_ID+"="+id, null);
    }


    //............ insert OUTfeeder Name ..............//
    public long insert_survey_outfeeder(String content0, String content1, String content2) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_OUTFEEDER_ID, content0);
        contentValues.put(SURVEY_SUBSTATION_OUTFEEDER_ID, content1);
        contentValues.put(SURVEY_OUTFEEDER_NAME, content2);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_OUTFEEDER_TABLE, null, contentValues);
    }

    public int delete_survey_outfeeder_all(){
        return sqLiteDatabase.delete(SURVEY_OUTFEEDER_TABLE, null, null);
    }

    public void delete_survey_outfeeder_byID(int id){
        sqLiteDatabase.delete(SURVEY_OUTFEEDER_TABLE, SURVEY_OUTFEEDER_TABLE_ID+"="+id, null);
    }


    //............ insert PT CApacity Name ..............//
    public long insert_survey_ptcapacity(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_PTCAPACITY_ID, content0);
        contentValues.put(SURVEY_PTCAPACITY_NAME, content1);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_PTCAPACITY_TABLE, null, contentValues);
    }

    public int delete_survey_ptcapacity_all(){
        return sqLiteDatabase.delete(SURVEY_PTCAPACITY_TABLE, null, null);
    }

    public void delete_survey_ptcapacity_byID(int id){
        sqLiteDatabase.delete(SURVEY_PTCAPACITY_TABLE, SURVEY_PTCAPACITY_TABLE_ID+"="+id, null);
    }

    //............ insert SwitchGear Name ..............//
    public long insert_survey_switchgear(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_SWITCHGEAR_ID, content0);
        contentValues.put(SURVEY_SWITCHGEAR_NAME, content1);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_SWITCHGEAR_TABLE, null, contentValues);
    }

    public int delete_survey_switchgear_all(){
        return sqLiteDatabase.delete(SURVEY_SWITCHGEAR_TABLE, null, null);
    }

    public void delete_survey_switchgear_byID(int id){
        sqLiteDatabase.delete(SURVEY_SWITCHGEAR_TABLE, SURVEY_SWITCHGEAR_TABLE_ID+"="+id, null);
    }

    //............ insert Location Name ..............//
    public long insert_survey_location(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_LOCATION_ID, content0);
        contentValues.put(SURVEY_LOCATION_NAME, content1);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_LOCATION_TABLE, null, contentValues);
    }

    public int delete_survey_location_all(){
        return sqLiteDatabase.delete(SURVEY_LOCATION_TABLE, null, null);
    }

    public void delete_survey_location_byID(int id){
        sqLiteDatabase.delete(SURVEY_LOCATION_TABLE, SURVEY_LOCATION_TABLE_ID+"="+id, null);
    }


    //............ insert Cable Length Name ..............//
    public long insert_survey_length(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_LENGTH_ID, content0);
        contentValues.put(SURVEY_LENGTH_NAME, content1);


        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_LENGTH_TABLE, null, contentValues);
    }

    public int delete_survey_length_all(){
        return sqLiteDatabase.delete(SURVEY_LENGTH_TABLE, null, null);
    }

    public void delete_survey_length_byID(int id){
        sqLiteDatabase.delete(SURVEY_LENGTH_TABLE, SURVEY_LENGTH_TABLE_ID+"="+id, null);
    }


    //............ insert Phase Name ..............//
    public long insert_survey_phase(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_PHASE_ID, content0);
        contentValues.put(SURVEY_PHASE_NAME, content1);
      // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_PHASE_TABLE, null, contentValues);
    }

    public int delete_survey_phase_all(){
        return sqLiteDatabase.delete(SURVEY_PHASE_TABLE, null, null);
    }

    public void delete_survey_phase_byID(int id){
        sqLiteDatabase.delete(SURVEY_PHASE_TABLE, SURVEY_PHASE_TABLE_ID+"="+id, null);
    }


    //............ insert Consumer Type Name ..............//
    public long insert_survey_consumertype(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_CONSUMERTYPE_ID, content0);
        contentValues.put(SURVEY_CONSUMERTYPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_CONSUMERTYPE_TABLE, null, contentValues);
    }

    public int delete_survey_consumertype_all(){
        return sqLiteDatabase.delete(SURVEY_CONSUMERTYPE_TABLE, null, null);
    }

    public void delete_survey_consumertype_byID(int id){
        sqLiteDatabase.delete(SURVEY_CONSUMERTYPE_TABLE, SURVEY_CONSUMERTYPE_TABLE_ID+"="+id, null);
    }


    //............ insert Category Type Name ..............//
    public long insert_survey_category(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_CATEGORY_ID, content0);
        contentValues.put(SURVEY_CATEGORY_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_CATEGORY_TABLE, null, contentValues);
    }

    public int delete_survey_category_all(){
        return sqLiteDatabase.delete(SURVEY_CATEGORY_TABLE, null, null);
    }

    public void delete_survey_category_byID(int id){
        sqLiteDatabase.delete(SURVEY_CATEGORY_TABLE, SURVEY_CATEGORY_TABLE_ID+"="+id, null);
    }

    //............ insert POLE TYPE Name ..............//
    public long insert_survey_poletype(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_POLETYPE_ID, content0);
        contentValues.put(SURVEY_POLETYPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_POLETYPE_TABLE, null, contentValues);
    }

    public int delete_survey_poletype_all(){
        return sqLiteDatabase.delete(SURVEY_POLETYPE_TABLE, null, null);
    }

    public void delete_survey_poletype_byID(int id){
        sqLiteDatabase.delete(SURVEY_POLETYPE_TABLE, SURVEY_POLETYPE_TABLE_ID+"="+id, null);
    }

    //............ insert POLE CONDITION Name ..............//
    public long insert_survey_poletcondition(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_POLECONDITION_ID, content0);
        contentValues.put(SURVEY_POLECONDITION_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_POLECONDITION_TABLE, null, contentValues);
    }

    public int delete_survey_polecondition_all(){
        return sqLiteDatabase.delete(SURVEY_POLECONDITION_TABLE, null, null);
    }

    public void delete_survey_polecondition_byID(int id){
        sqLiteDatabase.delete(SURVEY_POLECONDITION_TABLE, SURVEY_POLECONDITION_TABLE_ID+"="+id, null);
    }

    //............ insert POLE LOCATION Name ..............//
    public long insert_survey_poletlocation(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_POLELOCATION_ID, content0);
        contentValues.put(SURVEY_POLELOCATION_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_POLELOCATION_TABLE, null, contentValues);
    }

    public int delete_survey_polelocation_all(){
        return sqLiteDatabase.delete(SURVEY_POLELOCATION_TABLE, null, null);
    }

    public void delete_survey_polelocation_byID(int id){
        sqLiteDatabase.delete(SURVEY_POLELOCATION_TABLE, SURVEY_POLELOCATION_TABLE_ID+"="+id, null);
    }

    //............ insert INSULATOR TYPE Name ..............//
    public long insert_survey_insulatortype(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_INSULATORTYPE_ID, content0);
        contentValues.put(SURVEY_INSULATORTYPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_INSULATORTYPE_TABLE, null, contentValues);
    }

    public int delete_survey_insulatortype_all(){
        return sqLiteDatabase.delete(SURVEY_INSULATORTYPE_TABLE, null, null);
    }

    public void delete_survey_insulatortype_byID(int id){
        sqLiteDatabase.delete(SURVEY_INSULATORTYPE_TABLE, SURVEY_INSULATORTYPE_TABLE_ID+"="+id, null);
    }


    //............ insert INSULATOR Condition Name ..............//
    public long insert_survey_insulatorcondition(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_INSULATORCONDITION_ID, content0);
        contentValues.put(SURVEY_INSULATORCONDITION_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_INSULATORCONDITION_TABLE, null, contentValues);
    }

    public int delete_survey_insulatorcondition_all(){
        return sqLiteDatabase.delete(SURVEY_INSULATORCONDITION_TABLE, null, null);
    }

    public void delete_survey_insulatorcondition_byID(int id){
        sqLiteDatabase.delete(SURVEY_INSULATORCONDITION_TABLE, SURVEY_INSULATORCONDITION_TABLE_ID+"="+id, null);
    }

    //............ insert STAY TYPE Name ..............//
    public long insert_survey_staytype(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_STAYTYPE_ID, content0);
        contentValues.put(SURVEY_STAYTYPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_STAYTYPE_TABLE, null, contentValues);
    }

    public int delete_survey_staytype_all(){
        return sqLiteDatabase.delete(SURVEY_STAYTYPE_TABLE, null, null);
    }

    public void delete_survey_staytype_byID(int id){
        sqLiteDatabase.delete(SURVEY_STAYTYPE_TABLE, SURVEY_STAYTYPE_TABLE_ID+"="+id, null);
    }


    //............ insert Stay Condition Name ..............//
    public long insert_survey_staycondition(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_STAYCONDITION_ID, content0);
        contentValues.put(SURVEY_STAYCONDITION_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_STAYCONDITION_TABLE, null, contentValues);
    }

    public int delete_survey_staycondition_all(){
        return sqLiteDatabase.delete(SURVEY_STAYCONDITION_TABLE, null, null);
    }

    public void delete_survey_satycondition_byID(int id){
        sqLiteDatabase.delete(SURVEY_STAYCONDITION_TABLE, SURVEY_STAYCONDITION_TABLE_ID+"="+id, null);
    }


    //............ insert Guarding TYPE Name ..............//
    public long insert_survey_guardingtype(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_GUARDINGTYPE_ID, content0);
        contentValues.put(SURVEY_GUARDINGTYPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_GUARDINGTYPE_TABLE, null, contentValues);
    }

    public int delete_survey_guardingtype_all(){
        return sqLiteDatabase.delete(SURVEY_GUARDINGTYPE_TABLE, null, null);
    }

    public void delete_survey_guardingtype_byID(int id){
        sqLiteDatabase.delete(SURVEY_GUARDINGTYPE_TABLE, SURVEY_GUARDINGTYPE_TABLE_ID+"="+id, null);
    }


    //............ insert Stay Condition Name ..............//
    public long insert_survey_guardingcondition(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_GUARDINGCONDITION_ID, content0);
        contentValues.put(SURVEY_GUARDINGCONDITION_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_GUARDINGCONDITION_TABLE, null, contentValues);
    }

    public int delete_survey_guardingcondition_all(){
        return sqLiteDatabase.delete(SURVEY_GUARDINGCONDITION_TABLE, null, null);
    }

    public void delete_survey_guardingcondition_byID(int id){
        sqLiteDatabase.delete(SURVEY_GUARDINGCONDITION_TABLE, SURVEY_GUARDINGCONDITION_TABLE_ID+"="+id, null);
    }


    //............ insert Earthing TYPE Name ..............//
    public long insert_survey_earthingtype(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_EARTHINGTYPE_ID, content0);
        contentValues.put(SURVEY_EARTHINGTYPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_EARTHINGTYPE_TABLE, null, contentValues);
    }

    public int delete_survey_earthingtype_all(){
        return sqLiteDatabase.delete(SURVEY_EARTHINGTYPE_TABLE, null, null);
    }

    public void delete_survey_earthingtype_byID(int id){
        sqLiteDatabase.delete(SURVEY_EARTHINGTYPE_TABLE, SURVEY_EARTHINGTYPE_TABLE_ID+"="+id, null);
    }


    //............ insert earthing Condition Name ..............//
    public long insert_survey_earthingcondition(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_EARTHINGCONDITION_ID, content0);
        contentValues.put(SURVEY_EARTHINGCONDITION_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_EARTHINGCONDITION_TABLE, null, contentValues);
    }

    public int delete_survey_earthingcondition_all(){
        return sqLiteDatabase.delete(SURVEY_EARTHINGCONDITION_TABLE, null, null);
    }

    public void delete_survey_earthingcondition_byID(int id){
        sqLiteDatabase.delete(SURVEY_EARTHINGCONDITION_TABLE, SURVEY_EARTHINGCONDITION_TABLE_ID+"="+id, null);
    }

    //............ insert Crossing Name ..............//
    public long insert_survey_crossing(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_CROSSING_ID, content0);
        contentValues.put(SURVEY_CROSSING_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_CROSSING_TABLE, null, contentValues);
    }

    public int delete_survey_crossing_all(){
        return sqLiteDatabase.delete(SURVEY_CROSSING_TABLE, null, null);
    }

    public void delete_survey_crossing_byID(int id){
        sqLiteDatabase.delete(SURVEY_CROSSING_TABLE, SURVEY_CROSSING_TABLE_ID+"="+id, null);
    }

    //............ insert Conductor TYPE Name ..............//
    public long insert_survey_conductortype(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_CONDUCTORTYPE_ID, content0);
        contentValues.put(SURVEY_CONDUCTORTYPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_CONDUCTORTYPE_TABLE, null, contentValues);
    }

    public int delete_survey_conductortype_all(){
        return sqLiteDatabase.delete(SURVEY_CONDUCTORTYPE_TABLE, null, null);
    }

    public void delete_survey_conductortype_byID(int id){
        sqLiteDatabase.delete(SURVEY_CONDUCTORTYPE_TABLE, SURVEY_CONDUCTORTYPE_TABLE_ID+"="+id, null);
    }


    //............ insert conductor Condition Name ..............//
    public long insert_survey_conductorcondition(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_CONDUCTORCONDITION_ID, content0);
        contentValues.put(SURVEY_CONDUCTORCONDITION_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(SURVEY_CONDUCTORCONDITION_TABLE, null, contentValues);
    }

    public int delete_survey_conductorcondition_all(){
        return sqLiteDatabase.delete(SURVEY_CONDUCTORCONDITION_TABLE, null, null);
    }

    public void delete_survey_conductorcondition_byID(int id){
        sqLiteDatabase.delete(SURVEY_CONDUCTORCONDITION_TABLE, SURVEY_CONDUCTORCONDITION_TABLE_ID+"="+id, null);
    }




    //............ insert Master DropDown ..............//
    public long insert_masterdropdown(String content0, String content1, String content2, String content3) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MASTER_DROPDOWN_LEVEL1, content0);
        contentValues.put(MASTER_DROPDOWN_LEVEL2, content1);
        contentValues.put(MASTER_DROPDOWN_LEVEL3, content2);
        contentValues.put(MASTER_DROPDOWN_LEVEL4, content3);

        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(MASTER_DROPDOWN_TABLE, null, contentValues);
    }

    public int delete_masterdropdown_all(){
        return sqLiteDatabase.delete(MASTER_DROPDOWN_TABLE, null, null);
    }

    public void delete_masterdropdown_byID(int id){
        sqLiteDatabase.delete(MASTER_DROPDOWN_TABLE, MASTER_DROPDOWN_TABLE_ID+"="+id, null);
    }



    //............ insert Master Material ISSueD ..............//
    public long insert_material_issued(String content0, String content1, String content2) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MATERIAL_ISSUED_TKC_ID, content0);
        contentValues.put(MATERIAL_ISSUED_NAME, content1);
        contentValues.put(SCHEME_ID, content2);
      // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(MATERIAL_ISSUED_TABLE, null, contentValues);
    }

    public int delete_material_issued_all(){
        return sqLiteDatabase.delete(MATERIAL_ISSUED_TABLE, null, null);
    }

    public void delete_material_issued_byID(int id){
        sqLiteDatabase.delete(MATERIAL_ISSUED_TABLE, MATERIAL_ISSUED_TABLE_ID+"="+id, null);
    }

    //............ insert workprogress scope Name ..............//
    public long insert_workprogress_scope(String content0, String content1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(WORKPROGRESS_SCOPE_ID, content0);
        contentValues.put(WORKPROGRESS_SCOPE_NAME, content1);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(WORKPROGRESS_SCOPE_TABLE, null, contentValues);
    }

    public int delete_workprogress_scope_all(){
        return sqLiteDatabase.delete(WORKPROGRESS_SCOPE_TABLE, null, null);
    }

    public void delete_workprogress_scope_byID(int id){
        sqLiteDatabase.delete(WORKPROGRESS_SCOPE_TABLE, WORKPROGRESS_SCOPE_TABLE_ID+"="+id, null);
    }


    //............ insert NCR Name ..............//
    public long insert_ncr(String content0, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, String content10, String content11) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(NCR_INSPECTION_ID, content0);
        contentValues.put(NCR_DISTRICT, content1);
        contentValues.put(NCR_BLOCK, content2);
        contentValues.put(NCR_VILLAGE, content3);
        contentValues.put(NCR_REMARK, content4);
        contentValues.put(NCR_MOBILE_DATE, content5);
        contentValues.put(NCR_NAME, content6);
        contentValues.put(NCR_DISTRICTID, content7);
        contentValues.put(NCR_SCHEME_ID, content8);
        contentValues.put(NCR_ITEM_NAME, content9);
        contentValues.put(NCR_TOLA, content10);
        contentValues.put(NCR_ITEM_ID, content11);
        // Log.e("content value", "" + contentValues);
        return sqLiteDatabase.insert(NCR_TABLE, null, contentValues);
    }

    public int delete_ncr_all(){
        return sqLiteDatabase.delete(NCR_TABLE, null, null);
    }

    public void delete_ncr_byID(int id){
        sqLiteDatabase.delete(NCR_TABLE, NCR_INSPECTION_ID+"="+id, null);
    }



    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_PROJECT_TABLE);
            db.execSQL(CREATE_GEOGRAPHIC_TABLE);
            db.execSQL(CREATE_SUBAREAONE_TABLE);
            db.execSQL(CREATE_SUBAREATWO_TABLE);
            db.execSQL(CREATE_SUBAREATHREE_TABLE);
            db.execSQL(CREATE_INSPECTION_REINSPECTION_TABLE);
            db.execSQL(CREATE_TKC_TABLE);
            db.execSQL(CREATE_ITEM_TABLE);
            db.execSQL(CREATE_CHOOSE_ITEM_TABLE);
            db.execSQL(CREATE_SI_TABLE);
            db.execSQL(CREATE_SURVEY_NAME_ID_TABLE);
            db.execSQL(CREATE_ASSET_TYPE_TABLE);
            db.execSQL(CREATE_INSPECTION_SUBITEM_TABLE);
            db.execSQL(CREATE_SURVEY_FEEDER_TABLE);
            db.execSQL(CREATE_SURVEY_DTRRATING_TABLE);
            db.execSQL(CREATE_SURVEY_DTRMOUTING_TABLE);
            db.execSQL(CREATE_SURVEY_SUBSTATIONVOLTAGE_TABLE);
            db.execSQL(CREATE_SURVEY_INFEEDER_TABLE);
            db.execSQL(CREATE_SURVEY_OUTFEEDER_TABLE);
            db.execSQL(CREATE_SURVEY_PTCAPACITY_TABLE);
            db.execSQL(CREATE_SURVEY_SWITCHGEAR_TABLE);
            db.execSQL(CREATE_SURVEY_LOCATION_TABLE);
            db.execSQL(CREATE_SURVEY_LENGTH_TABLE);
            db.execSQL(CREATE_SURVEY_PHASE_TABLE);
            db.execSQL(CREATE_SURVEY_CONSUMERTYPE_TABLE);
            db.execSQL(CREATE_SURVEY_CATEGORY_TABLE);
            db.execSQL(CREATE_SURVEY_POLETYPE_TABLE);
            db.execSQL(CREATE_SURVEY_POLECONDITION_TABLE);
            db.execSQL(CREATE_SURVEY_POLELOCATION_TABLE);
            db.execSQL(CREATE_SURVEY_INSULATORTYPE_TABLE);
            db.execSQL(CREATE_SURVEY_INSULATORCONDITION_TABLE);
            db.execSQL(CREATE_SURVEY_STAYTYPE_TABLE);
            db.execSQL(CREATE_SURVEY_STAYCONDITION_TABLE);
            db.execSQL(CREATE_SURVEY_GUARDINGTYPE_TABLE);
            db.execSQL(CREATE_SURVEY_GUARDINGCONDITION_TABLE);
            db.execSQL(CREATE_SURVEY_EARTHINGTYPE_TABLE);
            db.execSQL(CREATE_SURVEY_EARTHINGCONDITION_TABLE);
            db.execSQL(CREATE_SURVEY_CROSSING_TABLE);
            db.execSQL(CREATE_WORKPROGRESS_SCOPE_TABLE);
            db.execSQL(CREATE_SURVEY_CONDUCTORTYPE_TABLE);
            db.execSQL(CREATE_SURVEY_CONDUCTORCONDITION_TABLE);
            db.execSQL(CREATE_NCR_TABLE);
            db.execSQL(CREATE_MASTER_DROPDOWN_TABLE);
            db.execSQL(CREATE_MATERIAL_ISSUED_TABLE);

            db.execSQL(CREATE_MGEOGRAPHIC_TABLE);
            db.execSQL(CREATE_MSUBAREAONE_TABLE);

            //db.execSQL(CREATE_FACTORY_TABLE);


        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

            db.execSQL("DROP TABLE IF EXISTS " + CREATE_PROJECT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_GEOGRAPHIC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SUBAREAONE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SUBAREATWO_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SUBAREATHREE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_INSPECTION_REINSPECTION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TKC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_ITEM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CHOOSE_ITEM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SI_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_NAME_ID_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_ASSET_TYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_INSPECTION_SUBITEM_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_FEEDER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_DTRRATING_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_DTRMOUTING_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_SUBSTATIONVOLTAGE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_INFEEDER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_OUTFEEDER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_PTCAPACITY_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_SWITCHGEAR_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_LOCATION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_LENGTH_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_PHASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_CONSUMERTYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_CATEGORY_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_POLETYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_POLECONDITION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_POLELOCATION_TABLE);

            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_INSULATORTYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_INSULATORCONDITION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_STAYTYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_STAYCONDITION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_GUARDINGTYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_GUARDINGCONDITION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_EARTHINGTYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_EARTHINGCONDITION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_CROSSING_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_WORKPROGRESS_SCOPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_CONDUCTORTYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SURVEY_CONDUCTORCONDITION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_NCR_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_MASTER_DROPDOWN_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_MATERIAL_ISSUED_TABLE);

            db.execSQL("DROP TABLE IF EXISTS " + CREATE_MGEOGRAPHIC_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_MSUBAREAONE_TABLE);

           //db.execSQL("DROP TABLE IF EXISTS " + CREATE_FACTORY_TABLE);

            onCreate(db);
        }
    }




}
