package com.sapphire.flags;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Err extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_err);

        Intent intent=getIntent();
        TextView textView=findViewById(R.id.err_message);
        textView.setText(intent.getStringExtra("err_message"));
    }
}
