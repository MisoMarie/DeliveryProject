package com.deliverymate.controller;


import com.deliverymate.domain.CartDTO;
import com.deliverymate.domain.FoodDTO;
import com.deliverymate.domain.StoreDTO;
import com.deliverymate.domain.UserDTO;
import com.deliverymate.service.UserService;
import jakarta.mail.Store;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Controller
@RequestMapping("/user")
public class CartController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public void test(String data, Model model){
        model.addAttribute("data", data);
    }

    /************************ 장바구니 ********************/
    // 장바구니 화면으로 이동.
    @GetMapping("/cart")
    public void get_user_cart(
            @AuthenticationPrincipal UserDTO user,
            Model model
    ){
        List<CartDTO> carts = new ArrayList<>();
        carts = userService.get_carts(user);
        model.addAttribute("carts", carts);
        System.out.println(carts);
    }

    // 장바구니에 상품을 추가
    @PostMapping("/cart")
    public ResponseEntity<Void> post_user_cart(
            @AuthenticationPrincipal UserDTO user,
             CartDTO cart
    ){
        log.info(cart);
        cart.setUser(user);
        userService.add_cart(cart);
        // 장바구니 삽입 성공
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // 장바구니에 담기는 음식의 가게 정보 비교
    @ResponseBody
    @GetMapping("/cart/duplicate")
    public Boolean get_foodId(
            @RequestParam Integer storeNo,
            @AuthenticationPrincipal UserDTO loginUser
    ){
        List<CartDTO> carts = userService.get_carts(loginUser);
        if(carts == null || carts.size() == 0) {
            return true; // 장바구니에 담기가 가능
        }
        CartDTO cart = carts.get(0);
        StoreDTO cartStore = cart.getStore();
        if(cartStore.getStoreNo().equals(storeNo)){//foodId에 해당하는 상점의 no){
            return Boolean.TRUE; // 가능
        }else{
            return Boolean.FALSE; // 담을 수 없음
        }
    }

    // 장바구니 제거
    @ResponseBody
    @DeleteMapping("/cart")
    public ResponseEntity<Void> delete_user_cart(
            @AuthenticationPrincipal UserDTO loginUser
    ){
        List<CartDTO> carts = userService.get_carts(loginUser);
        log.info(loginUser);
        userService.delete_cart(carts);
        return ResponseEntity.ok().body(null);
    }

    // 장바구니에 있는 음식 주문
    @PostMapping("/order")
    public void post_user_order(
            @RequestBody List<CartDTO> carts,
            HttpSession session
    ){
        log.info(carts + "등록중..");
        session.setAttribute("carts", carts);
    }





    





}
