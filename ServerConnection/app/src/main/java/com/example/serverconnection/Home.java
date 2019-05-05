package com.example.serverconnection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }



    public void SearchQuery(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Query Serch Selected";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void Classification(View view){
        Context context = getApplicationContext();
        CharSequence text = "Classification Selected";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent = new Intent(this, ClassificationActivity.class);
        startActivity(intent);
    }

    public void Recommendation(View view){
        Context context = getApplicationContext();
        CharSequence text = "Recommendation Selected";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent = new Intent(this, Recommendation.class);
        startActivity(intent);
    }

}
