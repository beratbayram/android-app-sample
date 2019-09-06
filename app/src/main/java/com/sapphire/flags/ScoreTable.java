package com.sapphire.flags;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ScoreTable extends AppCompatActivity {

    int i;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);

        sharedPreferences=getSharedPreferences("prefs", Context.MODE_PRIVATE);
        ArrayList<String> list = new ArrayList<>();

        for(i=1;i<=sharedPreferences.getInt("userCounter",0);i++)
            list.add(sharedPreferences.getString("username"+Integer.toString(i),"Null")
                    + ": " +
                    sharedPreferences.getInt("score"+Integer.toString(i),0));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                return view;
            };
        };

        ListView listView=findViewById(R.id.username_list);
        listView.setAdapter(adapter);
    }
    public void clearScores(View view){
        sharedPreferences.edit().clear().commit();
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
