package com.example.abtasdan.listviewtutorial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.adapters.ConcertAdapter;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.requests.requests.ConcertifyApiRequest;
import com.example.abtasdan.listviewtutorial.requests.requests.RetrofitHttpClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class UnregisteredMainPageActivity extends AppCompatActivity {



    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R.id.lv_concerts_unregistered)
    ListView lvConcerts;
    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unregistered_main_page);
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


    @OnClick(R.id.btn_login)
    public void clickLogin(){
        Intent intent = new Intent(UnregisteredMainPageActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_sign_up)
    public void clickSignUp(){
        Intent intent = new Intent(UnregisteredMainPageActivity.this,SignUpActivity.class);
        startActivity(intent);

    }
    private void refreshItems() {
        int userId = 7;
        concertifyApiRequest.getConcerts(userId, new Callback<ArrayList<Concert>>() {
            @Override
            public void success(ArrayList<Concert> concerts, Response response) {
                ConcertAdapter concertAdapter = new ConcertAdapter(UnregisteredMainPageActivity.this, concerts, false);
                lvConcerts.setAdapter(concertAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

                error.printStackTrace();

            }
        });
    }





}
