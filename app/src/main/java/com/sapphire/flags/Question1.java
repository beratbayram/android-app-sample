package com.sapphire.flags;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class Question1 extends AppCompatActivity {

    int level,score=0;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        Intent intent=getIntent();
        username = intent.getStringExtra("username");

        level =1;
        setTitle("Level: 1 | Score: 0");
    }

    public void falseAnswer(View view){

        RadioGroup radioGroup;
        ImageView imageView= findViewById(R.id.image);

        switch (level){
            case 1: //japan
                radioGroup = findViewById(R.id.Question_1);
                radioGroup.setVisibility(View.INVISIBLE);

                imageView.setImageResource(R.drawable.congo);

                radioGroup = findViewById(R.id.Question_2);
                radioGroup.setVisibility(View.VISIBLE);
                break;

            case 2: //congo
                radioGroup = findViewById(R.id.Question_2);
                radioGroup.setVisibility(View.INVISIBLE);

                imageView.setImageResource(R.drawable.estonia);

                radioGroup = findViewById(R.id.Question_3);
                radioGroup.setVisibility(View.VISIBLE);
                break;

            case 3://estonia
                radioGroup = findViewById(R.id.Question_3);
                radioGroup.setVisibility(View.INVISIBLE);

                imageView.setImageResource(R.drawable.mongolia);

                radioGroup = findViewById(R.id.Question_4);
                radioGroup.setVisibility(View.VISIBLE);
                break;

            case 4://mongolia
                radioGroup = findViewById(R.id.Question_4);
                radioGroup.setVisibility(View.INVISIBLE);

                imageView.setImageResource(R.drawable.vatican);

                radioGroup = findViewById(R.id.Question_5);
                radioGroup.setVisibility(View.VISIBLE);
                break;

            case 5://vatican
                Intent intent= new Intent();
                intent.putExtra("username",username);
                intent.putExtra("score",score);
                setResult(Activity.RESULT_OK,intent);
                finish();
        }
        if(++level>5)
            setTitle("Finished | Score: " + Integer.toString(score));
        else
            setTitle("Level: " + Integer.toString(level) + " | Score: " + Integer.toString(score));
    }
    public void trueAnswer(View view){
        score += 20;
        if(level == 5){

            SharedPreferences sharedPreferences=getSharedPreferences("prefs",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("fun_fact", true);
            editor.apply();
        }
        falseAnswer(view);
    }
    @Override
    public void onBackPressed() {

        Intent intent= new Intent();
        intent.putExtra("username",username);
        intent.putExtra("score",0);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
