package com.example.serverconnection;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectServer {

    //reason for 10.0.2.2 is https://developer.android.com/studio/run/emulator-networking
    SearchResult SearchResultObj;
    int Action = 0;
    String SearchURL = "http://10.0.2.2:5000/search/";
    JSONObject searchResponse;
    //String SearchURL = "http://stackoverflow.com";
    String SearchData;
    InputStream searchin;
    String fullresponse;
    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String ResponseData =  getResponseFromServer(strings[0]);
            Log.d("doInBackgroundReceived", ResponseData );
            return ResponseData;
        }

        @Override
        protected void onPostExecute(String Response){
            if (Response != null && Response.compareTo("OK") == 0) {
                Log.d("Received", new String(Integer.toString(Response.length())) );
                fullresponse = readStream(searchin);
                ReadReceivedJson();
            }
            else {
                Log.d("Response ", "Response Failed onPostExecute");
            }
        }
    }

    public void SendSearchData(String data,int Act,SearchResult SearchResultObj){
        SearchData = data;
        this.Action = Act;
        Log.d("SearchQuery ", data);
        if (Action == 0) {
            SearchURL = "http://10.0.2.2:5000/search/";
            this.SearchResultObj = SearchResultObj;
        }
        new HTTPAsyncTask().execute(SearchURL);
    }

    private String getResponseFromServer(String targetUrl){
        try {
            //creating Http URL connection
            URL url = new URL(targetUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            //building json object
            JSONObject jsonObject ;
            if(Action ==0){
                //Creating content to sent to server
                jsonObject = CreateSearchJson();
            }
            else {
                jsonObject = CreateSearchJson();
            }

            CreateDatatoSend(urlConnection, jsonObject);
            //making POST request to URl
            urlConnection.connect();
            //return response message
            return urlConnection.getResponseMessage();

        } catch (MalformedURLException e) {
            Log.d("JCF","URL failed");
        } catch (IOException e) {
            Log.d("JCF","IO Exception getResponseFromServer");;
        }
        return null;
    }

    private JSONObject CreateSearchJson() {

        JSONObject jsonsearchObject = new JSONObject();
        try{
            jsonsearchObject.put("searchString",SearchData);
            return jsonsearchObject;
        }catch (JSONException e){
            Log.d("JCF","Can't format JSON OBject class: Connect servet Method : CreateSearchJson");
        }
        return null;
    }

    private void CreateDatatoSend(HttpURLConnection urlConnection,JSONObject JObject){
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            Log.d("JCF",JObject.toString());
            OutputStream os = new BufferedOutputStream(urlConnection.getOutputStream());

            os.write(JObject.toString().getBytes());
            os.flush();
            searchin = new BufferedInputStream(urlConnection.getInputStream());

            os.close();
        } catch (IOException e) {
            Log.d("JCF","set Post failed CreateDatatoSend");
        }

    }

    private String readStream(InputStream in){
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = 0;
        try {
            result = bis.read();
            while(result != -1) {
                byte b = (byte)result;
                buf.write(b);
                result = bis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buf.toString();
    }

    private void ReadReceivedJson(){
        Log.d("Full Received", fullresponse );
        try {
            searchResponse = new JSONObject(fullresponse);
            if (Action == 0){
                SearchResultObj.ReceivedQueryResult(searchResponse);
            }
        } catch (JSONException e) {
            Log.d("Received Joson Exp", e.toString());
        }

        /*
        try {
            JSONArray arrJson = searchResponse.getJSONArray("Movie");
            String Moviearr[] = new String[arrJson.length()];
            for(int i = 0; i < arrJson.length(); i++) {
                Moviearr[i] = arrJson.getString(i);
                Log.d("Movie", Moviearr[i]);
            }
        } catch (JSONException e) {
            Log.d("No Movie", fullresponse );
        }
        */

    }
}
