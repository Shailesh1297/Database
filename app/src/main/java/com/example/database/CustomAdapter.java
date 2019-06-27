package com.example.database;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context c;
    String name[],course[],city[];
    int id[],semester[];


    LayoutInflater layoutinflater;

    public CustomAdapter(Context c,int id[],String name[],String course[],int semester[],String city[])
    {
        this.c=c;
        this.id=id;
        this.name=name;
        this.course=course;
        this.semester=semester;
        this.city=city;
        layoutinflater=LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView stdid,stdname,stdcourse,stdsemester,stdcity;


        convertView=layoutinflater.inflate(R.layout.custom,null,false);

        stdid=(TextView)convertView.findViewById(R.id.id);
        stdname=(TextView)convertView.findViewById(R.id.name);
        stdcourse=(TextView)convertView.findViewById(R.id.course);
        stdsemester=(TextView)convertView.findViewById(R.id.semester);
        stdcity=(TextView)convertView.findViewById(R.id.city);


        stdid.setText("+"+id[position]);
        stdname.setText(name[position]);
        stdcourse.setText(course[position]);
        stdsemester.setText("+"+semester[position]);
        stdcity.setText(city[position]);

        return convertView;
    }
}
