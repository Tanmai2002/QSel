import android.location.Address;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
    int AnsNO;
    String fAddress;
    File f;
    pro(String mode, String TopicName){
        fAddress= Environment.getExternalStorageDirectory().toString()+"/Dazz/Topics/"+TopicName+".txt";

       Checking=new ArrayList<Boolean>();
        AnsL=new ArrayList<String>();
        GivenAns=new ArrayList<String>();
        AnsNO=1;
        int i=0;
        f=new File(fAddress);

        while(i<100){
            AnsL.add(0);
            i++;
        }
        if(!mode.equals("set")){

            try {
                initialiseAnsL();
            }catch (IOException e){

        }

            fAddress= Environment.getExternalStorageDirectory().toString()+"/Dazz/Solved/"+TopicName+".txt";

      }
    }
    void initialiseAnsL()throws IOException {
        Scanner sc=new Scanner(f);
        String a="";
        while(sc.hasNextLine()){
            a=a+sc.nextLine();

        }
        a=a.substring(1,a.length()-1);
        GivenAns.add(0+"");
        GivenAns.addAll(Arrays.asList(a.split(", ")));

        sc.close();
    }
    void SetAnsList(int i,String ans){
        AnsL.set(i,ans);
    }
    void printAnsList() throws IOException{
        String gen=AnsL+"";


        FileOutputStream pw=new FileOutputStream(fAddress);
            PrintWriter pwn=new PrintWriter(pw, true);

            pwn.println(gen);

           pwn.close();
pw.close();



    }
    void checkAns(){
        int i=0;
        while(i<AnsL.size()){
            Checking.set(i,AnsL.get(i)==GivenAns.get(i));
        }
    }
}
