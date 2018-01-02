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
/**
 * Controller to handle the operations related with following
 * @author ffguven
 *
 */
@RestController
public class RelationController {
	/**
	 * HTTP POST request with request parameters will result in following the specified user
	 * @param follower id of the follower user
	 * @param following id of the following user
	 * @return status
	 */
	@RequestMapping(value="follow",method=RequestMethod.POST)
	public String follow(@RequestParam int follower, @RequestParam int following) {
		RelationChecker.follow(follower,following); // use helper function to follow specified user
		return "OK.";
	}
	/**
	 * HTTP POST request with request parameters will result in unfollowing the specified user
	 * @param follower id of the follower user
	 * @param following id of the following user
	 * @return
	 */
	@RequestMapping(value="unfollow",method=RequestMethod.POST)
	public String unfollow(@RequestParam int follower, @RequestParam int following) {
		RelationChecker.unfollow(follower,following); // use helper function to unfollow specified user
		return "OK.";
	}
}
