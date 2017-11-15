package boun.group9.backend.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.CommentOperations;

@Controller
public class CommentController {

    private static Application.STATUS status;
    @RequestMapping(value="/new-comment",method= RequestMethod.POST)
    public String submitNewComment(@ModelAttribute Comments comment) {
        System.out.println(comment.getCommented_by());
        System.out.println(comment.getComment());
        System.out.println(comment.getDown_votes());
        System.out.println(comment.getUp_votes());
        System.out.println(comment.getConcert_id());
        status = CommentOperations.createComment(comment);
        if(status == Application.STATUS.SUCCESS) {
            return "concert";
        }else {
            return "error";
        }
    }
    /*
    @RequestMapping("/getAllComments/{concertID}")
    public String getAllCommentsByConcertID(@PathVariable("concertID") int concertID, Model model) {
        ArrayList<Comments> resultList=CommentOperations.getCommentsByConcertID(concertID);
        Concerts concert=ConcertOperations.getConcert(concertID);
        model.addAttribute("page","1");
        model.addAttribute("concert",concert);
        model.addAttribute("commentList",resultList);
        return "comments";
    }
*/
    

    //METODU OLUÅ�TURDUM.
    @RequestMapping(value = "/comments/{commentID}/deleteComment", method=RequestMethod.GET)
    public String deleteComment( @PathVariable("commentID") int commentID , HttpSession session){
    	
    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
    	System.out.println("asdasdas");
        status = CommentOperations.deleteComment(commentID, userID);
        System.out.println(status);
        if(status == Application.STATUS.SUCCESS){
            return "deneme";
        }else{
            return "error";
        }

    }
    
    //METODU OLUÅ�TURDUM.
    @RequestMapping(value = "/comments/{commentID}/upVote", method = RequestMethod.GET)
    public String likeComment(@PathVariable("commentID") int commentID , HttpSession session){

    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
        status = CommentOperations.upVoteForComment(commentID, userID);
        System.out.println(status);
        if (status == Application.STATUS.SUCCESS){
            return "deneme";
        }else{
            return "error";
        }
    }
    
    //METODU OLUŞTURDUM.
    @RequestMapping(value = "/comments/{commentID}/downVote" , method = RequestMethod.GET)
    public String unlikeComment(@PathVariable("commentID") int commentID, HttpSession session) {

    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
    	status = CommentOperations.downVoteForComment(commentID,userID);
    	if(status == Application.STATUS.SUCCESS) {
    		return "deneme"; 
    	}else {
    		return "error";
    	}
    }


}
