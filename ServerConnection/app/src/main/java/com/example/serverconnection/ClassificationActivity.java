package com.example.serverconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassificationActivity extends AppCompatActivity {

    private ConnectServer cntsvr;
    private int DisplayCpacity;

    private TextView[] textViewMovie;
    private TextView[] textViewMovieClsf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        cntsvr = new ConnectServer();
        DisplayCpacity = 3;
        textViewMovie = new TextView[DisplayCpacity];
        textViewMovieClsf = new TextView[DisplayCpacity];
        // Get the Intent that started this activity and extract the string
        textViewMovie[0] = findViewById(R.id.editTextMovie1);
        textViewMovie[1] = findViewById(R.id.editTextmovie2);
        textViewMovie[2] = findViewById(R.id.editTextmovie3);
        textViewMovieClsf[0] = findViewById(R.id.editTextclassification1);
        textViewMovieClsf[1] = findViewById(R.id.editTextclassification2);
        textViewMovieClsf[2] = findViewById(R.id.editTextclassification3);

        sendQuery();
    }

    public void sendQuery() {
        cntsvr.SendClassificationData("Classification Action",1,this);
    }

    public void ReceivedQueryResult(JSONObject ReceivedJSonObj){
        DisplayQueryResult(ReceivedJSonObj);
    }

    public void DisplayQueryResult(JSONObject ReceivedJSonObj){
        try {
            JSONArray arrJsonMovie = ReceivedJSonObj.getJSONArray("Movie");
            String Moviearr[] = new String[arrJsonMovie.length()];

            JSONArray arrJsonMovDes = ReceivedJSonObj.getJSONArray("Class");
            String MovieClsarr[] = new String[arrJsonMovDes.length()];
            int totalResult = arrJsonMovie.length();
            int loopcount = totalResult;
            if (totalResult > DisplayCpacity){
                loopcount = DisplayCpacity;
            }

            //textViewMovie1.setText(message);
            for(int i = 0; i < loopcount; i++) {
                Moviearr[i] = arrJsonMovie.getString(i);
                MovieClsarr[i] = arrJsonMovDes.getString(i);
                Log.d("Movie", Moviearr[i]);
                Log.d("Classification", MovieClsarr[i]);
                textViewMovie[i].setText(Moviearr[i]);
                textViewMovieClsf[i].setText(MovieClsarr[i]);
                //HighlightQueryTerm(textViewMoviedes[i]);
            }
        } catch (JSONException e) {
            Log.d("No Movie", "check code " );
        }
    }
}
