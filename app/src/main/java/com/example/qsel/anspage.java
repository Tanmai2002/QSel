package com.example.qsel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class anspage extends AppCompatActivity {
   private pro p;
   public static String TopicName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anspage);
        ListView lv=findViewById(R.id.anspage_qno);

        float avgTime=0;
        TextView tv=(TextView) findViewById(R.id.anspage_TITLE);

        tv.setText(TopicName);
        TextView OutOf=(TextView) findViewById(R.id.anspage_Outof);

       ArrayList qnot =new ArrayList<String>();
        int c;

      p=FloatinS.pr;
        for(c=0;c<p.AnsL.size();c++){
         qnot.add(c+"");

        }

c=1;
        int tot=0;
        int a=0;

        while((!p.AnsL.get(c).toString().equals("1"))){
            tot=tot+1;

            avgTime=avgTime+Float.parseFloat(p.MyTimings.get(c).toString());
            if( p.Checking.get(c).toString().equals("true")){
                a=a+1;
            }
            c++;
        }


        avgTime=avgTime/tot;
        TextView at=findViewById(R.id.anspage_Avg_Timw);
        at.setText(avgTime+"");
        OutOf.setText(a+"/"+tot);
       MyAdapter ma=new MyAdapter(this,  qnot.subList(0,tot+1),p.AnsL,p.GivenAns,p.Checking,p.MyTimings);


        lv.setAdapter(ma);

    }
    class MyAdapter extends ArrayAdapter<String>{
        Context Rcon;
        List Rq;

        List mT;
        List YA;
        List GA;
        List TRv;

        MyAdapter(Context c, List Qno,List YourAns,List GivenAns, List Tv,List mtimings ){

            super(c,R.layout.row,R.id.row_Qno, Qno);
            this.Rq= Qno;
          this.GA= GivenAns;
           this.TRv= Tv;
            this.Rcon=c;
            this.YA= YourAns;
            this.mT=mtimings;
            Rq.set(0,"Qno");
            GA.set(0,"Given Ans");
            YA.set(0,"Your Ans");
            TRv.set(0,"TV");
            mT.set(0,"Time");





        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View r = li.inflate(R.layout.row, parent, false);
            if (!GA.get(position).toString().equals("1")) {
                TextView QnoRow = r.findViewById(R.id.row_Qno);
                TextView YARow = r.findViewById(R.id.row_Yourans);
                TextView MTime = r.findViewById(R.id.row_time);
                TextView GARow = r.findViewById(R.id.row_GivnAms);
                TextView TVRow = r.findViewById(R.id.row_tvalue);
                LinearLayout bac = r.findViewById(R.id.row_main);
                QnoRow.setText(Rq.get(position).toString());
                if(position==0) {
                    YARow.setText(YA.get(position).toString()+ "");
                }else{
                    YARow.setText(YA.get(position).toString().charAt(0) + "");
                }
                GARow.setText(GA.get(position).toString());
                TVRow.setText(TRv.get(position).toString());
                MTime.setText(mT.get(position).toString());
                if (position != 0) {

                    if ((Boolean) TRv.get(position)) {
                        if ((Integer) mT.get(position) < 60) {
                            bac.setBackgroundColor(Color.GREEN);
                        } else if ((Integer) mT.get(position) < 120) {
                            bac.setBackgroundColor(Color.YELLOW);
                        } else {
                            bac.setBackgroundColor(Color.MAGENTA);
                        }
                    }else {
                        if ((Integer) mT.get(position) < 60) {
                            MTime.setBackgroundColor(Color.GREEN);
                        } else if ((Integer) mT.get(position) < 120) {
                            MTime.setBackgroundColor(Color.YELLOW);
                        } else {
                            MTime.setBackgroundColor(Color.MAGENTA);
                        }
                    }

                    if(YA.get(position).toString().length()>1){
                        QnoRow.setBackgroundColor(Color.BLUE);
                    }

                }


            }
            return r;
            }

        }
    }

