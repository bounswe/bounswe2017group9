package boun.group9.webservice.helper;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Comments;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchChecker {
    public static String basicSearchQueryConcert(String input) {
        String query = "select id as Concerts_id from concerts WHERE name Like CONCAT('%',\"" + input +"\",'%') or \n" +
                "EXISTS( select id from locations where city Like CONCAT('%', \"" + input +"\" ,'%')) or \n" +
                "EXISTS( select id from artists where name Like CONCAT('%',\"" + input +"\" ,'%'));";
        return query;
    }


    public static String basicSearchQueryUser(String input){
        String query = "select id as Users_id from users WHERE name Like CONCAT('%',\"" + input + "\",'%') or username Like CONCAT('%',\"" + input+  "\",'%');";
        return  query;
    }

    public static String advancedSearchPrice(int min , int max){
        String query = "select id as Concerts_id from concerts where (concerts.min_price between "+ min  +" and " + max + ") OR (concerts.max_price between "+ min +" and " + max + ") OR ( " +
                 min + " between concerts.min_price and concerts.max_price ) OR ( " +max + " between concerts.min_price and concerts.max_price);";
        System.out.println(query);
        return query;
    }

    public static String advancedSearchLocation(String location){
        String query ="select id as Concerts_id from concerts WHERE concerts.location IN ( select id from locations where city Like CONCAT('%', \"" + location + "\" ,'%'));";
        System.out.println(query);
        return query;
    }

    public static String advancedSearchDate(Date startDate , Date endDate){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        String query = "select id as Concerts_id from concerts WHERE (concerts.date_time between \"" + sdf.format(startDate)+ "\"  and \"" + sdf.format(endDate)+ "\");";
        System.out.println(query);
        return query;
    }
}


