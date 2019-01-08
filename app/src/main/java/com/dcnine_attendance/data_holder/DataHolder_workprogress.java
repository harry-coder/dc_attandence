package com.dcnine_attendance.data_holder;

import java.util.ArrayList;

/**
 * Created by nitinb on 10-02-2016.
 */
public class DataHolder_workprogress {

    private static DataHolder_workprogress dataObject = null;


    public DataHolder_workprogress() {
        // left blank intentionally
    }

    public static DataHolder_workprogress getInstance() {
        if (dataObject == null)
            dataObject = new DataHolder_workprogress();
        return dataObject;
    }

    String pick_date;
    String geographoic_id;
    String subareaone;
    String subareatwo;
    String subareathree;

    String material_location;
    String scheme_id;
    String tkc_id;

    public String getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(String scheme_id) {
        this.scheme_id = scheme_id;
    }

    public String getTkc_id() {
        return tkc_id;
    }

    public void setTkc_id(String tkc_id) {
        this.tkc_id = tkc_id;
    }

    public String getMaterial_location() {
        return material_location;
    }

    public void setMaterial_location(String material_location) {
        this.material_location = material_location;
    }

    public String getTkc_map() {
        return tkc_map;
    }

    public void setTkc_map(String tkc_map) {
        this.tkc_map = tkc_map;
    }

    String tkc_map;

    public String getPick_date() {
        return pick_date;
    }

    public void setPick_date(String pick_date) {
        this.pick_date = pick_date;
    }

    public String getGeographoic_id() {
        return geographoic_id;
    }

    public void setGeographoic_id(String geographoic_id) {
        this.geographoic_id = geographoic_id;
    }

    public String getSubareaone() {
        return subareaone;
    }

    public void setSubareaone(String subareaone) {
        this.subareaone = subareaone;
    }

    public String getSubareatwo() {
        return subareatwo;
    }

    public void setSubareatwo(String subareatwo) {
        this.subareatwo = subareatwo;
    }

    public String getSubareathree() {
        return subareathree;
    }

    public void setSubareathree(String subareathree) {
        this.subareathree = subareathree;
    }

    public String getPick_item() {
        return pick_item;
    }

    public void setPick_item(String pick_item) {
        this.pick_item = pick_item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    String pick_item;
    String quantity;
    String remark;

    public String receiving_item;
    private ArrayList<String> receivingselecteditem;

    public String getReceiving_item() {
        return receiving_item;
    }

    public void setReceiving_item(String receiving_item) {
        this.receiving_item = receiving_item;
    }

    public ArrayList<String> getReceivingselecteditem() {
        return receivingselecteditem;
    }

    public void setReceivingselecteditem(ArrayList<String> receivingselecteditem) {
        this.receivingselecteditem = receivingselecteditem;
    }

    private String str_pole;
    private String str_dt;

    public String getStr_pole() {
        return str_pole;
    }

    public void setStr_pole(String str_pole) {
        this.str_pole = str_pole;
    }

    public String getStr_dt() {
        return str_dt;
    }

    public void setStr_dt(String str_dt) {
        this.str_dt = str_dt;
    }

    public String getStr_conductor() {
        return str_conductor;
    }

    public void setStr_conductor(String str_conductor) {
        this.str_conductor = str_conductor;
    }

    public String getStr_cable() {
        return str_cable;
    }

    public void setStr_cable(String str_cable) {
        this.str_cable = str_cable;
    }

    public String getStr_metering() {
        return str_metering;
    }

    public void setStr_metering(String str_metering) {
        this.str_metering = str_metering;
    }

    public String getStr_substation() {
        return str_substation;
    }

    public void setStr_substation(String str_substation) {
        this.str_substation = str_substation;
    }

    private String str_conductor;
    private String str_cable;
    private String str_metering;
    private String str_substation;

    public void nullify_DataHolder_workprogress() {

        dataObject=null;

        str_pole=null;
        str_dt=null;
        str_conductor=null;
        str_cable=null;
        str_metering=null;
        str_substation=null;


        pick_date=null;
        geographoic_id=null;
        subareaone=null;
        subareatwo=null;
        subareathree=null;
        pick_item=null;
        remark=null;
        scheme_id=null;
        tkc_id=null;


        quantity=null;




    }


  /*  public void nullify_NextITEMDataHolder_workprogress() {

        dataObject=null;

        pick_item=null;
        quantity=null;

    }*/


    String vpick_date;
    String vgeographoic_id;
    String vsubareaone;
    String vsubareatwo;
    String vsubareathree;
    String vpick_item;

    public String getVpick_date() {
        return vpick_date;
    }

    public void setVpick_date(String vpick_date) {
        this.vpick_date = vpick_date;
    }

    public String getVgeographoic_id() {
        return vgeographoic_id;
    }

    public void setVgeographoic_id(String vgeographoic_id) {
        this.vgeographoic_id = vgeographoic_id;
    }

    public String getVsubareaone() {
        return vsubareaone;
    }

    public void setVsubareaone(String vsubareaone) {
        this.vsubareaone = vsubareaone;
    }

    public String getVsubareatwo() {
        return vsubareatwo;
    }

    public void setVsubareatwo(String vsubareatwo) {
        this.vsubareatwo = vsubareatwo;
    }

    public String getVsubareathree() {
        return vsubareathree;
    }

    public void setVsubareathree(String vsubareathree) {
        this.vsubareathree = vsubareathree;
    }

    public String getVpick_item() {
        return vpick_item;
    }

    public void setVpick_item(String vpick_item) {
        this.vpick_item = vpick_item;
    }
}
