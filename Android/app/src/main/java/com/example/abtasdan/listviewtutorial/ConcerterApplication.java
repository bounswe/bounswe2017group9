package com.example.abtasdan.listviewtutorial;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.abtasdan.listviewtutorial.modals.CreatedBy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
/**
 * Created by abtasdan on 29.11.2017.
 */

public class ConcerterApplication extends MultiDexApplication {
    CreatedBy loginSignupResponse;

    public static final String MY_LOGIN_SIGN_UP_RESPONSE = "myLoginSignUpResponse";
    public SharedPreferences prefs;
    private Tracker mTracker;
    @Override
    public void onCreate() {
        super.onCreate();


        String jsonUser = prefs.getString(MY_LOGIN_SIGN_UP_RESPONSE, null);
        if (jsonUser != null) {
            try {
                this.loginSignupResponse = Utils.OBJECT_MAPPER.readValue(
                        jsonUser, CreatedBy.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public CreatedBy getLoginSignupResponse() {
        return loginSignupResponse;
    }

    public void setLoginSignupResponse(CreatedBy loginSignupResponse) {
        try {
            String json;
            json = Utils.OBJECT_MAPPER.writeValueAsString(loginSignupResponse);
            System.out.println("json login signup response : "
                    + loginSignupResponse);
            if (loginSignupResponse != null) {
                prefs.edit().putString(MY_LOGIN_SIGN_UP_RESPONSE, json).commit();
            } else {
                prefs.edit().remove(MY_LOGIN_SIGN_UP_RESPONSE).commit();
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.loginSignupResponse = loginSignupResponse;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
