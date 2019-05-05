package com.example.serverconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassificationActivity extends AppCompatActivity {
    private ConnectServer cntsvr;
    private TextView[] textViewMovie;
    private TextView[] textViewMovieProb;
    private int DisplayCpacity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);
        DisplayCpacity = 3;
        cntsvr = new ConnectServer();
        textViewMovie = new TextView[DisplayCpacity];;
        textViewMovieProb = new TextView[DisplayCpacity];;
        textViewMovie[0] = findViewById(R.id.editTextMovie1);
        textViewMovie[1] = findViewById(R.id.editTextMovie2);
        textViewMovie[2] = findViewById(R.id.editTextMovie3);
        textViewMovieProb[0] = findViewById(R.id.editTextprobablity1);
        textViewMovieProb[1] = findViewById(R.id.editTextprobablity2);
        textViewMovieProb[2] = findViewById(R.id.editTextprobablity3);
    }

    public void sendQuery(String query) {
        cntsvr.SendClassificationData(query,1,this);
    }

    public void ReceivedQueryResult(JSONObject ReceivedJSonObj){
        DisplayQueryResult(ReceivedJSonObj);
    }

    public void DisplayQueryResult(JSONObject ReceivedJSonObj){
        try{
            JSONArray arrJsonMovie = ReceivedJSonObj.getJSONArray("Movie");
            String Moviearr[] = new String[arrJsonMovie.length()];

            JSONArray arrJsonMovDes = ReceivedJSonObj.getJSONArray("Prabablity");
            String MovieProbablity[] = new String[arrJsonMovDes.length()];

            int totalResult = arrJsonMovie.length();
            for(int i = 0; i < totalResult; i++) {
                Moviearr[i] = arrJsonMovie.getString(i);
                MovieProbablity[i] = arrJsonMovDes.getString(i);
                Log.d("Movie", Moviearr[i]);
                Log.d("Prapablity", MovieProbablity[i]);
                textViewMovie[i].setText(Moviearr[i]);
                textViewMovieProb[i].setText(MovieProbablity[i]);
                //HighlightQueryTerm(textViewMoviedes[i]);
            }

        }catch (JSONException e) {
            Log.d("No Movie", "check code " );
        }

    }

    public void ClassificationQuery(View view){
        EditText editText = (EditText) findViewById(R.id.editTextquery);
        String message = editText.getText().toString();
        sendQuery(message);
    }

}
