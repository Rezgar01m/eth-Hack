package com.ride.bhack.ui;

import android.os.AsyncTask;
import android.util.Log;

import com.ride.bhack.HttpHandler;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mplacona on 4/10/15.
 */
public class AsyncHttpTask extends AsyncTask<String, Void, String> {
    private HttpHandler httpHandler;
    public AsyncHttpTask(HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make the http request
            HttpResponse httpResponse = httpclient.execute(httpHandler.getHttpRequestMethod());

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        httpHandler.onResponse(result);
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }
}