package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class helloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        int size=0;
        size= Integer.parseInt(request.getParameter("pageSize"));
        //size=pageSize;
        System.out.println("size=="+size);
        return "ok";
    }
}
