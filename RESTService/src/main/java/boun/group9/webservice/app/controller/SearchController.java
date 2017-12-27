package boun.group9.webservice.app.controller;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.AdvancedSearch;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.BasicSearch;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.ConcertChecker;
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
	
	//searches
    @RequestMapping(value="basic-search",method=RequestMethod.GET)
    public String basicSearch(@RequestParam String searchKey) {
    	String result;
    	BasicSearch bs;
    	ArrayList<Users> userList = SearchChecker.searchUsers(searchKey);
    	ArrayList<Concerts> concertList = SearchChecker.searchConcerts(searchKey);
    	concertList= ConcertChecker.sortByDate(concertList);
        ArrayList<Artists> artistList = SearchChecker.searchArtists(searchKey);
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