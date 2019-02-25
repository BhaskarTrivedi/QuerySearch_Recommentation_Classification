package com.example.serverconnection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.SearchQuery.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void SearchQuery(View view) {
        Intent intent = new Intent(this, SearchResult.class);
        EditText editText = (EditText) findViewById(R.id.edTextSearchAct);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}

