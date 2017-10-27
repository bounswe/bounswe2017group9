package com.concerter.controllers;

import com.concerter.models.Concerts;
import com.google.gson.Gson;
import javafx.application.Application;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Configuration
@Controller
@RequestMapping("/Concert")
public class ConcertController {
    public static HashMap<Integer,JSONObject> map=new HashMap<Integer,JSONObject>();

    //Example concert creation
    public static void createMockConcerts(){
        JSONObject json=new JSONObject();
        json.put("id",1);
        json.put("name","Can Gox Concert");
        json.put("created_by",1);
        json.put("artist_name",1);
        json.put("location",1);
        Date date=new Date();
        date.setTime(2017-02-01);
        json.put("date_time",date);
        json.put("min_price",20);
        json.put("max_price",50);
        json.put("rate",3);
        json.put("voter_amount",2);
        json.put("image_path","localhost/8080");
        map.put(1,json);



        json=new JSONObject();
        json.put("id",2);
        json.put("name","Rihanna Concert");
        json.put("created_by",2);
        json.put("artist_name",2);
        json.put("location",2);
        date=new Date();
        date.setTime(2017-02-01);
        json.put("date_time",date);
        json.put("min_price",60);
        json.put("max_price",100);
        json.put("rate",4);
        json.put("voter_amount",2);
        json.put("image_path","localhost/8080");
        map.put(2,json);

    }

    /*
    @RequestMapping(value="getConcert")
    public JSONObject getConcert(){
        createMockConcerts();
        JSONObject json=map.get(1);
        return json;
    }
    */


    //http://localhost:8080/Concert/getConcertById/1
    @RequestMapping(value="getConcertById/{concertId}")
    //@CrossOrigin(origins="http://localhost:3000")
    public JSONObject getConcertById(@PathVariable int concertId ){
        createMockConcerts();
        JSONObject json=map.get(concertId);
        if(json==null){
            json.put("status:","null");
        }
        return json;
    }



    @RequestMapping(value="getAllConcerts/{userId}", method = RequestMethod.GET)
    //@CrossOrigin(origins="http://localhost:3000")
    public JSONObject getAllConcertsByUserId(@PathVariable int userId) {
        createMockConcerts();
        JSONObject json=new JSONObject();
        return json;

    }


    //localhost:8080/Concert/createConcert
    @RequestMapping(value="/createConcert", method = RequestMethod.POST)
    //@CrossOrigin(origins="http://localhost:3000")
    public JSONObject createConcert(@RequestBody Concerts concert) throws ParseException{
        JSONObject json=new JSONObject();
        return json;
    }


}
