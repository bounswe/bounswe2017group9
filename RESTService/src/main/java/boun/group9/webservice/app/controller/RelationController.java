package boun.group9.webservice.app.controller;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Relations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.IJsonSyntaxException;
import boun.group9.webservice.exception.ISQLException;
import boun.group9.webservice.exception.InternalServerException;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.RelationChecker;
import boun.group9.webservice.helper.UserChecker;
import com.google.gson.JsonSyntaxException;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class RelationController {

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
