package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.modals.ArtistForConcertObject;
import com.example.abtasdan.listviewtutorial.modals.CreateConcertObject;
import com.example.abtasdan.listviewtutorial.modals.LocationObject;
import com.example.abtasdan.listviewtutorial.modals.NewUserObject;
import com.example.abtasdan.listviewtutorial.modals.requests.SignUpReq;
import com.example.abtasdan.listviewtutorial.requests.requests.ConcertifyApiRequest;
import com.example.abtasdan.listviewtutorial.requests.requests.RetrofitHttpClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class CreateConcertActivity extends Activity {

    @BindView(R.id.btn_create_concert)
    Button createButton;
    @BindView(R.id.et_create_address)
    EditText addressText;
    @BindView(R.id.et_create_artist_name)
    EditText artistText;
    @BindView(R.id.et_create_city)
    EditText cityText;
    @BindView(R.id.et_create_date)
    EditText dateText;
    @BindView(R.id.et_create_max_price)
    EditText maxPriceText;
    @BindView(R.id.et_create_min_price)
    EditText minPriceText;
    @BindView(R.id.et_create_name)
    EditText nameText;
    @BindView(R.id.et_create_photo)
    EditText photoText;

    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_concert);
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


    @OnClick(R.id.btn_create_concert)
    public void createConcert() {
        final String artist = artistText.getText().toString();
        final String date = "29.12.2017 15.00 PM";
        final String name = nameText.getText().toString();
        final String city = cityText.getText().toString();
        final String address = addressText.getText().toString();
        final int minPrice = Integer.parseInt(minPriceText.getText().toString());
        final int maxPrice = Integer.parseInt(maxPriceText.getText().toString());
        final String photoPath = photoText.getText().toString();

        if (artist.isEmpty() || date.isEmpty() || name.isEmpty() || city.isEmpty() || address.isEmpty() ) {

        } else {
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm aa");
            Date newDate= null;
            try{
                newDate = dateFormat.parse(date);
            }catch (ParseException e){

            }

            final CreateConcertObject newConcertObject = new CreateConcertObject();
            final LocationObject locationObject = new LocationObject();
            locationObject.setAddress(address);
            locationObject.setCity(city);
            locationObject.setLatitude(0);
            locationObject.setLongitude(0);
            final ArtistForConcertObject artistForConcertObject = new ArtistForConcertObject();
            artistForConcertObject.setName(artist);

            newConcertObject.setName(name);
            newConcertObject.setArtist(artistForConcertObject);
            newConcertObject.setLocation(locationObject);
            newConcertObject.setDate_time(newDate);
            newConcertObject.setMax_price(maxPrice);
            newConcertObject.setMin_price(minPrice);
            newConcertObject.setImage_path(photoPath);
            String[] dateParts=date.split(" ");
            newConcertObject.setDate_str(dateParts[0]);
            newConcertObject.setTime_str(dateParts[1]);
            SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
            String restoredText = prefs.getString("name", null);

            if (restoredText != null) {
                int id = prefs.getInt("id", 0); //0 is the default value.
                newConcertObject.setCreated_by_id(id);

            }

            concertifyApiRequest.newConcert(newConcertObject, new Callback<String>() {
                @Override
                public void success(String stringResponse, Response response) {
                    if (stringResponse.equals("OK.")) {
                        Log.e("createConcertservice", "success");

                        finish();
                        Intent intent = new Intent(CreateConcertActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Log.e("createConcertservice", "kardeş ok dönmedi");
                    }


                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("createConcertservice", error.toString());

                }
            });

        }
    }
}