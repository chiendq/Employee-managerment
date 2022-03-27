package com.example.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    @RequestMapping("/join")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("/logout")
    public String handleError(){
        return "handleError";
    }
}
