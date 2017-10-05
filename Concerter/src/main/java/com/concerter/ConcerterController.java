package com.concerter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConcerterController {
    @RequestMapping(value="/")
    public String home(){
        return "first";
    }


}
