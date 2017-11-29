package boun.group9.webservice.helper;

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


     public static String advancedSearchGeneral(Date startDate , Date endDate , String location , int minPrice , int maxPrice){

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        String query = "select id as Concerts_id from concerts WHERE ";

        if(startDate != null && endDate != null) {
            String q = " (concerts.date_time between \"" + sdf.format(startDate)+ "\"  and \"" + sdf.format(endDate)+ "\") AND";
            query += q ;
        }else if(startDate != null && endDate == null) {
            String q = " (concerts.date_time between \"" + sdf.format(startDate)+ "\"  and now() ) AND";
            query += q;
        }else if(startDate == null && endDate != null){
            startDate.setTime(1990-01-01);
            String q = " (concerts.date_time between \"" + sdf.format(startDate)+ "\"  and \"" + sdf.format(endDate)+ "\") AND";
            query += q;
        }

        if(location != null && minPrice != 0 && maxPrice != 0) {
            String q = " (concerts.location IN ( select id from locations where city Like CONCAT('%', \"" + location + "\" ,'%'))) AND";
            query += q;
        }else {
            String q = " (concerts.location IN ( select id from locations where city Like CONCAT('%', \"" + location + "\" ,'%')));";
            query += q;
        }

        if(minPrice != 0 && maxPrice != 0) {
            String q = " ((concerts.min_price between "+ minPrice  +" and " + maxPrice + ") OR (concerts.max_price between "+ minPrice +" and " + maxPrice + ") OR ( " +
                    minPrice + " between concerts.min_price and concerts.max_price ) OR ( " +maxPrice + " between concerts.min_price and concerts.max_price));";
            query += q;
        }

        System.out.println(query);

        return query;
    }
}

