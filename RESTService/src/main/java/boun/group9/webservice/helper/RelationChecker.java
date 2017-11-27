package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.Relations;

public class RelationChecker {
    /*
    public static String followQuery(Relations relations){
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        String query = "INSERT INTO Relations ";
        String fieldQuery = "(";
        String valuesQuery = "VALUES(";
        fieldQuery += "follower_id,";
        valuesQuery += relations.getFollower_id()+",";
        fieldQuery += "following_id,";
        valuesQuery += relations.getFollowing_id()+",";
        fieldQuery += "created_at,";
        valuesQuery += "'"+currentTime+"',";
        if(fieldQuery.endsWith(",")) {
            fieldQuery = fieldQuery.substring(0, fieldQuery.length()-1);
        }
        if(valuesQuery.endsWith(",")) {
            valuesQuery = valuesQuery.substring(0, valuesQuery.length()-1);
        }
        query+=fieldQuery+") "+valuesQuery+");";
        System.out.println(query);
        return query;

    }
*/
    public static String follow(int followerID, int followingID){
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        String query = "INSERT INTO Relations ";
        String fieldQuery = "(";
        String valuesQuery = "VALUES(";
        fieldQuery += "follower_id,";
        valuesQuery += followerID+",";
        fieldQuery += "following_id,";
        valuesQuery += followingID+",";
        fieldQuery += "created_at,";
        valuesQuery += "'"+currentTime+"',";
        if(fieldQuery.endsWith(",")) {
            fieldQuery = fieldQuery.substring(0, fieldQuery.length()-1);
        }
        if(valuesQuery.endsWith(",")) {
            valuesQuery = valuesQuery.substring(0, valuesQuery.length()-1);
        }
        query+=fieldQuery+") "+valuesQuery+");";
        System.out.println(query);
        return query;

    }


    public static String unFollow(int followerID, int followingID){
        String query = "DELETE FROM Relations Where Relations.follower_id = "+followerID+" and Relations.following_id="+ followingID+";";
        System.out.println(query);
        return query;

    }

}
