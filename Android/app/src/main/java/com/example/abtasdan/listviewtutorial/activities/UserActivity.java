package com.example.abtasdan.listviewtutorial.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abtasdan.listviewtutorial.R;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_profile_name)
    TextView tvProfileName;
    @BindView(R.id.tv_profile_email)
    TextView tvProfileEmail;
    @BindView(R.id.tv_last_update)
    TextView tvLastUpdate;
    @BindView(R.id.tv_profile_followings)
    TextView tvProfileFollowings;
    @BindView(R.id.tv_profile_followers)
    TextView tvProfileFollowers;
    @BindView(R.id.tv_profile_attendings)
    TextView tvProfileAttendings;
    @BindView(R.id.tv_profile_attended)
    TextView tvProfileAttended;
    @BindView(R.id.btn_follow)
    Button btnFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        CreatedBy user_object = (CreatedBy) getIntent().getSerializableExtra("user_object");
        Glide.with(this).load(user_object.getPhoto_path()).into(ivImage);
        tvProfileName.setText(user_object.getName());
        tvProfileEmail.setText(user_object.getEmail());
        tvLastUpdate.setText(user_object.getLast_login());
        tvProfileFollowings.setText(String.valueOf(user_object.getFollowings()));
        tvProfileFollowers.setText(String.valueOf(user_object.getFollowers()));
    }
}
