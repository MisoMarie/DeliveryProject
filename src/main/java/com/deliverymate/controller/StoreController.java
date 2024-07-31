package com.deliverymate.controller;


import com.deliverymate.domain.FoodDTO;
import com.deliverymate.domain.ReviewDTO;
import com.deliverymate.domain.StoreDTO;
import com.deliverymate.domain.UserDTO;
import com.deliverymate.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private MainService mainService;

    @GetMapping("/filter")
    public String get_flter(){
        return "/filter";
    }

    @GetMapping("/store")
    public String get_store(){
        return "/store";
    }

    @GetMapping("/filter/{category}")
    public String get_filter(
            @PathVariable("category") String category,
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ) {
        List<StoreDTO> store = mainService.get_filter(category,userDTO);
        model.addAttribute("store", store);
        return "filter";
    }

    @GetMapping("/filter/store/{storeNo}")
    public String get_store(
            @AuthenticationPrincipal UserDTO userDTO,
            @PathVariable("storeNo") Integer storeNo,
            Model model
    ) {
        StoreDTO store = mainService.get_store(storeNo, userDTO);
        model.addAttribute("store", store);
        return "store";
    }

    @GetMapping("/review/{storeNo}")
    public ResponseEntity<List<ReviewDTO>> get_reviews(
            @PathVariable("storeNo") Integer storeNo,
            @RequestParam("order") String order
    ) {
        List<ReviewDTO> reviews = mainService.get_store_reviews(storeNo, order);
        return ResponseEntity.ok(reviews);
    }

    // 사용자는  foodId를 전달해줄 것이다.
    // 그럼 해당 foodId의 상점 정보를 조회 => 그래야 이 음식이 어느 상점것인지 아니까
    // 그리고 현재 장바구니에 있는 아무 음식이나 하나 가져온다 => 왜? 해당 장바구니의 상점 정보를 알아야 하니까
    // 두 상점을 비교한다
    //  => 다르면, 장바구니에 그냥 못넣는다고 해줌
    //  => 같으면,



}
