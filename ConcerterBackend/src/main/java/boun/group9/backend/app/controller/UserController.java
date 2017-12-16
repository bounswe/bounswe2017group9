package boun.group9.backend.app.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.SpotifyTokenBody;
import boun.group9.backend.app.data.Spotify_user;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.UserOperations;

import boun.group9.backend.app.helper.UserOperations;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
	private static STATUS status;
	
	@RequestMapping(value="/request-spotify-login",method=RequestMethod.POST)
	public RedirectView requestSpotifyLogin() {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("https://accounts.spotify.com/en/authorize?client_id=bb34cac7478d4bc483f7711e0873b8b4&scope=user-read-email&response_type=code&redirect_uri=http:%2F%2Fconcerter.eu-central-1.elasticbeanstalk.com%2Fspotify-code");
		return redirectView;
	}
	@RequestMapping(value="/spotify-code")
	public ModelAndView getSpotifyCode(Model model,@RequestParam(value="code",required=true) String code, HttpSession session) {
		System.out.println(code);
		String response;
		SpotifyTokenBody stb = new SpotifyTokenBody();
		Spotify_user spotifyUser;
		Users user;
		try {
			String clientString = Application.SPOTIFY_CLIENT_ID+":"+Application.SPOTIFY_CLIENT_SECRET;
			String parameters = "grant_type=authorization_code&redirect_uri=http%3A%2F%2Fconcerter.eu-central-1.elasticbeanstalk.com%2Fspotify-code&code="+code;
			URL url = new URL(Application.SPOTIFY_DEFAULT_AUTHENTICATION_HOST+"/api/token");
			HttpURLConnection connection= (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization","Basic "+Base64.encodeBase64String(clientString.getBytes()));
			connection.setRequestProperty("grant_type", "authorization_code");
			connection.setRequestProperty("code", code);
			connection.setRequestProperty("redirect_uri","http%3A%2F%2Fhttp://concerter.eu-central-1.elasticbeanstalk.com/spotify-code");
			System.out.println(Base64.encodeBase64String(clientString.getBytes()));
			
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(parameters.getBytes());
			os.close();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			response = br.readLine();
			stb = Application.gson.fromJson(response, SpotifyTokenBody.class);
			url = new URL(Application.SPOTIFY_DEFAULT_HOST+"/v1/me");
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", stb.getToken_type()+" "+stb.getAccess_token());
			connection.connect();
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String currentLine;
			response = "";
			while((currentLine=br.readLine()) != null) {
				response+=currentLine;
			}
			System.out.println(response);
			spotifyUser = Application.gson.fromJson(response, Spotify_user.class);
			user = new Users(spotifyUser);
			user = UserOperations.signUp(user);
			session.setAttribute("spotifyToken",stb);
			session.setAttribute("loggedInUser",user);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/index");
	}
	@RequestMapping(value="/spotify-token")
	public String getSpotifyToken(@RequestBody String body) {
		System.out.println(body);
		return "index";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model) {
		Users user = new Users();
		model.addAttribute("user",user);
		return "login-signup";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute Users user,HttpSession session,Model model) {
		user = UserOperations.login(user);
		session.setAttribute("loggedInUser",user);
		if(user == null) {
			System.out.println("user is null.");
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("redirect:/index");
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return new ModelAndView("redirect:/login");
	}
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signup(Model model) {
		return "login-signup";
	}
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute Users user) {
		UserOperations.signUp(user);
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping("/profile/{userID}")
	public String profilePage(@PathVariable("userID") int userID,Model model) {
		Users user = UserOperations.getUser(userID);
		System.out.println(user.getName());
		model.addAttribute("user",user);
		return "profile";
	}
	@RequestMapping("/me")
	public String myProfile(HttpSession session,Model model) {
		SpotifyTokenBody stb = (SpotifyTokenBody)session.getAttribute("spotifyToken");
		Users user = (Users)session.getAttribute("loggedInUser");
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		System.out.println(user.getPhoto_path());
		try {
			URL url = new URL(Application.SPOTIFY_DEFAULT_HOST+"/v1/me/playlists");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", stb.getToken_type()+" "+stb.getAccess_token());
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response = "";
			String playlists="";
			while((response=br.readLine()) != null) {
				playlists+=response;
			}
			model.addAttribute("playlists",playlists);
			System.out.println(playlists);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		model.addAttribute("user",user);
		return "me";
	}
	@RequestMapping("/profile/{userID}/attending")
	public String attendingProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> attendingConcertList = ConcertOperations.getAttendingConcerts(userID);
		model.addAttribute("page","1");
		model.addAttribute("user",user);
		model.addAttribute("concertList",attendingConcertList);
		return "profile";
	}
	@RequestMapping("/profile/{userID}/attended")
	public String attendedProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> attendedConcertList = ConcertOperations.getAttendedConcerts(userID);
		model.addAttribute("page","2");
		model.addAttribute("user",user);
		model.addAttribute("concertList",attendedConcertList);
		return "profile";
	}
	@RequestMapping("/profile/{userID}/thinking")
	public String thinkingProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> thinkingConcertList = ConcertOperations.getThinkingConcerts(userID);
		model.addAttribute("page","3");
		model.addAttribute("user",user);
		model.addAttribute("concertList",thinkingConcertList);
		return "profile";
	}
	
	
	
}
