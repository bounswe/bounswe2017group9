package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.abtasdan.listviewtutorial.R;

import butterknife.BindView;

public class SignUpActivity extends Activity {

    @BindView(R.id.et_email)
    EditText emailText;

    @BindView(R.id.et_password)
    EditText passwordText;

    @BindView(R.id.btn_login_main)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }









}
