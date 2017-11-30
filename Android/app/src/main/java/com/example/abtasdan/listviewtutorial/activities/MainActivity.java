package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.adapters.ConcertAdapter;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.requests.SearchResult;
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


public class MainActivity extends Activity {

    @BindView(R.id.lv_concerts)
    ListView lvConcerts;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_search)
    Button search;

    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @OnClick(R.id.tv_home)
    public void refresh() {
        refreshItems();
    }

    private void refreshItems() {
        int userId = 7;
        concertifyApiRequest.getConcerts(userId, new Callback<ArrayList<Concert>>() {
            @Override
            public void success(ArrayList<Concert> concerts, Response response) {
                ConcertAdapter concertAdapter = new ConcertAdapter(MainActivity.this, concerts, false);
                lvConcerts.setAdapter(concertAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

                error.printStackTrace();

            }
        });
    }

    private void search(String searchKey) {
        concertifyApiRequest.search(searchKey, new Callback<SearchResult>() {
            @Override
            public void success(SearchResult searchResult, Response response) {

                ConcertAdapter concertAdapter = new ConcertAdapter(MainActivity.this, searchResult.getConcerts(), false);
                lvConcerts.setAdapter(concertAdapter);


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @OnClick(R.id.btn_search)
    public void onClickSerch(){
       if(etSearch.getText().toString().isEmpty()){
           tvTitle.setText("Concerts");
       }else{
           tvTitle.setText("Search");
           search(etSearch.getText().toString());

       }
    }
    @OnClick(R.id.tv_profile_main)
    public void onClickProfile(){
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_explore_main)
    public void onClickExplore(){
        Intent intent = new Intent(MainActivity.this,RecommendationActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_logout)
    public void onViewClicked() {
        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();

        editor.putString("name",null);

        editor.apply();

        Intent intent = new Intent(MainActivity.this, UnregisteredMainPageActivity.class);
        startActivity(intent);
        finish();

    }
}
