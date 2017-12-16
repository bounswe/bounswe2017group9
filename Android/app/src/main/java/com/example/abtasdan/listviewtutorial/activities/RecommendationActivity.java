package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RecommendationActivity extends Activity {

    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @BindView(R.id.lv_recomendation)
    ListView recomendationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
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

    private void refreshItems() {
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);
        int userId = 0;
        if (restoredText != null) {

            int id = prefs.getInt("idName", 0); //0 is the default value.
            userId = id;

        }

        concertifyApiRequest.getRecommend(userId, new Callback<ArrayList<Concert>>() {
            @Override
            public void success(ArrayList<Concert> concerts, Response response) {

                ConcertAdapter concertAdapter = new ConcertAdapter(RecommendationActivity.this, concerts, false);
                recomendationList.setAdapter(concertAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

                error.printStackTrace();

            }
        });
    }
    @OnClick(R.id.tv_explore_recom)
    public void onClickExplore(){
       refreshItems();
    }
    @OnClick(R.id.tv_home_recom)
    public void onClickHome(){
        finish();
        Intent intent = new Intent(RecommendationActivity.this,MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_logout_recom)
    public void onClickLogout(){
        finish();
        Intent intent = new Intent(RecommendationActivity.this,UnregisteredMainPageActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_profile_recom)
    public void onClickProfile(){
        finish();
        Intent intent = new Intent(RecommendationActivity.this,ProfileActivity.class);
        startActivity(intent);
    }
}
