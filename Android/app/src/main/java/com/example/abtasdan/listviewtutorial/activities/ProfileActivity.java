package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.abtasdan.listviewtutorial.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity extends Activity {
    @BindView(R.id.tv_explore_profile)
    TextView goExplore;

    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            int id = prefs.getInt("idName", 0); //0 is the default value.

        }
    }

    @OnClick(R.id.tv_explore_profile)
    public void onClickExplore(){
        finish();
        Intent intent = new Intent(ProfileActivity.this,RecommendationActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_home_profile)
    public void onClickHome(){
        finish();
        Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_logout_profile)
    public void onClickLogout(){
        finish();
        Intent intent = new Intent(ProfileActivity.this,UnregisteredMainPageActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_profile_profile)
    public void onClickProfile(){
        finish();
        Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
        startActivity(intent);
    }
}
