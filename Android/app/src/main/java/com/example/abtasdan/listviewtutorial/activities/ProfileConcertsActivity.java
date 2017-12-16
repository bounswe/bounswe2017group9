package com.example.abtasdan.listviewtutorial.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.adapters.ConcertAdapter;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.requests.AttendConcertReq;
import com.example.abtasdan.listviewtutorial.requests.requests.ConcertifyApiRequest;
import com.example.abtasdan.listviewtutorial.requests.requests.RetrofitHttpClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class ProfileConcertsActivity extends AppCompatActivity {
    @BindView(R.id.lv_profile_concerts1)
    ListView lvConcerts;
    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_concerts);
        ButterKnife.bind(this);


        RetrofitHttpClient client = new RetrofitHttpClient();


        restAdapter = new RestAdapter.Builder()
                .

                        setClient(client)

                .

//                        setErrorHandler(new MyErrorHandler()
//
//                        )
//                .

//                        setRequestInterceptor(requestInterceptor)
//
//                .

//                        setConverter(new JacksonConverter()
//
//                        ).

//                        setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
//
//                .

        setLogLevel(RestAdapter.LogLevel.FULL)

                .

                        setLog(new AndroidLog("YOUR_LOG_TAG")

                        )
                .

                        setEndpoint(getString(R.string.base_url)

                        )
                .

                        build();


        concertifyApiRequest = restAdapter.create(ConcertifyApiRequest.class);
        refreshItems();
    }
    public void refreshItems(){
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);
        int userId = 0;
        if (restoredText != null) {
            userId = prefs.getInt("idName", 0); //0 is the default value.
        }

        AttendConcertReq attendConcertReq=new AttendConcertReq();
        attendConcertReq.setStatus("attending");
        attendConcertReq.setUser_id(userId);
        concertifyApiRequest.getAttendConcerts(attendConcertReq, new Callback<ArrayList<Concert>>() {
            @Override
            public void success(ArrayList<Concert> concerts, Response response) {

                ConcertAdapter concertAdapter = new ConcertAdapter(ProfileConcertsActivity.this, concerts, false);
                lvConcerts.setAdapter(concertAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

                error.printStackTrace();

            }
        });
    }
}
