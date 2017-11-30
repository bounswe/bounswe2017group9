package com.example.abtasdan.listviewtutorial;

/**
 * Created by abtasdan on 29.11.2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.abtasdan.listviewtutorial.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


public class Utils {


    // public static final String BASE_IMAGE_URL =
    // "http://cardgusto.solidict.com/";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static String emtyToNull(String object) {
        if (object==null) {
            return null;
        }
        return object.isEmpty()?null:object;
    }
    public static String getJsonObject(Object object) {
        String json = null;
        try {
            json = Utils.OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Object getObjectFromJson(String jsonObject, Class typeRef) {
        Object object = null;
        try {
            object = Utils.OBJECT_MAPPER.readValue(jsonObject, typeRef);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
