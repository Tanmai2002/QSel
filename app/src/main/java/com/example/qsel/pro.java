package com.example.qsel;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class pro {
    ArrayList AnsL;
    ArrayList Checking;
    ArrayList GivenAns;
    ArrayList MyTimings;
    int AnsNO;
    String TimingAdd;
    String fAddress;
    File f;
    File f2;
    Boolean gaOrAnsL=true;
    boolean m;
    //Set==true,Solve==False
    pro(boolean mode, String TopicName){
        fAddress= Environment.getExternalStorageDirectory().toString()+"/Dazz/Topics/"+TopicName+".txt";

        this.m=mode;
       Checking=new ArrayList<Boolean>();
        MyTimings=new ArrayList<Integer>();
        AnsL=new ArrayList<String>();
        GivenAns=new ArrayList<String>();
        AnsNO=1;
        int i=0;

        TimingAdd=Environment.getExternalStorageDirectory().toString()+"/Dazz/Timing/"+TopicName+".txt";
        f=new File(fAddress);

        f2=new File(TimingAdd);

        while(i<500){
            AnsL.add("1");
            MyTimings.add(0);
            Checking.add(false);
            i++;
        }


        if(f.exists()&& m){

            try {
                GivenAns.clear();
                initialiseAnsL();
            }catch (IOException e){

            }



        }
        if(!mode){

            try {
                initialiseAnsL();

            }catch (IOException e){

        }

            fAddress= Environment.getExternalStorageDirectory().toString()+"/Dazz/Solved/"+TopicName+".txt";

         f=new File(fAddress);
            if(f.exists()){
                gaOrAnsL=false;
                try {
                    initialiseAnsL();

                }catch (IOException e){

                }
            }


      }

    }
    void initialiseAnsL()throws IOException {
        Scanner sc=new Scanner(f);
        String a="";
        while(sc.hasNextLine()){
            a=a+sc.nextLine();

        }
        a=a.substring(1,a.length()-1);


        if(!m) {
            if (gaOrAnsL) {
                GivenAns.addAll(Arrays.asList(a.split(", ")));
            }else if(!gaOrAnsL){
                AnsL.clear();
                AnsL.addAll(Arrays.asList(a.split(", ")));
                setTimeListForChecking();
            }

        }
        if(m){
            AnsL.clear();
            AnsL.addAll(Arrays.asList(a.split(", ")));
        }

        sc.close();
    }
    void SetAnsList(int i,String ans){
        AnsL.set(i,ans);
    }
    void printAnsList(){
        /*String s=AnsL+"";
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            pw.write(s);
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
    void checkAns(){
        int i=0;
        while(i<AnsL.size()){
            Checking.set(i,( AnsL.get(i).toString().charAt(0)+"").equals(GivenAns.get(i).toString()));
            i++;
        }
    }

    public void setAnsListForChecking() throws IOException{
        Scanner sc=new Scanner(new File(fAddress));

        String a="";
        while(sc.hasNextLine()){
            a=a+sc.nextLine();

        }
        a=a.substring(1,a.length()-1);

        AnsL.clear();

        AnsL.addAll(Arrays.asList(a.split(", ")));

        sc.close();






    }
    public void setTimeListForChecking() throws IOException{
        Scanner sc2=new Scanner(new File(TimingAdd));
        String a="";
        while(sc2.hasNextLine()){
            a=a+sc2.nextLine();

        }
        a=a.substring(1,a.length()-1);



        ArrayList tAr=new ArrayList();
        tAr.addAll( Arrays.asList(a.split(", ")));
        int c=0;
        while (tAr.size()>c){
            MyTimings.set(c,Integer.parseInt(tAr.get(c).toString()));
            c++;
        }

        sc2.close();
    }

    public void resetGivenList(){
        int i=0;
        while (i<500){
            GivenAns.add(0);
            i++;
        }
    }
}
