package com.example.qsel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SetPage extends AppCompatActivity {

    pro p;
    String ans;
    RadioGroup answers;
    TextView Topic;
    TextView QuestNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_page);
        ans=0+"";
        Button Next=(Button) findViewById(R.id.SetNextQuest);
        Button Previous=(Button) findViewById(R.id.SetPreQuest);
        Button Save=(Button) findViewById(R.id.SaveGivenAns);
        Topic =(TextView) findViewById(R.id.ETopicName);
        QuestNo=findViewById(R.id.SQuestNo);
       answers=(RadioGroup)findViewById(R.id.SetAnswers);
       Topic.setText(MainActivity.tn);
        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.SETA:
                        ans="A";
                        break;
                    case R.id.SETB:
                        ans="B";
                        break;
                    case R.id.SETC:
                        ans="C";
                        break;
                        case R.id.SETD:
                            ans="D";
                        break;
                    default:
                        ans=0+"";
                        break;


                }
            }
        });
        p=new pro(true,Topic.getText().toString());

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetAns(true);
            }
        });

        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetAns(false);
            }
        });

        Save.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    saveAns();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return true;
            }
        });


    }
    void SetAns(Boolean b){
        int q=Integer.parseInt(QuestNo.getText().toString());
        p.SetAnsList(q,ans);
        if(b){
            q=q+1;
        }else {
            q=q-1;

        }
        QuestNo.setText(q+"");
        answers.clearCheck();
        String s=p.AnsL.get(q).toString();
        switch (s){
            case "A":
                answers.check(R.id.SETA);
                break;
            case "B":
                answers.check(R.id.SETB);
                break;
            case "C":
                answers.check(R.id.SETC);
                break;
            case "D":
                answers.check(R.id.SETD);
                break;
                default:
                answers.clearCheck();
                break;


        }

        Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
    }
    void saveAns() throws IOException {
        File f= new File(p.fAddress);
        FileWriter fr=new FileWriter(f);
        BufferedWriter bw=new BufferedWriter(fr);
        PrintWriter pw=new PrintWriter(bw);
        pw.println(p.AnsL+"");
        pw.close();
        bw.close();
        fr.close();



        Toast.makeText(this,p.f.canWrite()+"",Toast.LENGTH_LONG).show();

    }
}
