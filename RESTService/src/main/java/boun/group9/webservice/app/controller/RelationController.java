package boun.group9.webservice.app.controller;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Relations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.IJsonSyntaxException;
import boun.group9.webservice.exception.ISQLException;
import boun.group9.webservice.exception.InternalServerException;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.RelationChecker;
import boun.group9.webservice.helper.SearchChecker;
import boun.group9.webservice.helper.UserChecker;
import com.google.gson.JsonSyntaxException;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class RelationController {

    @RequestMapping(value="followers/{followingID}",method= RequestMethod.GET)
    public String getFollowers(@PathVariable(value="followingID")int followingID){
        String jsonString="";
        String query;
        String userQuery;
        ResultSet rs;
        ResultSet rsuser;
        Users user;
        ArrayList<Users> userList=new ArrayList<Users>();
        try{
            query= RelationChecker.getFollowers(followingID);
            System.out.println(query);
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                user=new Users();
                userQuery = UserChecker.getUser(rs.getInt("follower_id"));
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

    @RequestMapping(value="followings/{followerID}",method= RequestMethod.GET)
    public String getFollowings(@PathVariable(value="followerID")int followerID){
        String jsonString="";
        String query;
        String userQuery;
        ResultSet rs;
        ResultSet rsuser;
        Users user;
        ArrayList<Users> userList=new ArrayList<Users>();
        try{
            query= RelationChecker.getFollowings(followerID);
            System.out.println(query);
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                user=new Users();
                userQuery = UserChecker.getUser(rs.getInt("following_id"));
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

    @RequestMapping(value="follow/{followerID}/{followingID}",method= RequestMethod.PUT)
    public String follow(@PathVariable(value="followerID")int followerID,@PathVariable(value="followingID")int followingID ) {
        String query;
        ResultSet rs;
        try {
            query = RelationChecker.follow(followerID, followingID);
            System.out.println(query);
            UserController.follow(followerID);
            UserController.followed(followingID);
            rs = Database.connect(query,Application.MODE_UPDATE);
            return "OK.";
        }catch(JsonSyntaxException ex) {
            ex.printStackTrace();
            throw new IJsonSyntaxException();
        }catch(SQLException ex) {
            ex.printStackTrace();
            throw new ISQLException();
        }catch(NotSavedException ex) {
            ex.printStackTrace();
            throw new InternalServerException();
        }
    }

    @RequestMapping(value="unfollow/{followerID}/{followingID}",method= RequestMethod.PUT)
    public String unFollow(@PathVariable(value="followerID")int followerID,@PathVariable(value="followingID")int followingID ) {
        String query;
        ResultSet rs;
        try {
            query = RelationChecker.unFollow(followerID, followingID);
            System.out.println(query);
            UserController.unFollow(followerID);
            UserController.unFollowed(followingID);
            rs = Database.connect(query,Application.MODE_UPDATE);
            return "OK.";
        }catch(JsonSyntaxException ex) {
            ex.printStackTrace();
            throw new IJsonSyntaxException();
        }catch(SQLException ex) {
            ex.printStackTrace();
            throw new ISQLException();
        }catch(NotSavedException ex) {
            ex.printStackTrace();
            throw new InternalServerException();
        }
    }

}
