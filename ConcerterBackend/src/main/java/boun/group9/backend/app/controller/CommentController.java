package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.CommentOperations;
import boun.group9.backend.app.helper.ConcertOperations;
@Controller
public class CommentController {

    private static Application.STATUS status;
    
    @RequestMapping(value="/newcomment/{category}",method= RequestMethod.POST)
    public ModelAndView NewComment(@PathVariable("category") int category,@ModelAttribute Comments newComment,HttpSession session) {
    	Users user = (Users)session.getAttribute("loggedInUser");  	
    	if(user==null){
    		return new ModelAndView("/login");
    	}
    	newComment.setCommented_by(user.getId());
    	newComment.setCategory(category);
    	System.out.println(newComment.getId());;
        System.out.println(newComment.getCommented_by());
        System.out.println(newComment.getComment());
        System.out.println(newComment.getDown_votes());
        System.out.println(newComment.getUp_votes());
        System.out.println(newComment.getConcert_id());
        System.out.println(newComment.getCategory());
        status = CommentOperations.createCommentwithCategory(newComment);
        if(status == Application.STATUS.SUCCESS) {
        	return new ModelAndView("redirect:/index");
        }else {
        	return new ModelAndView("redirect:/error");
        }
    }
    
  
    @RequestMapping(value = "/comments/{commentID}/deleteComment", method=RequestMethod.GET)
    public String deleteComment( @PathVariable("commentID") int commentID , HttpSession session){
    	
    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
    	System.out.println("asdasdas");
        status = CommentOperations.deleteComment(commentID, userID);
        System.out.println(status);
        if(status == Application.STATUS.SUCCESS){
            return "redirect/:concert";
        }else{
            return "error";
        }

    }
    
    
    @RequestMapping(value = "/comments/{commentID}/upVote", method = RequestMethod.GET)
    public String likeComment(@PathVariable("commentID") int commentID , HttpSession session){

    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
        status = CommentOperations.upVoteForComment(commentID, userID);
        System.out.println(status);
        if (status == Application.STATUS.SUCCESS){
            return "redirect/:concert";
        }else{
            return "error";
        }
    }
    
    
    @RequestMapping(value = "/comments/{commentID}/downVote" , method = RequestMethod.GET)
    public String unlikeComment(@PathVariable("commentID") int commentID, HttpSession session) {

    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
    	status = CommentOperations.downVoteForComment(commentID,userID);
    	if(status == Application.STATUS.SUCCESS) {
    		return "redirect/:concert"; 
    	}else {
    		return "error";
    	}
    }

    @RequestMapping(value = "/concerts/{concertID}/comments/{commentCategory}", method = RequestMethod.GET)
    public String getCommentsByCategory(Model model ,@PathVariable("concertID") int concertID, @PathVariable("commentCategory") int category) {
        ArrayList<Comments> resultList;
        resultList = CommentOperations.getCommentsByCategory(concertID, category);
        Concerts concert= ConcertOperations.getConcert(concertID);
        
        for(Comments c : resultList) {
            System.out.println(c.getId() + " , " + c.getCategory());
        }
        
        model.addAttribute("page","1");
        model.addAttribute("concert", concert);
        model.addAttribute("commentList",resultList);
        return "redirect/:concert";
    }
    

    

}
