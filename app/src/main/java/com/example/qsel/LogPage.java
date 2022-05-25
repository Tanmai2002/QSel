package com.example.qsel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class LogPage extends MainActivity {
ListView lv;
    ArrayList<String> log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_page);
        lv=findViewById(R.id.LogListView);
     log=MainActivity.GetLog();
        ArrayAdapter at=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,log);
        lv.setAdapter(at);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SolveSelected(log.get(position));
                ArrayList t1=new ArrayList(Arrays.asList(log.get(position).split("/")));


                anspage.TopicName=t1.get(t1.size()-1).toString().substring(2);
                addToLog(log.get(position));

            }
        });
    }

}
