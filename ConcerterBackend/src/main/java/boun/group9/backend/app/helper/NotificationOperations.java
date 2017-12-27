package boun.group9.backend.app.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Notifications;
import boun.group9.backend.app.data.SemanticTag;

public class NotificationOperations {

		public static ArrayList<Notifications> getNotificationList(int userId){
			ArrayList<Notifications> notificationList = new ArrayList<Notifications>();
			try {
				URL url = new URL(Application.API_ENDPOINT+"/notifications/"+userId);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String json = br.readLine();
				notificationList = new ArrayList<Notifications> (Arrays.asList(Application.gson.fromJson(json, Notifications[].class)));
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return notificationList;
		}
}
