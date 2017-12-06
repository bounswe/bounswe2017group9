package com.example.abtasdan.listviewtutorial.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.abtasdan.listviewtutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends Activity {
    @BindView(R.id.tv_explore_profile)
    TextView goExplore;
    @BindView(R.id.tv_profile_profile)
    TextView goProfile;
    @BindView(R.id.tv_logout_profile)
    TextView goLogout;
    @BindView(R.id.tv_home_profile)
    TextView goMain;
    @BindView(R.id.tv_profile_name)
    TextView nameText;
    @BindView(R.id.tv_profile_email)
    TextView emailText;
    @BindView(R.id.tv_profile_followers)
    TextView followersText;
    @BindView(R.id.tv_profile_followings)
    TextView followingsText;
    @BindView(R.id.tv_last_update)
    TextView lastUpText;



    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);

        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            String email = prefs.getString("mail", "No email defined");
            String lastUp = prefs.getString("updated_at","No update defined");
            int followings = prefs.getInt("followings", 0);
            int followers = prefs.getInt("followers", 0);

            String temp1= followers+" Followers";
            String temp2= followings+" Followings";
            nameText.setText(name);
            emailText.setText(email);
            lastUpText.setText(lastUp);
            followersText.setText(temp1);
            followingsText.setText(temp2);
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
    @OnClick(R.id.tv_profile_attended)
    public void onClickAttended(){
        Intent intent = new Intent(ProfileActivity.this,ProfileOldConcertsActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_profile_attendings)
    public void onClickAttending(){
        Intent intent = new Intent(ProfileActivity.this,ProfileConcertsActivity.class);
        startActivity(intent);
    }
}
