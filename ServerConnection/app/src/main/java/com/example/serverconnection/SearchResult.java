package com.example.serverconnection;

import android.content.Context;
import android.content.Intent;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchResult extends AppCompatActivity {

    private ConnectServer cntsvr;
    private TextView[] textViewMovie;
    private TextView[] textViewMoviedes;
    private int DisplayCpacity;
    private String[] token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        DisplayCpacity = 3;
        cntsvr = new ConnectServer();
        textViewMovie = new TextView[DisplayCpacity];
        textViewMoviedes = new TextView[DisplayCpacity];
        // Get the Intent that started this activity and extract the string
        textViewMovie[0] = findViewById(R.id.edTxtMovieName1);
        textViewMovie[1] = findViewById(R.id.edTxtMovieName2);
        textViewMovie[2] = findViewById(R.id.edTxtMovieName3);
        textViewMoviedes[0] = findViewById(R.id.edTxtMovieDes1);
        textViewMoviedes[1] = findViewById(R.id.edTxtMovieDes2);
        textViewMoviedes[2] = findViewById(R.id.edTxtMovieDes3);
        Intent intent = getIntent();
        String message = intent.getStringExtra(SearchActivity.EXTRA_MESSAGE);
        token = message.split(" ");
        sendQuery(message);
    }

    public void sendQuery(String query) {
        cntsvr.SendSearchData(query,0,this);
    }

    public void ReceivedQueryResult(JSONObject ReceivedJSonObj){
        DisplayQueryResult(ReceivedJSonObj);
    }

    public void DisplayQueryResult(JSONObject ReceivedJSonObj){
        try {
            JSONArray arrJsonMovie = ReceivedJSonObj.getJSONArray("Movie");
            String Moviearr[] = new String[arrJsonMovie.length()];

            JSONArray arrJsonMovDes = ReceivedJSonObj.getJSONArray("Description");
            String MovieDesarr[] = new String[arrJsonMovDes.length()];
            int totalResult = arrJsonMovie.length();
            int loopcount = totalResult;
            if (totalResult > DisplayCpacity){
                loopcount = DisplayCpacity;
            }

            //textViewMovie1.setText(message);
            for(int i = 0; i < loopcount; i++) {
                Moviearr[i] = arrJsonMovie.getString(i);
                MovieDesarr[i] = arrJsonMovDes.getString(i);
                Log.d("Movie", Moviearr[i]);
                Log.d("Description", MovieDesarr[i]);
                textViewMovie[i].setText(Moviearr[i]);
                textViewMoviedes[i].setText(MovieDesarr[i]);
                //HighlightQueryTerm(textViewMoviedes[i]);
            }
        } catch (JSONException e) {
            Log.d("No Movie", "check code " );
        }

    }

    public void HighlightQueryTerm(TextView HighlightText){
        for (int index = 0;index<token.length;index++) {
            Log.d("Highlight Term", token[index] );
            String replacedWith = "<font color ='red'>" + token[index].toLowerCase() + "</font>";
            String originalString = HighlightText.getText().toString();
            String modifiedString = originalString.replaceAll(token[index].toLowerCase(),replacedWith.toLowerCase());
            HighlightText.setText(Html.fromHtml(modifiedString, HtmlCompat.FROM_HTML_MODE_LEGACY));
        }
    }
}
