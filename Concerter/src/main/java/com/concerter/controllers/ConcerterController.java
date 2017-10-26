package com.concerter.controllers;

import com.concerter.config.AppConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.io.IOException;

@Controller
public class ConcerterController {

    @RequestMapping(value = "/")

    public JSONObject test() {
        JSONObject json=new JSONObject();
        json.put("status:","works");
        return json;
    }

    /*
    //Example request from API
    @RequestMapping(value = "/login")
    public JSONObject POST() throws ParseException {
        String input="{\"email\":\"fatih@gmail.com\",\"password\":\"54321\"}";
        JSONObject json= AppConfig.URLConnection("user",input,"POST");
        return json;
    }
    */


}




