package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.abtasdan.listviewtutorial.Concert;
import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.adapters.ConcertAdapter;
import com.example.abtasdan.listviewtutorial.requests.requests.ConcertifyApiRequest;
import com.example.abtasdan.listviewtutorial.requests.requests.JacksonConverter;
import com.example.abtasdan.listviewtutorial.requests.requests.RetrofitHttpClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class MainActivity extends Activity {

    @BindView(R.id.lv_concerts)
    ListView lvConcerts;
    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<Concert> concerts = new ArrayList<>();
        concerts.add(new Concert("Buğra","","12","","","https://vikolo.com/wp-content/uploads/2017/03/Lana-Del-Rey.jpg",12,13));
        concerts.add(new Concert("Serdar","","13","","","https://vikolo.com/wp-content/uploads/2017/03/Lana-Del-Rey.jpg",12,13));
        concerts.add(new Concert("Hazel","","14","","","https://vikolo.com/wp-content/uploads/2017/03/Lana-Del-Rey.jpg",12,13));


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

    private void refreshItems() {
        int userId=7;
        concertifyApiRequest.getConcerts(userId, new Callback<ArrayList<Concert>>() {
            @Override
            public void success(ArrayList<Concert> concerts, Response response) {
                ConcertAdapter concertAdapter = new ConcertAdapter(MainActivity.this,concerts,false);
                lvConcerts.setAdapter(concertAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
