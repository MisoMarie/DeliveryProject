package com.deliverymate.controller;

import com.deliverymate.domain.UserDTO;
import com.deliverymate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    @PostMapping("/mypage")
//    public String



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

    // id db에서 있나없나 확인하는 용도
    @GetMapping("/checkId")
    public ResponseEntity<Boolean> checkUserIdExists(@RequestParam String id) {
        boolean exists = userService.isUserIdExists(id);
        return ResponseEntity.ok(exists);
    }


    @GetMapping("/find")
    public String getFind() {
        return "user/find";
    }


    @GetMapping("/reset/password")
    public String get_reset_password(
            @RequestParam(required = false) String token,
            Model model
    ){
        if(token == null){
            return "user/reset_password";
        }

        Boolean result = userService.check_token_is_valid(token);
        if(result != null && result){
            System.out.println(token + " 이 유효함");
            model.addAttribute("token", token);
            model.addAttribute("success", true);
        }
        System.out.println(token + " 이 유효하지 않음");
        return "user/reset_password";
    }

    @PostMapping("/reset/password")
    public String post_reset_password(
            @RequestParam String token,
            @RequestParam String password,
            Model model
    ){
        Boolean result = userService.check_token_is_valid(token);
        if(result != null && result){
            userService.user_password_modify(token, password);
            model.addAttribute("success", true);
        }
        return "user/reset_password";
    }

}