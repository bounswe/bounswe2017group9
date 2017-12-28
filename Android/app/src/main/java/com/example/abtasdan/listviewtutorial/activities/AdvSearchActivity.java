package com.example.abtasdan.listviewtutorial.activities;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.adapters.ConcertAdapter;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.requests.AdvReq;
import com.example.abtasdan.listviewtutorial.modals.requests.SearchResult;
import com.example.abtasdan.listviewtutorial.requests.requests.ConcertifyApiRequest;
import com.example.abtasdan.listviewtutorial.requests.requests.RetrofitHttpClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

import static com.example.abtasdan.listviewtutorial.activities.MainActivity.MyPREFERENCES;

public class AdvSearchActivity extends Activity {

    @BindView(R.id.tv_home_adv)
    TextView tvHome;
    @BindView(R.id.tv_logout_adv)
    TextView tvLogout;
    @BindView(R.id.tv_title_adv)
    TextView tvTitle;
    @BindView(R.id.bt_advsearch)
    Button bt_search;
    @BindView(R.id.edit_location)
    EditText locatio;
    @BindView(R.id.edit_location_name)
    EditText locatio_name;
    @BindView(R.id.edit_minprice)
    EditText minprice;
    @BindView(R.id.edit_maxprice)
    EditText maxprice;
    @BindView(R.id.edit_start)
    EditText startDate;
    @BindView(R.id.edit_end)
    EditText endDate;
    @BindView(R.id.edit_concertname)
    EditText concertname;
    @BindView(R.id.edit_artist)
    EditText artistname;
    @BindView(R.id.edit_stime)
    EditText startingtime;
    @BindView(R.id.edit_etime)
    EditText endingtime;
    @BindView(R.id.results_gone)
    LinearLayout results;
    @BindView(R.id.adv_result)
    ListView list_result;


    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_search);
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


    }

    private void advsearch() {



        final String name = concertname.getText().toString();
        final String artist_name = artistname.getText().toString();
        final String location = locatio.getText().toString();
        final String location_name = locatio_name.getText().toString();
        int min_price = 0;
        if(  !( minprice.getText().toString().isEmpty() )  )
            min_price = Integer.parseInt(minprice.getText().toString());
        int max_price = 0;
        if(  !(maxprice.getText().toString().isEmpty() )  )
            max_price = Integer.parseInt(maxprice.getText().toString());
        final String start_date_str = startDate.getText().toString();
        final String end_date_str = endDate.getText().toString();
        final String start_time_str = startingtime.getText().toString();
        final String end_time_str = endingtime.getText().toString();

        AdvReq request = new AdvReq();

        if(  !( name.isEmpty() )  ){
            request.setName(name);
        }
        if(  !( artist_name.isEmpty() )  ){
            request.setArtist_name(artist_name);
        }
        if(  !( location.isEmpty() )  ){
            request.setLocation(location);
        }
        if(  !( location_name.isEmpty() )  ){
            request.setLocation_name(location_name);
        }
        if(  !( minprice.getText().toString().isEmpty() )  ){
            request.setMin_price(min_price);
        }
        if(  !(maxprice.getText().toString().isEmpty() )  ){
            request.setMax_price(max_price);
        }
        if(  !( start_date_str.isEmpty() )  ){
            request.setStart_date_str(start_date_str);
        }
        if(  !( end_date_str.isEmpty() )  ){
            request.setEnd_date_str(end_date_str);
        }
        if(  !( start_time_str.isEmpty() )  ){
            request.setStart_time_str(start_time_str);
        }
        if(  !( end_time_str.isEmpty() )  ){
            request.setEnd_time_str(end_time_str);
        }

        if(name.isEmpty()  &&  artist_name.isEmpty() &&   location.isEmpty() &&  location_name.isEmpty() &&   minprice.getText().toString().isEmpty() &&
                maxprice.getText().toString().isEmpty() &&  start_date_str.isEmpty() &&  end_date_str.isEmpty() &&  start_time_str.isEmpty() && end_time_str.isEmpty()){
            Toast.makeText(AdvSearchActivity.this, "Please enter a field.", Toast.LENGTH_LONG).show();

        }else{

            concertifyApiRequest.getAdvResult(request, new Callback<ArrayList<Concert>>() {
                @Override
                public void success( ArrayList< Concert > resultConcerts, Response response) {

                    if(resultConcerts.isEmpty()){
                        Toast.makeText(AdvSearchActivity.this, "According to your search, \n No results. ", Toast.LENGTH_LONG).show();
                    }else{
                        results.setVisibility(View.VISIBLE);
                        ConcertAdapter concertAdapter = new ConcertAdapter(AdvSearchActivity.this, resultConcerts, false);
                        list_result.setAdapter(concertAdapter);
                    }
                }
                @Override
                public void failure(RetrofitError error) {
                    Log.e("AdvSearchLog",  "failure");
                    Toast.makeText(AdvSearchActivity.this, "Connection error.", Toast.LENGTH_LONG).show();
                }
            });

        }

    }

    @OnClick(R.id.tv_profile_adv)
    public void onClickProfile(){
        finish();
        Intent intent = new Intent(AdvSearchActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_explore_adv)
    public void onClickExplore(){
        finish();
        Intent intent = new Intent(AdvSearchActivity.this,RecommendationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_logout_adv)
    public void onViewClicked() {
        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();

        editor.putString("name",null);

        editor.apply();
        finish();
        Intent intent = new Intent(AdvSearchActivity.this, UnregisteredMainPageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_home_adv)
    public void onClickHome(View v){
        finish();
        Intent intent = new Intent(AdvSearchActivity.this,MainActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.bt_advsearch)
    public void onClickAdvo(){
        advsearch();
    }

}
