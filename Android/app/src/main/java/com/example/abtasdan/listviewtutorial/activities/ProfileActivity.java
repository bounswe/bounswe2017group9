package com.example.abtasdan.listviewtutorial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abtasdan.listviewtutorial.R;

import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @OnClick(R.id.tv_explore_profile)
    public void onClickExplore(){
        Intent intent = new Intent(ProfileActivity.this,RecommendationActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_home_profile)
    public void onClickHome(){
        Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_logout_profile)
    public void onClickLogout(){
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
