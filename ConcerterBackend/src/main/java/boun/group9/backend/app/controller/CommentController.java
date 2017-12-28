package boun.group9.backend.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.CommentOperations;
@Controller
public class CommentController {
	private static Application.STATUS status;
	@RequestMapping(value="/new-comment",method=RequestMethod.POST)
	public ModelAndView createComment(@ModelAttribute Comments newComment, HttpSession session) {
		ModelAndView model=new ModelAndView("redirect:/index");
		Users loggedInUser = (Users)session.getAttribute("loggedInUser");
		newComment.setCommented_by(loggedInUser.getId());
		Application.STATUS status = CommentOperations.createCommentwithCategory(newComment);
		if(status==Application.STATUS.ERROR) {
			return new ModelAndView("redirect:/error");
		}
		
		return model;
	}
	
	@RequestMapping(value = "/deleteComment", method=RequestMethod.GET)
    public ModelAndView deleteComment( @RequestParam(name="commentID") String commentID , HttpSession session){
    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
    	int commentid=Integer.parseInt(commentID);
    	System.out.println("Deleting comment id: "+commentid);
        status = CommentOperations.deleteComment(commentid, userID);
        System.out.println(status);
        if(status == Application.STATUS.SUCCESS){
        	System.out.println(commentID);
        	return new ModelAndView("redirect:/index");
        }else{
            return new ModelAndView("redirect:/error");
        }

    }
	 @RequestMapping(value = "/upVote", method = RequestMethod.GET)
	    public ModelAndView likeComment(@RequestParam(name="commentID") String commentID , HttpSession session){

	    	Users user = (Users)session.getAttribute("loggedInUser");
	    	int userID = user.getId();
	    	int commentid=Integer.parseInt(commentID);
	        status = CommentOperations.upVoteForComment(commentid, userID);
	        System.out.println(status);
	        if (status == Application.STATUS.SUCCESS){
	        	return new ModelAndView("redirect:/index");
	        }else{
	        	return new ModelAndView("redirect:/error");
	        }
	    }
	 
	 @RequestMapping(value = "/downVote" , method = RequestMethod.GET)
	    public ModelAndView unlikeComment(@RequestParam(name="commentID") String commentID, HttpSession session) {

	    	Users user = (Users)session.getAttribute("loggedInUser");
	    	int userID = user.getId();
	    	int commentid=Integer.parseInt(commentID);
	    	status = CommentOperations.downVoteForComment(commentid,userID);
	    	if(status == Application.STATUS.SUCCESS) {
	    		return new ModelAndView("redirect:/index"); 
	    	}else {
	    		return new ModelAndView("redirect:/error");
	    	}
	    }
	
	
	
	
	
}
