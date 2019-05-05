package com.example.serverconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Recommendation extends AppCompatActivity {
    private ConnectServer cntsvr;
    private TextView[] textViewMovie;

    private int DisplayCpacity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        DisplayCpacity = 5;
        cntsvr = new ConnectServer();
        textViewMovie = new TextView[DisplayCpacity];

        // Get the Intent that started this activity and extract the string
        textViewMovie[0] = findViewById(R.id.eTRed1);
        textViewMovie[1] = findViewById(R.id.eTRed2);
        textViewMovie[2] = findViewById(R.id.eTRed3);
        textViewMovie[3] = findViewById(R.id.eTRed4);
        textViewMovie[4] = findViewById(R.id.eTRed5);
        sendQuery("demo");
    }

    public void sendQuery(String query) {
        cntsvr.SendRecommendationData(query,2,this);
    }

    public void ReceivedQueryResult(JSONObject ReceivedJSonObj){
        DisplayQueryResult(ReceivedJSonObj);
    }
    public void DisplayQueryResult(JSONObject ReceivedJSonObj){
        try {
            JSONArray arrJsonMovie = ReceivedJSonObj.getJSONArray("Movie");
            String Moviearr[] = new String[arrJsonMovie.length()];
            int totalResult = arrJsonMovie.length();
            int loopcount = totalResult;
            if (totalResult > DisplayCpacity){
                loopcount = DisplayCpacity;
            }

            //textViewMovie1.setText(message);
            for(int i = 0; i < loopcount; i++) {
                Moviearr[i] = arrJsonMovie.getString(i);
                Log.d("Movie", Moviearr[i]);
                textViewMovie[i].setText(Moviearr[i]);
                //HighlightQueryTerm(textViewMoviedes[i]);
            }
        } catch (JSONException e) {
            Log.d("No Movie", "check code " );
        }

    }

}
