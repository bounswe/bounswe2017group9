package boun.group9.webservice.app.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.helper.RecommendationChecker;

@RestController
public class RecommendationController {
	@RequestMapping(value="recommend/{userID}",method=RequestMethod.POST)
	public String recommend(@PathVariable(value="userID") int userID) {
		ArrayList<Concerts> concertList = RecommendationChecker.recommend(userID);
		return Application.gson.toJson(concertList);
	}
}
