package com.example.qsel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public static String tn;
    int d1=0;
    int time;
    public static boolean set;
    EditText e;

    public static String NameOfTopic;
    Spinner s1;
    Spinner s2;
    String[] sub = {"Others", "Maths", "Chemistry", "Physics", "Full"};

    String[][] topic = {{"Default"},
            {"Default","Logic","Matrices","Trigo Functions","Pairs of Straight Line","Vectors","3DGeometry","Lines","Plane","Linear Programming",
            "Continuity","Differentiations","Application Of Derivatives","Integration","Definite Integral","Application of DI","Differential Equation","Probability","Binomial Distribution","XI","XII"},
            {"Deafult","DfBlock","CoOrdination Compounds","Halogen Deriv","Alcohol,Phenol,Ether","Ald,Ketone,Acid","Nitrogen Compounds","BioMolecule","Polymers","EveryDay Chemistry",
                    "Solid State","Solution And Coll","Thermodyanamics","Electrochemistry","Kinetics","Isolation","PBlock" ,"XI","XII"},
            {"Default","Circular Motion","Gravitation","Rotational Motion","Oscillation","Elasticity","Surface Tension","WaveMotion","Stationary Waves","Radiations and KTOG","Wave Theory Of Light"
            ,"Interference and Diffraction","ElectroStatics","Current Electricity","Magnetic Effect Of ElectricCurrent","Magnetism","EMInduction","Photons and Electons","Atoms,Molecules and Nucleii","Semiconductors","Communication Systems","XI","XII"},
            {"1","2","3","4","5","6","7","8","9","10","XI","XII"}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        time=0;

        String k=Environment.getExternalStorageDirectory().toString()+"/Dazz";
        String k1=k+"/Topics";
        String k2=k+"/Solved";
        String k3=k+"/Solved";
        File f=new File(k1);
        File f1=new File(k2);
        File f3=new File(k3);
        if(!f.exists()){
            f.mkdirs();
            f1.mkdir();
            f3.mkdir();
            ArrayList<String> s=new ArrayList<>();
            PrintLog(s);

        }


         e=findViewById(R.id.ET);
        Button Set=(Button) findViewById(R.id.SetQ);
        Button fw=(Button)findViewById(R.id.Fw);
       s1=findViewById(R.id.spinner1);
        ArrayAdapter a=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sub);
        s1.setAdapter(a);

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),time+"",Toast.LENGTH_LONG).show();
            }
        });
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s2=(Spinner) findViewById(R.id.spinner2);
                ArrayAdapter a2=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,topic[position]);
                s2.setAdapter(a2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        fw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NameOfTopic=s2.getSelectedItem().toString();
                tn=e.getText().toString()+"/"+s1.getSelectedItem().toString().substring(0,2)+s2.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),tn,Toast.LENGTH_LONG).show();
                pro p=new pro(false,tn);
                try {
                    p.setAnsListForChecking();
                    p.setTimeListForChecking();
                }catch(IOException e){
                    e.printStackTrace();
                }
                try {
                    p.checkAns();
                }catch (Exception e){
                    p.resetGivenList();
                    e.printStackTrace();

                }
                FloatinS.pr=p;
                Intent i=new Intent(MainActivity.this,anspage.class);
                anspage.TopicName=NameOfTopic;
                startActivity(i);

            }

        });
        Button Solve=(Button) findViewById(R.id.SolveQ);
    Solve.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String k=Environment.getExternalStorageDirectory().toString()+"/Dazz";
            String k1=k+"/Solved/"+e.getText().toString();

            String k2=k+"/Timing/"+e.getText().toString();
            File f=new File(k1);
            File f2=new File(k2);
            if(!f.exists()){
                f.mkdir();

            }
            if(!f2.exists()){
                f2.mkdir();

            }
            NameOfTopic=s2.getSelectedItem().toString();
            anspage.TopicName=NameOfTopic;
            tn=e.getText().toString()+"/"+s1.getSelectedItem().toString().substring(0,2)+s2.getSelectedItem().toString();
            addToLog(tn);
            SolveSelected(tn);
           /* Intent i=new Intent(MainActivity.this,SolvePage.class);
            startActivity(i);
*/
        }
    });
    Set.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String k=Environment.getExternalStorageDirectory().toString()+"/Dazz";
            String k1=k+"/Topics/"+e.getText().toString();
            File f=new File(k1);
            if(!f.exists()){
                f.mkdir();
            }
            tn=e.getText().toString()+"/"+s1.getSelectedItem().toString().substring(0,2)+s2.getSelectedItem().toString();
            SetSelected(tn);


        }
    });
    }
    public void SetSelected(String n){

        Toast.makeText(getApplicationContext(),n,Toast.LENGTH_LONG).show();
        set=true;
        FloatinS.pr=new pro(true,n);
        Intent i=new Intent(MainActivity.this,FloatinS.class);
        startService(i);
    }
    public void SolveSelected(String n){

        Toast.makeText(getApplicationContext(),n,Toast.LENGTH_LONG).show();
        set=false;
        FloatinS.pr=new pro(false,n);
        Intent i=new Intent(MainActivity.this,FloatinS.class);
        startService(i);
    }
public void PrintLog( ArrayList<String> Log) {
   try{ File f= new File(Environment.getExternalStorageDirectory().toString()+"/Dazz/Log.txt");
       FileWriter fr=new FileWriter(f);
       BufferedWriter bw=new BufferedWriter(fr);
       PrintWriter pw=new PrintWriter(bw);
       pw.println(Log+"");
       pw.close();
       bw.close();
       fr.close();
   }catch (IOException e){
       e.printStackTrace();
   }
}
public static ArrayList<String> GetLog(){
        ArrayList<String> log=new ArrayList<>();
   try{
       File f=new File(Environment.getExternalStorageDirectory().toString()+"/Dazz/Log.txt");
       Scanner sc=new Scanner(f);
       String a="";
       while(sc.hasNextLine()){
           a=a+sc.nextLine();

       }
       a=a.substring(1,a.length()-1);

       log.addAll(Arrays.asList(a.split(", ")));
   }catch (Exception e){
       log.add("");
   }

        return  log;
}
public void addToLog(String s){
        ArrayList<String> temp=GetLog();
        temp.add(0,s);
        PrintLog(temp);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();

        mi.inflate(R.menu.mainmenus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.MainMenu:
              if(!getApplicationContext().equals(MainActivity.this)){
                  Intent i=new Intent(getApplicationContext(),MainActivity.class);
                  startActivity(i);
              }
                break;
            case R.id.LogMenu:
                Intent i2=new Intent(getApplicationContext(),LogPage.class);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
