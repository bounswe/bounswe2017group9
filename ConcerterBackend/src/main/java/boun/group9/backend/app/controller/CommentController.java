package boun.group9.backend.app.controller;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.helper.CommentOperations;
import boun.group9.backend.app.helper.ConcertOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

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
<<<<<<< Updated upstream
*/


}
