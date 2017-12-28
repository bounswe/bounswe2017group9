package com.example.abtasdan.listviewtutorial.requests.requests;

import com.example.abtasdan.listviewtutorial.modals.CommentObject;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.CreateConcertObject;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;
import com.example.abtasdan.listviewtutorial.modals.NewUserObject;
import com.example.abtasdan.listviewtutorial.modals.requests.AttendConcertReq;
import com.example.abtasdan.listviewtutorial.modals.requests.LoginReq;
import com.example.abtasdan.listviewtutorial.modals.requests.SearchResult;
import com.example.abtasdan.listviewtutorial.modals.requests.SignUpReq;


import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;

public interface ConcertifyApiRequest {


    @GET("/active-concerts")
    void getConcerts( Callback<ArrayList<Concert>> projectCallback);

    @GET("/basic-search")
    void search(@Query("searchKey") String searchKey, Callback<SearchResult> searchResultCallback);

    @POST("/user")
    void login(@Body LoginReq loginReq, Callback<CreatedBy> projectCallback);

   @POST("/new-user")
    void newUser(@Body SignUpReq newUser, Callback<CreatedBy> projectCallback);

    @POST("/concerts")
    void newConcert(@Body CreateConcertObject newConcertObject, Callback<String> projectCallback);

    @POST("/recommend/48")
    void getRecommend( @Body int user_id, Callback<ArrayList<Concert>> arrayListCallback);


    @GET("/concerts")
    void getAttendConcerts(@Body AttendConcertReq attendConcertReq, Callback<ArrayList<Concert>> arrayListCallback);

    @POST("/newcomment")
    void newComment(@Body CommentObject commentObject, Callback<String> projectCallback);





}
