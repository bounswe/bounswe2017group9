package boun.group9.webservice.app.controller;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.MusicalInterests;
import boun.group9.webservice.app.data.SemanticTags;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.MusicalInterestChecker;
import boun.group9.webservice.helper.WikiDataUtility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class MusicalInterestsController {


    @RequestMapping(value="musical-interests/search/{search}",method= RequestMethod.GET)
    public String getMusicalInterestFromWikidata(@PathVariable(value="search") String search) {
        String jsonString="";
        ArrayList<MusicalInterests> interestList = new ArrayList<>();
        MusicalInterests interest;
        try {

            JSONArray result= WikiDataUtility.searchData(search);
            Iterator i = result.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                interest=new MusicalInterests();
                interest.setMusicalInterestId(innerObj.get("id").toString());
                interest.setLabel(innerObj.get("label").toString());
                interestList.add(interest);

            }

            jsonString = Application.gson.toJson(interestList);


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return jsonString;
    }
    @RequestMapping(value = "user/musical-interests",method = RequestMethod.POST)
    public String addMusicalInterests(@RequestBody String body) {
        String query="";
        try {
            MusicalInterests interest=Application.gson.fromJson(body, MusicalInterests.class);
            query= MusicalInterestChecker.insertMusicalInterestsQuery(interest);
            System.out.println(query);
            Database.connect(query, Application.MODE_UPDATE);
            return "Saved.";
        }catch(SQLException ex) {
            ex.printStackTrace();
            return "SQL error occured.";
        }catch(NotSavedException ex) {
            ex.printStackTrace();
            return "Not saved.";
        }


    }

    @RequestMapping(value = "user/{userID}/musical-interests", method = RequestMethod.GET)
    public String getMusicalInterests(@PathVariable("userID") int user_id){

        ArrayList<MusicalInterests> interestsList = MusicalInterestChecker.getAllInterests(user_id);
        String jsonString = Application.gson.toJson(interestsList);
        System.out.println(jsonString);

        return jsonString;
    }


    @RequestMapping(value="user/{userID}/musical-interests/{interestID}",method=RequestMethod.DELETE)
    public String deleteMusicalInterest(@PathVariable(value="interestID") int interestID , @PathVariable(value = "userID") int userID){
        String query = MusicalInterestChecker.deleteMusicalInterest(interestID,userID);
        try {
            System.out.println(query);
            Database.connect(query, Application.MODE_UPDATE);
            return "Deleted.";
        }catch(SQLException ex) {
            ex.printStackTrace();
            return "SQL error occured.";
        }catch(NotSavedException ex) {
            ex.printStackTrace();
            return "Not deleted.";
        }
    }
}
