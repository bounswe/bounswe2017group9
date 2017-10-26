package com.concerter.controllers;

import com.concerter.models.Comments;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/Comment")
public class CommentController {

    @RequestMapping(value="/getComment/{commentId}", method = RequestMethod.GET)
    public JSONObject getComment(@PathVariable int commentId) throws ParseException{
        JSONObject json=new JSONObject();
        return json;
    }


    @RequestMapping(value="/createComment", method = RequestMethod.POST)
    public JSONObject createComment(@RequestBody Comments comment) throws ParseException{
    JSONObject json=new JSONObject();
    return json;
    }

    @RequestMapping(value="/getAllCommentsByConcertId/{concertId}", method = RequestMethod.GET)
    public JSONObject getAllCommentsByConcertId(@PathVariable int concertId) throws ParseException{
        JSONObject json=new JSONObject();
        return json;
    }



}
