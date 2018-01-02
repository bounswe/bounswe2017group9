package boun.group9.webservice.app.controller;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.AdvancedSearch;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.BasicSearch;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.SearchChecker;
import boun.group9.webservice.helper.UserChecker;

import boun.group9.webservice.helper.ConcertChecker;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Controller to handle operations related with search
 * @author ffguven
 *
 */
@RestController
public class SearchController {
	
	/**
	 * HTTP GET request with request parameter searchKey will result in searching the key in concerts,artists  and users
	 * @param searchKey the parameter that will be searched on Concerts, Artists and Users table on db
	 * @return JSON string of BasicSearch object
	 */
	//searches
    @RequestMapping(value="basic-search",method=RequestMethod.GET)
    public String basicSearch(@RequestParam String searchKey) {
    	String result;
    	BasicSearch bs;
    	ArrayList<Users> userList = SearchChecker.searchUsers(searchKey); // search users by using helper function
    	ArrayList<Concerts> concertList = SearchChecker.searchConcerts(searchKey); //search concerts by using helper function
    	concertList= ConcertChecker.sortByDate(concertList);
        
    	ArrayList<Artists> artistList = SearchChecker.searchArtists(searchKey); //search artists by using helper function
    	bs = new BasicSearch(userList,concertList,artistList);
    	result = Application.gson.toJson(bs);
    	return result;
    
    }
    @RequestMapping(value="advanced-search",method=RequestMethod.POST)
    public String advancedSearch(@RequestBody String body) {
    	AdvancedSearch as = Application.gson.fromJson(body, AdvancedSearch.class);
    	ArrayList<Concerts> concertList = SearchChecker.advancedSearch(as);
    	concertList= ConcertChecker.sortByDate(concertList);
        
    	return Application.gson.toJson(concertList);
    }

}