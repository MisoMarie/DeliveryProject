package com.deliverymate.controller;

import com.deliverymate.domain.UserDTO;
import com.deliverymate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

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

   @PostMapping("/register")
    public String post_user_register(
            UserDTO userDTO,
            RedirectAttributes redirectAttributes
   ){
       System.out.println(userDTO);
       boolean result = userService.user_register(userDTO);

       // 회원가입 완료, 문제 없음.
       if(result){
           return "redirect:/user/login";
       }
       redirectAttributes.addFlashAttribute("certError", true);
       System.out.println("post_user_register 회원가입 시도 : " + userDTO);
       return "redirect:/user/register";
   }

}
