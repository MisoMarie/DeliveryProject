package com.deliverymate.controller;

import com.deliverymate.domain.UserDTO;
import com.deliverymate.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegister() {

        return "user/register";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "user/login";
    }

    @GetMapping("/mypage")
    public String getMypage() {
        return "user/mypage";
   }

    @PostMapping("/register")
    public String registerUser(
            UserDTO user
//          ,BindingResult bindingResult
//            @RequestParam("id") String id,
//            @RequestParam("password") String password,
//            @RequestParam("phone") String phone,
//            @RequestParam("email") String email,
//            @RequestParam("name") String name,
//            @RequestParam("nickname") String nickname,
    ) {
//        if(bindingResult.hasErrors()) {
//
//        }
        try {
//            UserDTO user = new UserDTO();
//            user.setId(id);
//            user.setPassword(password);
//            user.setPhone(phone);
//            user.setEmail(email);
//            user.setName(name);
//            user.setNickname(nickname);
//            user.setImage(image.getBytes());
            userService.registerUser(user);
            return "redirect:/user/login";
        } catch (Exception e) {
            e.printStackTrace();
            return "register";
        }
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

    // id db에서 있나없나 확인하는 용도
    @GetMapping("/checkId")
    public ResponseEntity<Boolean> checkUserIdExists(@RequestParam String id) {
        boolean exists = userService.isUserIdExists(id);
        return ResponseEntity.ok(exists);
    }
}
