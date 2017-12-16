package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;
import com.example.abtasdan.listviewtutorial.modals.NewUserObject;
import com.example.abtasdan.listviewtutorial.modals.requests.SignUpReq;
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

public class SignUpActivity extends Activity {
    @BindView(R.id.et_photoSignUp)
    EditText photoPathText;
    @BindView(R.id.et_nameSignUp)
    EditText nameText;
    @BindView(R.id.et_passwordSignUp)
    EditText passwordText;
    @BindView(R.id.et_emailSignUp)
    EditText emailText;
    @BindView(R.id.et_username)
    EditText usernameText;

    @BindView(R.id.btn_signUP_2)
    Button signUpButton;

    private RestAdapter restAdapter;
    private ConcertifyApiRequest concertifyApiRequest;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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

    @OnClick(R.id.btn_signUP_2)
    public void signUp() {
        final String mail = emailText.getText().toString();
        final String password = passwordText.getText().toString();
        final String name= nameText.getText().toString();

        if(mail.isEmpty() || password.isEmpty()||name.isEmpty()){

        }else {

            final SignUpReq newUserObject = new SignUpReq();
            newUserObject.setName(name);
            newUserObject.setEmail(mail);
            newUserObject.setPassword(password);



            concertifyApiRequest.newUser(newUserObject, new Callback<CreatedBy>() {
                @Override
                public void success(CreatedBy stringResponse, Response response) {

                        Log.e("loginservice", "success");
                        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
                        editor.putString("mail", stringResponse.getEmail());
                        editor.putInt("id", stringResponse.getId());
                        editor.putString("name", stringResponse.getName());
                        editor.putInt("followers", stringResponse.getFollowers());
                        editor.putInt("followings",stringResponse.getFollowings());
                        editor.apply();

                        finish();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("loginservice", error.toString());
                    System.out.print(newUserObject);
                }
            });

        }

    }









}
