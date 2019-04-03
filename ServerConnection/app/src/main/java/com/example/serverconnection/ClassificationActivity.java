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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        cntsvr = new ConnectServer();
        textViewMovie = new TextView[1];
        textViewMovieProb = new TextView[1];
        textViewMovie[0] = findViewById(R.id.editTextClass);
        textViewMovieProb[0] = findViewById(R.id.editTextProbablity);
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
        EditText editText = (EditText) findViewById(R.id.editTextQuery);
        String message = editText.getText().toString();
        sendQuery(message);
    }

}
