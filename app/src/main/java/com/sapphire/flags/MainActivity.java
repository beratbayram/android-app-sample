package com.sapphire.flags;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Intent intent;
    EditText name;
    EditText passwd;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Flags (by Berat BAYRAM)");
        name = findViewById(R.id.login_box_name);
        passwd = findViewById(R.id.login_box_password);

        sharedPreferences=getSharedPreferences("prefs",Context.MODE_PRIVATE);
    }

    public void signUpFunction(View view) {

        boolean flag=false;

        if(name.getText().toString().equals("") ||
                name.getText().toString().equals(" ") ||
                name.getText().toString().equals("  ") ||
                passwd.getText().toString().equals("") ||
                passwd.getText().toString().equals(" ") ||
                passwd.getText().toString().equals("  "))
        {
            TextView invalid;
            invalid = findViewById(R.id.invalid_sign_in);
            invalid.setVisibility(View.GONE);
            invalid = findViewById(R.id.same_username);
            invalid.setVisibility(View.GONE);
            invalid= findViewById(R.id.empty_field);
            invalid.setVisibility(View.VISIBLE);
        }else {
            for (int i = 1; i <= sharedPreferences.getInt("userCounter", 0); i++)
                if (name.getText().toString().equals(sharedPreferences.getString("username" + Integer.toString(i), "Null")))
                    flag = true;

            if (flag) {
                TextView invalid;
                invalid= findViewById(R.id.empty_field);
                invalid.setVisibility(View.GONE);
                invalid = findViewById(R.id.invalid_sign_in);
                invalid.setVisibility(View.GONE);
                invalid = findViewById(R.id.same_username);
                invalid.setVisibility(View.VISIBLE);
            } else {
                intent = new Intent(this, Welcome.class);
                intent.putExtra("username", name.getText().toString());
                intent.putExtra("password", passwd.getText().toString());
                intent.putExtra("isNew", true);

                startActivity(intent);
            }
        }
    }

    public void signInFunction(View view){

        boolean flag=true;

        for(int i=1;i<=sharedPreferences.getInt("userCounter",0);i++){

            String username=sharedPreferences.getString("username"+Integer.toString(i),"NO_USER");

            if(username.equals("NO_USER")){
              Intent intent1 = new Intent(this,Err.class);
              intent1.putExtra("err_message","NO_USER");
              startActivity(intent1);
            }

            String password=sharedPreferences.getString("password"+Integer.toString(i),"NO_PASSWORD");
            if(password.equals("NO_PASSWORD")){
                Intent intent1 = new Intent(this,Err.class);
                intent1.putExtra("err_message","NO_PASSWORD");
                startActivity(intent1);
            }

            if (username.equals(name.getText().toString()) && password.equals(passwd.getText().toString())) {
                intent = new Intent(this, Welcome.class);

                intent.putExtra("username", name.getText().toString());
                intent.putExtra("password", passwd.getText().toString());
                intent.putExtra("isNew", false);
                intent.putExtra("userID",i);

                flag=false;
                startActivity(intent);
            }
        }
        if(flag){
            TextView invalid;
            invalid= findViewById(R.id.empty_field);
            invalid.setVisibility(View.GONE);
            invalid = findViewById(R.id.same_username);
            invalid.setVisibility(View.GONE);
            invalid = findViewById(R.id.invalid_sign_in);
            invalid.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
