package com.dcnine_attendance.adapter;

/**
 * Created by soubhagyarm on 22-02-2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dcnine_attendance.R;
import com.dcnine_attendance.data_holder.DataHolder_SiteInspection;

import java.util.ArrayList;

public class InteractiveArrayAdapter extends ArrayAdapter<String> {

    private final ArrayList<String> list;
    private final Activity context;

    public InteractiveArrayAdapter(Activity context, ArrayList<String> list) {
        super(context, R.layout.rowbuttonlayout, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox ,  checkBox2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
          final ArrayList<String> selectedStrings= new ArrayList<String>();
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.rowbuttonlayout, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.checkBox2 = (CheckBox) view.findViewById(R.id.uncheck);
            viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
//                            Model element = (Model) viewHolder.checkbox
//                                    .getTag();
//                            element.setSelected(buttonView.isChecked());
                           // Toast.makeText(context,"postion is "+position+" mark is "+list.get(position) ,Toast.LENGTH_SHORT).show();
//                           selectedStrings
                            selectedStrings.add(list.get(position));


                        }
                    });
            DataHolder_SiteInspection.getInstance().setSelectedStrings(selectedStrings);
            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position));
//        holder.checkbox.setChecked();
        return view;
    }
}