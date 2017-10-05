package com.concerter.controllers;
import com.concerter.models.User;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ConcerterController {

    User user=new User();

    //example request
    //http://localhost:8080/getaccount?name=hilal
    @RequestMapping("/getaccount")
    public String getAccount(@RequestParam(value = "name") String name) {
        user.setName(name);
        return user.getName();
    }
    //http://localhost:8080/getAccount?name=hilal
    @RequestMapping("/getAccount")
    public JSONObject getAccountwithJSON(@RequestParam(value = "name") String name){
        JSONObject json=new JSONObject();
        json.put("name",name);
        return json;
    }




}
