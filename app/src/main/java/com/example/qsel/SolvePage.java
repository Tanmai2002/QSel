package com.example.qsel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SolvePage extends AppCompatActivity {

    public static pro pr;
    String ans;
    TextView Topic;
    TextView QuestNo;
    RadioGroup answers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_page);
        TextView tv= (TextView) findViewById(R.id.TopicName);

        tv.setText(MainActivity.tn);
        pr=new pro(false, tv.getText().toString());
        Button Next=(Button) findViewById(R.id.SolveNextQuest);
        Button Previous=(Button) findViewById(R.id.SolvePreQuest);
        Button Save=(Button) findViewById(R.id.Submit);
        Topic =(TextView) findViewById(R.id.TopicName);
        QuestNo=findViewById(R.id.SolvingQuest);
        answers=(RadioGroup)findViewById(R.id.Answers);
        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.AA:
                        ans="A";
                        break;
                    case R.id.AB:
                        ans="B";
                        break;
                    case R.id.AC:
                        ans="C";
                        break;
                    case R.id.AD:
                        ans="D";
                        break;
                    default:
                        ans=0+"";
                        break;


                }
            }
        });
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

                pr.checkAns();
                Toast.makeText(getApplicationContext(),"Sumbit"+"",Toast.LENGTH_LONG).show();
                Intent i=new Intent(SolvePage.this,anspage.class);
                startActivity(i);
                return true;
            }
        });
    }
    void SetAns(Boolean b){
        int q=Integer.parseInt(QuestNo.getText().toString());
        pr.SetAnsList(q,ans);
        if(b){
            q=q+1;
        }else {
            q=q-1;

        }
        QuestNo.setText(q+"");
        answers.clearCheck();

        String s=pr.AnsL.get(q).toString();
        switch (s){
            case "A":
                answers.check(R.id.AA);
                break;
            case "B":
                answers.check(R.id.AB);
                break;
            case "C":
                answers.check(R.id.AC);
                break;
            case "D":
                answers.check(R.id.AD);
                break;
            default:
                answers.clearCheck();
                break;


        }
        Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
    }
}
