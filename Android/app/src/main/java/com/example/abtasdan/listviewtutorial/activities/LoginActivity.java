package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;
import com.example.abtasdan.listviewtutorial.modals.requests.LoginReq;
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

import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    @BindView(R.id.btn_login_main)
    Button btnMainMenu;
    @BindView(R.id.btn_login_spotify)
    Button btnLoginSpotify;
    @BindView(R.id.et_email)
    EditText etMail;
    @BindView(R.id.et_password)
    EditText etPassword;

    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;

    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    @OnClick(R.id.btn_login_main)
    public void onViewClicked() {
        login();

        finish();
    }
    @OnClick(R.id.btn_login_spotify)
    public void  onClicked(){
        Intent intent = new Intent(LoginActivity.this,UnregisteredMainPageActivity.class);
        startActivity(intent);
    }

    public void login() {
        String mail = etMail.getText().toString();
        String password = etPassword.getText().toString();
        if(mail.isEmpty() || password.isEmpty()){

        }else {

            final LoginReq loginReq = new LoginReq();

            loginReq.setEmail(mail);
            loginReq.setPassword(password);

            concertifyApiRequest.login(loginReq, new Callback<CreatedBy>() {
                @Override
                public void success(CreatedBy loginResponse, Response response) {
                    Log.e("loginservice", "success");
                    SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
                    editor.putString("mail", loginResponse.getEmail().toString());
                    editor.putLong("id", loginResponse.getId());
                    editor.putString("photo_path",loginResponse.getPhoto_path().toString());
                    editor.putString("created_at",loginResponse.getCreated_at().toString());
                    editor.putString("updated_at",loginResponse.getUpdated_at().toString());
                    editor.putString("last_login",loginResponse.getLast_login().toString());
                    editor.putString("name",loginResponse.getName().toString());
                    editor.putInt("followers",loginResponse.getFollowers());
                    editor.putInt("followings",loginResponse.getFollowings());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);


                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("loginservice", error.toString());
                }
            });

        }

    }
}
