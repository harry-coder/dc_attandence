package com.dcnine_attendance.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcnine_attendance.R;
import com.dcnine_attendance.data_holder.ScopeDTO;

import java.util.ArrayList;

/**
 * Created by nitinb on 06-02-2016.
 */
public class ScopeAdapter extends BaseAdapter {


    ArrayList<ScopeDTO> scopeDTOArrayList;
    Context mContext;

    public ScopeAdapter(Context context, ArrayList<ScopeDTO> scopeDTOs){
        scopeDTOArrayList=scopeDTOs;
        mContext=context;


    }

    @Override
    public int getCount() {
        return scopeDTOArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (convertView==null){

            LayoutInflater layoutInflater=((Activity)mContext).getLayoutInflater();
            convertView=layoutInflater.inflate(R.layout.custom_spinner,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.textViewName=(TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(viewHolder);


        }else{

            viewHolder=(ViewHolder)convertView.getTag();

        }if(i==0){
            viewHolder.textViewName.setText(scopeDTOArrayList.get(i).getDefaultSelect());

        }else {
            viewHolder.textViewName.setText(scopeDTOArrayList.get(i).getScopeName());
        }



        return convertView;
    }

    static class ViewHolder{

        TextView textViewName;

    }
}
