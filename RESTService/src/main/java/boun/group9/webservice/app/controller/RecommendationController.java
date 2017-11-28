package boun.group9.webservice.app.controller;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class RecommendationController {
    @RequestMapping(value="recommendation/{userID}",method= RequestMethod.GET)
    public static String getRecommendation(@PathVariable(value="userID") int userID){
        String jsonString;
        String query;
        ResultSet rs;
        Concerts concert;
        int concert_id=0;
        ArrayList<Concerts> concertList=new ArrayList<Concerts>();
        try{
            query= RecommendationChecker.recommendationQuery(userID);
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                concert_id=rs.getInt("Concert_id");
                jsonString=ConcertController.getConcert(concert_id);
                System.out.println(jsonString);
                concert=Application.gson.fromJson(jsonString, Concerts.class);
                concertList.add(concert);
            }
            jsonString = Application.gson.toJson(concertList);
        }catch(SQLException ex) {
            System.out.println("SQL Exception occured");
            ex.printStackTrace();
            return "SQL Error occured.";
        }catch(NotSavedException ex) {
            ex.printStackTrace();
            return "Not saved.";
        }
        return jsonString;
    }


}
