package boun.group9.webservice.helper;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.MusicalInterests;
import boun.group9.webservice.exception.NotSavedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicalInterestChecker {
    public static String insertMusicalInterestsQuery(MusicalInterests interests) {
        String query=
                "INSERT INTO MusicalInterests (label, user_id,musicalInterestId)\n" +
                        "SELECT * FROM (SELECT '"+interests.getLabel().replaceAll("\"", " ")+"',"+
                        interests.getUser_id()+",'"
                        +interests.getMusicalInterestId()+"') AS tmp\n" +
                        "WHERE NOT EXISTS (SELECT musicalInterestId,user_id FROM MusicalInterests WHERE (musicalInterestId = '"+interests.getMusicalInterestId()+"' and user_id="+
                        interests.getUser_id()+")) LIMIT 1;";

        return query;
    }

    public static ArrayList<MusicalInterests> getAllInterests (int userId){
        ArrayList<MusicalInterests> result = new ArrayList<MusicalInterests>();
        String query="SELECT * FROM MusicalInterests WHERE user_id="+userId+";";
        ResultSet rs;
        try {
            rs = Database.connect(query, Application.MODE_GET);
            while(rs.next()) {
                MusicalInterests interest=new MusicalInterests();
                interest.setId(rs.getInt("id"));
                interest.setLabel(rs.getString("label"));
                interest.setMusicalInterestId(rs.getString("musicalInterestId"));
                interest.setUser_id(rs.getInt("user_id"));
                result.add(interest);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }catch(NotSavedException ex) {
            ex.printStackTrace();
            return null;
        }
        Database.closeConnection();
        return result;
    }

    public static String deleteMusicalInterest(int interestID, int userID){

        String query = "DELETE FROM MusicalInterests WHERE MusicalInterests.id = " + interestID + " and MusicalInterests.user_id = "+ userID +";" ;
        return query;
    }
}
