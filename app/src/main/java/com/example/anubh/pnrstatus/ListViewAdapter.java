package com.example.anubh.pnrstatus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.anubh.pnrstatus.Constants.FIRST_COLUMN;
import static com.example.anubh.pnrstatus.Constants.SECOND_COLUMN;
import static com.example.anubh.pnrstatus.Constants.THIRD_COLUMN;

/**
 * Created by anubh on 13-Oct-16.
 */

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView sno;
    TextView bookingstatus;
    TextView currentstatus;

    public ListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.list_row, null);

            sno = (TextView) convertView.findViewById(R.id.sno);
            bookingstatus = (TextView) convertView.findViewById(R.id.bookingstatus);
            currentstatus = (TextView) convertView.findViewById(R.id.currentstatus);


        }

        HashMap<String, String> map=list.get(position);
        sno.setText(map.get(FIRST_COLUMN));
        bookingstatus.setText(map.get(SECOND_COLUMN));
        currentstatus.setText(map.get(THIRD_COLUMN));

        return convertView;
    }
}

