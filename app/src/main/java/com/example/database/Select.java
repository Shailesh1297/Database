package com.example.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Select extends AppCompatActivity {

    ListView list;
    String name[],course[],city[];
    int id[],semester[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        list=(ListView)findViewById(R.id.list);

        Bundle bundle=getIntent().getExtras();

        name=bundle.getStringArray("name");
        course=bundle.getStringArray("course");
        city=bundle.getStringArray("city");
        id=bundle.getIntArray("id");
        semester=bundle.getIntArray("semester");

         CustomAdapter ca=new CustomAdapter(getBaseContext(),id,name,course,semester,city);

        list.setAdapter(ca);



    }
}
