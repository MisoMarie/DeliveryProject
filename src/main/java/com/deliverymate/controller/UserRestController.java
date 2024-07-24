package com.deliverymate.controller;


import com.deliverymate.domain.StoreDTO;
import com.deliverymate.domain.UserDTO;
import com.deliverymate.service.UserEmailService;
import com.deliverymate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired private UserService userService;
    @Autowired private UserEmailService userEmailService;


    /****************** mail(유저 아이디/비밀번호 찾기) **********************/
    @GetMapping("/find/id")
    public ResponseEntity<String> get_find_user_id(
            @RequestParam("email") String userEmail
    ){
        Boolean result = userEmailService.find_user_id(userEmail);
        if(result != null && result){
            return ResponseEntity.ok().body("아이디를 이메일로 전송하였습니다");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에 문제가 발생했습니다. 나중에 다시 시도해주세요");
    }

    // 아이디와 이메일을 받아서 해당 유저의 비밀번호를 reset 할 수 있는 재설정 링크를 이메일로 전송
    @GetMapping("/find/pw")
    public ResponseEntity<String> get_reset_user_pw(
            @RequestParam("id") String userId,
            @RequestParam("email") String userEmail
    ){
        Boolean result = userEmailService.reset_user_password(userId, userEmail);
        if(result != null && result){
            return ResponseEntity.ok().body("재설정 링크를 이메일로 전송하였습니다");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에 문제가 발생했습니다. 나중에 다시 시도해주세요");
    }

    /****************** wishlist **********************/
    @GetMapping("/wishlist")
    public ResponseEntity<List<StoreDTO>> get_user_wishlists(
            @AuthenticationPrincipal UserDTO userDTO
    ){
        // 유저가 좋아요 한 방 정보들을 전부 가져온다
        List<StoreDTO> stores = userService.get_user_wishlist_with_stores(userDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }

    @PostMapping("/wishlist/{storeNo}")
    public ResponseEntity<Void> post_user_wishlist(
            @AuthenticationPrincipal UserDTO userDTO,
            @PathVariable("storeNo") Integer storeNo
    ){
        userService.add_user_wishlist(userDTO.getId(), storeNo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

    @DeleteMapping("/wishlist/{storeNo}")
    public ResponseEntity<Void> delete_user_wishlist(
            @AuthenticationPrincipal UserDTO userDTO,
            @PathVariable("storeNo") Integer storeNo
    ){
        userService.remove_user_wishlist(userDTO.getId(), storeNo);
        return ResponseEntity.ok().body(null);
    }

}