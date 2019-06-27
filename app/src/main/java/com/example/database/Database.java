package com.example.database;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class Database extends AppCompatActivity {

    EditText id,name,course,semester,city;
    Button insert,update,delete,select;

    int stdid[],stdsemester[];
    String stdname[],stdcourse[],stdcity[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(threadPolicy);

        bindWidget();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    delete();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectData();
               Intent intent=new Intent(getApplicationContext(),Select.class);

               intent.putExtra("id",stdid);
                intent.putExtra("name",stdname);
                intent.putExtra("course",stdcourse);
                intent.putExtra("semester",stdsemester);
                intent.putExtra("city",stdcity);

               startActivity(intent);
            }
        });



    }

    public void bindWidget()
    {
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        course=(EditText)findViewById(R.id.course);
        semester=(EditText)findViewById(R.id.semester);
        city=(EditText)findViewById(R.id.city);

        insert=(Button)findViewById(R.id.insert);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);
        select=findViewById(R.id.select);

    }

    public HttpURLConnection connect()
    {
        HttpURLConnection connection=null;

        try
        {
            URL url=new URL("http://192.168.1.9/student/data.php");
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);

            Log.d("connection","etablished");

        }catch(Exception e)
        {
            Log.d("connection","disconnected");
        }

        return connection;
    }

    public void insert()
    {
        try
        {
            HttpURLConnection connection=connect();

            OutputStream outputStream=connection.getOutputStream();

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");

            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);

            String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("course","UTF-8")+"="+URLEncoder.encode(course.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(semester.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(city.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("submit","UTF-8")+"="+URLEncoder.encode("insert","UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            InputStream inputStream=connection.getInputStream();
            connection.disconnect();
            Log.d("Data","inserted");


        }catch (Exception e)
        {
            Log.d("Data",e.toString());
        }

    }

    public void update()
    {

        try
        {
            HttpURLConnection connection=connect();

            OutputStream outputStream=connection.getOutputStream();

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");

            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);

            String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("course","UTF-8")+"="+URLEncoder.encode(course.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(semester.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(city.getText().toString(),"UTF-8")+
                    "&"+URLEncoder.encode("submit","UTF-8")+"="+URLEncoder.encode("update","UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            InputStream inputStream=connection.getInputStream();
            connection.disconnect();
            Log.d("Data","updated");


        }catch (Exception e)
        {
            Log.d("Data",e.toString());
        }
    }

    public void delete()
    {

        try
        {
            HttpURLConnection connection=connect();

            OutputStream outputStream=connection.getOutputStream();

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");

            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);

            String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id.getText().toString(),"UTF-8")+

                    "&"+URLEncoder.encode("submit","UTF-8")+"="+URLEncoder.encode("delete","UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            InputStream inputStream=connection.getInputStream();
            connection.disconnect();
            Log.d("Data","deleted");


        }catch (Exception e)
        {
            Log.d("Data",e.toString());
        }
    }


    public void selectData()
    {
        StringBuilder stringBuilder=new StringBuilder();
        String line="";

        try{
            HttpURLConnection conn=connect();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);

            InputStream inputStream=conn.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");


            }

            String data=stringBuilder.toString().trim();

            Log.d("data:",stringBuilder.toString());

            JSONArray jsonArray=new JSONArray(data);



            int length=jsonArray.length();

            stdid=new int[length];
            stdsemester=new int[length];

            stdname=new String[length];
            stdcourse=new String[length];
            stdcity=new String[length];

            JSONObject jsonObject=null;

            for(int i=0;i<length;i++)
            {
                jsonObject=jsonArray.getJSONObject(i);

                stdid[i]=jsonObject.getInt("id");
                stdname[i]=jsonObject.getString("name");
                stdcourse[i]=jsonObject.getString("course");
                stdsemester[i]=jsonObject.getInt("semester");
                stdcity[i]=jsonObject.getString("city");

            }

               Log.d("id",stdid.toString());

        }catch (Exception e)
        {
                Log.d("Data",e.toString());
        }
    }
}
