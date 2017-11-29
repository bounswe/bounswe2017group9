package boun.group9.backend.app.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.SearchOperations;

@Controller
public class SearchController {
    @RequestMapping("/searchconcert/{name}")
    public String basicSearchConcert(@PathVariable("name") String name,Model model) {
        ArrayList<Concerts> concert = SearchOperations.basicSearchConcert(name);
        for (int i = 0; i < concert.size(); i++) {
            System.out.println(concert.get(i).getName());
        }
        model.addAttribute("concertList",concert);
        return "redirect/:concert";
    }
    @RequestMapping("/searchuser/{name}")
    public String basicSearchUser(@PathVariable("name") String name,Model model) {
        ArrayList<Users> users = SearchOperations.basicSearchUser(name);
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getName());
        }
        model.addAttribute("userList",users);
        return "redirect/:concert";
    }
    
    @RequestMapping("/advancedSearchPrice/{min}/{max}")
    public String advancedSearchPrice(@PathVariable("min") int min ,@PathVariable("max") int max, Model model) {
        ArrayList<Concerts> concert = SearchOperations.advancedSearchPrice(min, max);
        for (int i = 0; i < concert.size(); i++) {
            System.out.println(concert.get(i).getName());
        }
        model.addAttribute("concertList",concert);
        return "deneme";
    }
    
    @RequestMapping("/advancedSearchLocation/{location}")
    public String advancedSearchLocation(@PathVariable("location") String location , Model model) {
        ArrayList<Concerts> concert = SearchOperations.advancedSearchLocation(location);
        for (int i = 0; i < concert.size(); i++) {
            System.out.println(concert.get(i).getName());
        }
        model.addAttribute("concertList",concert);
        return "deneme";
    }
    
    @RequestMapping("/advancedSearchDate/{startDate}/{endDate}")
    public String advancedSearchDate(@PathVariable("startDate") String startDate ,@PathVariable("endDate") String endDate , Model model) {
        ArrayList<Concerts> concert = SearchOperations.advancedSearchDate(startDate, endDate);
        for (int i = 0; i < concert.size(); i++) {
            System.out.println(concert.get(i).getName());
        }
        model.addAttribute("concertList",concert);
        return "deneme";
    }
    @RequestMapping("/advancedSearch/{minPrice}/{maxPrice}/{location}/{startDate}/{endDate}")
    public String advancedSearch(@PathVariable("minPrice") int minPrice, 
    							 @PathVariable("maxPrice") int maxPrice, @PathVariable("location") String location,
    							@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate , Model model) {
        ArrayList<Concerts> concert = SearchOperations.advancedSearch(minPrice, maxPrice, location, startDate, endDate);
        for (int i = 0; i < concert.size(); i++) {
            System.out.println(concert.get(i).getName());
        }
        model.addAttribute("concertList",concert);
        return "deneme";
    }
    
}
