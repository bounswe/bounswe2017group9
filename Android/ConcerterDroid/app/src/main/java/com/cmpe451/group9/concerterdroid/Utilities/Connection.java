package com.cmpe451.group9.concerterdroid.Utilities;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Necip on 18.11.2017.
 */

public class Connection {


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        stext = (EditText) findViewById(R.id.searchT);
//        sbutton = (Button) findViewById(R.id.searchB);
//
//
//        sbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String head = stext.getText().toString();
//
//                USGS_REQUEST_URL =
//                "https://content.guardianapis.com/search?api-key=48584b11-c96f-439d-9956-4f83240925b0"
//                +"&q="+head+"&page-size=1&show-fields=body,headline";
//
//                TextView dHead = (TextView) findViewById(R.id.dateH);
//                dHead.setVisibility(View.VISIBLE);
//                TextView nHead = (TextView) findViewById(R.id.newsH);
//                nHead.setVisibility(View.VISIBLE);
//
//                // Kick off an {@link AsyncTask} to perform the network request
//                TsunamiAsyncTask task = new TsunamiAsyncTask();
//                task.execute();
//            }
//        });
//
//
//
//    }
//
//
//    /**
//     * Update the screen to display information from the given {@link Event}.
//     */
//    private void updateUi(Event earthquake) {
//        // Display the earthquake title in the UI
//        TextView titleTextView = (TextView) findViewById(R.id.title);
//        titleTextView.setText(earthquake.title);
//
//        // Display the earthquake date in the UI
//        TextView dateTextView = (TextView) findViewById(R.id.date);
//        dateTextView.setText(earthquake.time);
//
//        // Display whether or not there was a tsunami alert in the UI
//        TextView tsunamiTextView = (TextView) findViewById(R.id.tsunami_alert);
//        tsunamiTextView.setText(Html.fromHtml(earthquake.tsunamiAlert));
//        //Html.fromHtml("<h2>Title</h2><br><p>Description here</p>")
//    }
//
//
//
//
//    /**
//     * {@link AsyncTask} to perform the network request on a background thread, and then
//     * update the UI with the first earthquake in the response.
//     */
//    private class TsunamiAsyncTask extends AsyncTask<URL, Void, Event> {
//        //---------------------------------------------------------------------------------------------------------------------------
//        @Override
//        protected Event doInBackground(URL... urls) {
//            // Create URL object
//            URL url = createUrl(USGS_REQUEST_URL);
//
//            // Perform HTTP request to the URL and receive a JSON response back
//            String jsonResponse = "";
//            try {
//                jsonResponse = makeHttpRequest(url);
//            } catch (IOException e) {
//                Log.e(LOG_TAG, "HttpRequest exception", e);
//            }
//
//            // Extract relevant fields from the JSON response and create an {@link Event} object
//            Event earthquake = extractFeatureFromJson(jsonResponse);
//
//            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
//            return earthquake;
//        }
//
//        /**
//         * Update the screen with the given earthquake (which was the result of the
//         * {@link TsunamiAsyncTask}).
//         */
//        @Override
//        protected void onPostExecute(Event earthquake) {
//            if (earthquake == null) {
//                return;
//            }
//
//            updateUi(earthquake);
//        }
//
//        /**
//         * Returns new URL object from the given string URL.
//         */
//        private URL createUrl(String stringUrl) {
//            URL url = null;
//            try {
//                url = new URL(stringUrl);
//            } catch (MalformedURLException exception) {
//                Log.e(LOG_TAG, "Error with creating URL", exception);
//                return null;
//            }
//            return url;
//        }
//
//        /**
//         * Make an HTTP request to the given URL and return a String as the response.
//         */
//        private String makeHttpRequest(URL url) throws IOException {
//            String jsonResponse = "";
//            HttpURLConnection urlConnection = null;
//            InputStream inputStream = null;
//            try {
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.setReadTimeout(10000 /* milliseconds */);
//                urlConnection.setConnectTimeout(15000 /* milliseconds */);
//                urlConnection.connect();
//                inputStream = urlConnection.getInputStream();
//                jsonResponse = readFromStream(inputStream);
//            } catch (IOException e) {
//                Log.e(LOG_TAG, "Inside HttpRequest exception", e);
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (inputStream != null) {
//                    // function must handle java.io.IOException here
//                    inputStream.close();
//                }
//            }
//            return jsonResponse;
//        }
//
//        /**
//         * Convert the {@link InputStream} into a String which contains the
//         * whole JSON response from the server.
//         */
//        private String readFromStream(InputStream inputStream) throws IOException {
//            StringBuilder output = new StringBuilder();
//            if (inputStream != null) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
//                BufferedReader reader = new BufferedReader(inputStreamReader);
//                String line = reader.readLine();
//                while (line != null) {
//                    output.append(line);
//                    line = reader.readLine();
//                }
//            }
//            return output.toString();
//        }
//
//        /**
//         * Return an {@link Event} object by parsing out information
//         * about the first earthquake from the input earthquakeJSON string.
//         */
//        private Event extractFeatureFromJson(String earthquakeJSON) {
//            try {
//                JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
//                JSONObject tempy = baseJsonResponse.getJSONObject("response");
//                JSONArray results = tempy.getJSONArray("results");
//
//                // If there are results in the features array
//                if (results.length() > 0) {
//                    // Extract out the first feature (which is an earthquake)
//                    JSONObject newsfeed = results.getJSONObject(0);
//
//
//
//
//                    JSONObject fields = newsfeed.getJSONObject("fields");
//
//                    // Extract out the title, time, and tsunami values
//                    String title = fields.getString("headline");
//                    String time = newsfeed.getString("webPublicationDate").substring(0,10);
//                    String tsunamiAlert = fields.getString("body");
//
//                    // Create a new {@link Event} object
//                    return new Event(title, time, tsunamiAlert);
//                }
//            } catch (JSONException e) {
//                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
//            }
//            return null;
//        }
//    }

}
