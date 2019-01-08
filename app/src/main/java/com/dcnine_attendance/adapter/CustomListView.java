package com.dcnine_attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dcnine_attendance.R;
import com.dcnine_attendance.data_holder.DataHolder_SiteInspection;

import java.util.ArrayList;

public class CustomListView extends BaseAdapter {

    private LayoutInflater lInflater;
//    private List<ItemObject> listStorage;
ArrayList<String> item_name_list;
    ViewHolder listViewHolder;
    ArrayList<String> selectedItems = new ArrayList<String>();
    public CustomListView(Context context, ArrayList<String> item_name_list) {
        lInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.item_name_list=item_name_list;
//        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return item_name_list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            listViewHolder = new ViewHolder();
            convertView = lInflater.inflate(R.layout.listview_with_checkbox, parent, false);

            listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.textView);
            listViewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(item_name_list.get(position));
        listViewHolder.checkBox.setChecked(false);
        listViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("gggggt"+position);

                System.out.println("ffff"+item_name_list.get(position));
               selectedItems.add(String.valueOf(position));
                    //selectedItems.add(item_name_list.get(position));
                    DataHolder_SiteInspection.getInstance().setSelectedItems(selectedItems);
//                for(int i =0;i<= position;i++)
//                {
//                    System.out.println("ffff"+item_name_list.get(position));
//                    selectedItems.add(item_name_list.get(i));
//                }

//                SparseBooleanArray checked = listViewHolder.checkBox.getCheckedItemPositions();
//                ArrayList<String> selectedItems = new ArrayList<String>();
//                for (int i = 0; i < checked.size(); i++) {
//                    // Item position in adapter
//                    int position2 = checked.keyAt(i);
//                    // Add sport if it is checked i.e.) == TRUE!
//                    if (checked.valueAt(i))
//                        selectedItems.add(adapter.getItem(position2).toString());
//                }
//
//                String[] outputStrArr = new String[selectedItems.size()];
//
//                for (int i = 0; i < selectedItems.size(); i++) {
//                    outputStrArr[i] = selectedItems.get(i);
//                    System.out.println("values ON CLICK"+ outputStrArr[i]);
//                }
            }
        });
        listViewHolder.checkBox
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
//                        System.out.println("gggggt"+item_name_list.position);
//                        int getPosition = (Integer) buttonView.getTag(); // Here
//                        // we get  the position that we have set for the checkbox using setTag.
//                        item_name_list.get(getPosition); // Set the value of
                        // checkbox to
                        // maintain its
                        // state.
                    }
                });
        return convertView;
    }

    static class ViewHolder{

        TextView textInListView;
        CheckBox checkBox;
    }
}