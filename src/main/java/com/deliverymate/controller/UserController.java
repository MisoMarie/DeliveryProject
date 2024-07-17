package com.deliverymate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public String get_register(){
        return "user/register";
    }

    @GetMapping("/login")
    public String get_login(){
        return "user/login";
    }

   @GetMapping("/mypage")
    public String get_mypage(){
        return "user/mypage";
   }

}
