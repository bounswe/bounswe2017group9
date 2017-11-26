package boun.group9.webservice.app.controller;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.SearchChecker;
import boun.group9.webservice.helper.UserChecker;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class SearchController {

    @RequestMapping(value="basicsearchuser/{username}",method= RequestMethod.GET)
    public String basicSearchUser(@PathVariable(value="username") String username) {
        String jsonString;
        ResultSet rs;
        ResultSet rsuser;
        Users user;
        String query;
        String userQuery;
        ArrayList<Users> userList = new ArrayList<Users>();
        try{
            query= SearchChecker.basicSearchQueryUser(username);
            System.out.println(query);
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
            user=new Users();
            userQuery = UserChecker.getUser(rs.getInt("Users_id"));
            System.out.println(userQuery);
            rsuser = Database.connect(userQuery, Application.MODE_GET);
                while(rsuser.next()) {
                    user.setId(rsuser.getInt("Users_id"));
                    user.setEmail(rsuser.getString("Users_email"));
                    user.setName(rsuser.getString("Users_name"));
                    user.setUsername(rsuser.getString("Users_username"));
                    user.setFollowers(rsuser.getInt("Users_followers"));
                    user.setFollowings(rsuser.getInt("Users_followings"));
                    user.setPhoto_path(rsuser.getString("Users_photo_path"));

                }
                userList.add(user);
            }
            jsonString = Application.gson.toJson(userList);
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
    @RequestMapping(value="basicsearchconcert/{concert}",method= RequestMethod.GET)
    public String basicSearchConcert(@PathVariable(value="concert") String concertinfo) {
        String jsonString;
        String query;
        ResultSet rs;
        Concerts concert;
        int concert_id=0;
        ArrayList<Concerts> concertList=new ArrayList<Concerts>();
        try{
            query=SearchChecker.basicSearchQueryConcert(concertinfo);
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                concert_id=rs.getInt("Concerts_id");
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

    @RequestMapping(value = "advancedSearchPrice/{min}/{max}", method = RequestMethod.GET)
    public String advancedSearchPrice(@PathVariable(value = "min") int minPrice ,@PathVariable(value = "max") int maxPrice ){
        String jsonString;
        String query;
        ResultSet rs;
        Concerts concert;
        int concert_id=0;
        ConcertController controller=new ConcertController();
        ArrayList<Concerts> concertList=new ArrayList<Concerts>();
        try{
            query=SearchChecker.advancedSearchPrice(minPrice, maxPrice);
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                concert_id=rs.getInt("Concerts_id");
                jsonString=controller.getConcert(concert_id);
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

    @RequestMapping(value = "advancedSearchLocation/{location}", method = RequestMethod.GET)
    public String advancedSearchLocaion(@PathVariable(value = "location") String location ){
        String jsonString;
        String query;
        ResultSet rs;
        Concerts concert;
        int concert_id=0;
        ConcertController controller=new ConcertController();
        ArrayList<Concerts> concertList=new ArrayList<Concerts>();
        try{
            query=SearchChecker.advancedSearchLocation(location);

            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                concert_id=rs.getInt("Concerts_id");
                jsonString=controller.getConcert(concert_id);
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

    @RequestMapping(value = "advancedSearchDate/{start}/{end}", method = RequestMethod.GET)
    public String advancedSearchDate(@PathVariable(value = "start") @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss") String start,
                                     @PathVariable(value = "end") @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss") String end){
        /*Date start = new Date("Jan 14,1970 5:50:50");
        Date end = new Date("Jan 14,2000 5:50:50");
        */
        System.out.println(start);
        System.out.println(end);

        String jsonString;
        String query;
        ResultSet rs;
        Concerts concert;
        int concert_id=0;



        ConcertController controller=new ConcertController();
        ArrayList<Concerts> concertList=new ArrayList<Concerts>();
        try{

            java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date startDate = format.parse(start);
            java.util.Date endDate = format.parse(end);

            /*
            SimpleDateFormat s = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Date startDate = s.parse(start);
            Date endDate = s.parse(end);
*/
            System.out.println(startDate);
            System.out.println(endDate);

            query=SearchChecker.advancedSearchDate(startDate,endDate);
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                concert_id=rs.getInt("Concerts_id");
                jsonString=controller.getConcert(concert_id);
                System.out.println(jsonString);
                concert=Application.gson.fromJson(jsonString, Concerts.class);
                concertList.add(concert);
            }
            jsonString = Application.gson.toJson(concertList);
        }catch(SQLException ex) {
            System.out.println("SQL Exception occured");
            ex.printStackTrace();
            return "SQL Error occured.";
        }catch(Exception ex) {
            ex.printStackTrace();
            return "Not saved.";
        }
        return jsonString;
    }



}