package com.example.qsel;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Trace;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Timer;

public class FloatinS extends Service implements View.OnClickListener {



        private WindowManager mWindowManager;

        private int Mytime;
    private TextView DoubtNo;
        private TextView QuestNo;
        private RadioGroup answers;
        private View mFloatingView;
        public static pro pr;
        public static ArrayList doubt;
        public static String curdob;








        @Override

        public IBinder onBind(Intent intent) {

            return null;

        }



        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override

        public void onCreate() {

            super.onCreate();

            Mytime=0;

            sw stop=new sw();
            stop.start();


            doubt=new ArrayList(){};
            int i=0;
            while(pr.AnsL.size()>i){
                if(pr.AnsL.get(i).toString().length()>1){
                    doubt.add("z");
                }else {
                    doubt.add("");
                }
                i++;
            }
            doubt.add("");
            doubt.add("");
            curdob="";



            //pr=new pro(MainActivity.set,MainActivity.tn);
            //getting the widget layout from xml using layout inflater

            mFloatingView = LayoutInflater.from(this).inflate(R.layout.fwindoe, null);
            QuestNo=mFloatingView.findViewById(R.id.FwQno);
            answers=mFloatingView.findViewById(R.id.FwAnswers);

            DoubtNo=mFloatingView.findViewById(R.id.Fw_doubt);



            //setting the layout parameters

            final WindowManager.LayoutParams params = new WindowManager.LayoutParams(

                    WindowManager.LayoutParams.WRAP_CONTENT,

                    WindowManager.LayoutParams.WRAP_CONTENT,

                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,

                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,

                    PixelFormat.TRANSLUCENT);





            //getting windows services and adding the floating view to it

            mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

            mWindowManager.addView(mFloatingView, params);





            //getting the collapsed and expanded view from the floating view





            //adding click listener to close button and expanded view
            mFloatingView.findViewById(R.id.FwNext).setOnClickListener(this);


            mFloatingView.findViewById(R.id.FwPre).setOnClickListener(this);

            DoubtNo.setOnClickListener(this);

            mFloatingView.findViewById(R.id.FwSave).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        SaveAns(MainActivity.set);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    stopSelf();
                    return true;
                }
            });







            //adding an touchlistener to make drag movement of the floating widget

            mFloatingView.findViewById(R.id.relativeLayoutParent).setOnTouchListener(new View.OnTouchListener() {

                private int initialX;

                private int initialY;

                private float initialTouchX;

                private float initialTouchY;



                @Override

                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            initialX = params.x;

                            initialY = params.y;

                            initialTouchX = event.getRawX();

                            initialTouchY = event.getRawY();

                            return true;



                        case MotionEvent.ACTION_UP:

                            //when the drag is ended switching the state of the widget
/*
                            kapali_widget.setVisibility(View.GONE);

                            acik_widget.setVisibility(View.VISIBLE);
*/
                            return true;



                        case MotionEvent.ACTION_MOVE:

                            //this code is helping the widget to move around the screen with fingers

                            params.x = initialX + (int) (event.getRawX() - initialTouchX);

                            params.y = initialY + (int) (event.getRawY() - initialTouchY);

                            mWindowManager.updateViewLayout(mFloatingView, params);

                            return true;

                    }

                    return false;

                }

            });

            int count=1;
            while (!pr.AnsL.get(count).equals("1")){
                if(count==1){
                    String s=pr.AnsL.get(1).toString().charAt(0)+"";
                    Mytime=Integer.parseInt(pr.MyTimings.get(1).toString());
               checkAButton( s );
                }
                SetAns(true);
                count++;
            }

        }



        @Override

        public void onDestroy() {

            super.onDestroy();

            if (mFloatingView != null) mWindowManager.removeView(mFloatingView);

        }


        public void SetAns(boolean b){
            int q=Integer.parseInt(QuestNo.getText().toString());
            pr.MyTimings.set(q,Mytime);
            Toast.makeText(getApplicationContext(),pr.MyTimings.get(q)+"",Toast.LENGTH_LONG).show();


            RadioButton rb=mFloatingView.findViewById(answers.getCheckedRadioButtonId());
try {


    String t;
    doubt.set(q,curdob);
    t = "" + rb.getText().toString();
    pr.SetAnsList(q, t+doubt.get(q));

}catch (Exception e){

    pr.SetAnsList(q,"0"+doubt.get(q));
}
            if(b){
                doubt.add("");
                q=q+1;
            }else {
                q=q-1;

            }
            Mytime=Integer.parseInt(pr.MyTimings.get(q).toString());
            QuestNo.setText(q+"");
            answers.clearCheck();

            curdob=doubt.get(q).toString();
            if(curdob.equals("")){
                DoubtNo.setBackgroundColor(Color.TRANSPARENT);
            }else if(curdob.equals("z")){
                DoubtNo.setBackgroundColor(Color.RED);
            }
            String s=pr.AnsL.get(q).toString().charAt(0)+"";

           // Toast.makeText(getApplicationContext(),pr.AnsL.get(q).toString(),Toast.LENGTH_LONG).show();
           checkAButton(s);




        }

        public void checkAButton(String s){
            switch (s){
                case "A":
                    answers.check(R.id.Fw_a);
                    break;
                case "B":
                    answers.check(R.id.Fw_b);
                    break;
                case "C":
                    answers.check(R.id.Fw_c);
                    break;
                case "D":
                    answers.check(R.id.Fw_d);
                    break;
                default:
                    answers.clearCheck();
                    break;


            }
        }
        public void SaveAns(boolean s) throws IOException {

            File f= new File(pr.fAddress);
            FileWriter fr=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fr);
            PrintWriter pw=new PrintWriter(bw);
            pw.println(pr.AnsL+"");
            pw.close();
            bw.close();
            fr.close();
            if(!s){


                Toast.makeText(this,pr.f.canWrite()+"",Toast.LENGTH_LONG).show();

try {
    pr.checkAns();
}catch (Exception e){
    pr.resetGivenList();
    e.printStackTrace();
}


pTL();
                Toast.makeText(getApplicationContext(),"Sumbit"+"",Toast.LENGTH_LONG).show();
                Intent h=new Intent(FloatinS.this,anspage.class);
                h.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(h);
            }



        }

        @Override

        public void onClick(View v) {

          switch (v.getId()) {
              case R.id.FwNext:
                  SetAns(true);


                  break;
              case R.id.FwPre:
                  SetAns(false);
                  break;
              case R.id.Fw_doubt:

                  if(curdob.equals("z")){
                      curdob="";
                      DoubtNo.setBackgroundColor(Color.TRANSPARENT);
                  }else if(curdob.equals("")){
                      curdob="z";
                      DoubtNo.setBackgroundColor(Color.RED);
                  }
                  break;


          }

/*
                case R.id.layoutExpanded:

                    //switching views

                    kapali_widget.setVisibility(View.VISIBLE);

                    acik_widget.setVisibility(View.GONE);

                    break;



                case R.id.buttonClose:

                    //closing the widget

                    stopSelf();

                    break;

            }
*/
        }

        public void pTL()throws IOException{
            File f= new File(pr.TimingAdd);
            FileWriter fr=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fr);
            PrintWriter pw=new PrintWriter(bw);
            pw.println(pr.MyTimings+"");
            pw.close();
            bw.close();
            fr.close();
        }

    class sw extends Thread{
        @Override
        public void run() {
            while(true){
                try {
                    sw.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                Mytime=Mytime+1;

            }
        }
    }


}
