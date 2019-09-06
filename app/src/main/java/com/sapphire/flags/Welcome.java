package com.sapphire.flags;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    Integer userID;
    int userCounter;
    String name;
    String username;
    String password;
    Integer score = 0;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        userID = intent.getIntExtra("userID",0);

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        score = sharedPreferences.getInt("score" + Integer.toString(userID), 0);
        userCounter = sharedPreferences.getInt("userCounter", 0);

        if (intent.getBooleanExtra("isNew", false)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            userCounter++;
            editor.putInt("userCounter", userCounter);
            editor.putString("username" + Integer.toString(userCounter), username);
            editor.putString("password" + Integer.toString(userCounter), password);
            editor.putInt("score" + Integer.toString(userCounter), 0);
            editor.apply();
            userID=userCounter;
        }

        score = sharedPreferences.getInt("score" + Integer.toString(userID), 0);


        TextView textView = findViewById(R.id.welcome);
        textView.setText(username + "\n\n Score: " + Integer.toString(score));

        setTitle(username);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void nextQuestion(View view) {

        Intent intent = new Intent(this, Question1.class);
        intent.putExtra("username", username);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        TextView textView2=findViewById(R.id.fun_fact);
        textView2.setVisibility(View.GONE);

        Button button=findViewById(R.id.button_start);
        button.setText(R.string.start_again);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                username = data.getStringExtra("username");
                score = data.getIntExtra("score", 0);

                TextView textView = findViewById(R.id.welcome);
                textView.setText(username + "\n\n Score:" + Integer.toString(score));

                sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("score" + Integer.toString(userID), score);
                editor.apply();

                if(sharedPreferences.getBoolean("fun_fact",false)) {
                    editor.putBoolean("fun_fact", false);
                    editor.apply();
                    textView2.setVisibility(View.VISIBLE);
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                Intent intent = new Intent(this, Err.class);
                intent.putExtra("err_message", "onActivityResult");
                startActivity(intent);
            }
        }
    }

    public void showScoreTable(View view) {
        Intent intent = new Intent(this, ScoreTable.class);
        intent.putExtra("username", username);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
