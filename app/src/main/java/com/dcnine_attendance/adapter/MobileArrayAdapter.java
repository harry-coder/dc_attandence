package com.dcnine_attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dcnine_attendance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soubhagyarm on 07-03-2016.
 */
public class MobileArrayAdapter extends ArrayAdapter<String> {
    private List<String> inspList,districtList,blockList,villageList,remarkList,mobileList,inspByList,districtIdList,schemeList,itemList,tolaList;
    //private List<String> districtList;
    private Context context;
    ArrayList<Boolean> positionArray,positionArray2;
    ArrayList<String> selectedItems = new ArrayList<String>();
    ArrayList<String> selectedItems2 = new ArrayList<String>();
    private String[] arrTemp;



    public MobileArrayAdapter(Context context, List<String> offersAreaList, List<String> offersAreaCodeList,
                              List<String> blockList, List<String> villageList, List<String> itemList, List<String> mobileList) {
        super(context, R.layout.report_view_list, offersAreaList);
        this.context=context;
        this.inspList=offersAreaList;
        this.districtList=offersAreaCodeList;
        this.blockList=blockList;
        this.villageList=villageList;
        this.itemList=itemList;
        this.mobileList=mobileList;

       /* positionArray = new ArrayList<Boolean>(offersAreaList.size());
        positionArray2 = new ArrayList<Boolean>(offersAreaList.size());
        for(int i =0;i<offersAreaList.size();i++){
            positionArray.add(false);
            positionArray2.add(false);
        }*/

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.report_view_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textview1);
        TextView textView2 = (TextView) rowView.findViewById(R.id.textview2);
        TextView textView3 = (TextView) rowView.findViewById(R.id.textview3);
        TextView textView4 = (TextView) rowView.findViewById(R.id.textview4);
        TextView textView5 = (TextView) rowView.findViewById(R.id.textview5);
        TextView textView6 = (TextView) rowView.findViewById(R.id.textview6);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(inspList.get(position));
        textView2.setText(districtList.get(position));
        textView3.setText(blockList.get(position));
        textView4.setText(villageList.get(position));
        textView5.setText(itemList.get(position));
        textView6.setText(mobileList.get(position));

        //editText6.setText(arrTemp[position]);


        // Change icon based on name
        String s = inspList.get(position);
        String s1 = districtList.get(position);


        System.out.println(s);
        System.out.println(s1);

       /* if (s.equals("WindowsMobile")) {
            imageView.setImageResource(R.drawable.windowsmobile_logo);
        } else if (s.equals("iOS")) {
            imageView.setImageResource(R.drawable.ios_logo);
        } else if (s.equals("Blackberry")) {
            imageView.setImageResource(R.drawable.blackberry_logo);
        } else {
            imageView.setImageResource(R.drawable.android_logo);
        }*/

        return rowView;
    }
}