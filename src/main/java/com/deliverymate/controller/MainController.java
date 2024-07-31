package com.deliverymate.controller;

import com.deliverymate.domain.CartDTO;
import com.deliverymate.domain.StoreDTO;
import com.deliverymate.domain.UserDTO;
import com.deliverymate.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/main")
    public String get_main() {
        return "public/main";
    }

    @GetMapping("/index")
    public void get_test(Model model){
        CartDTO cartDTO1 = new CartDTO();
        cartDTO1.setCount(1);
        CartDTO cartDTO2 = new CartDTO();
        cartDTO2.setCount(2);
        CartDTO cartDTO3 = new CartDTO();
        cartDTO3.setCount(3);

        int sum = 0;
        for (int i = 1; i <= 3; i++){
            sum = sum + i;
        }


        model.addAttribute("carts", List.of(cartDTO1, cartDTO2, cartDTO3));
        model.addAttribute("nums", List.of(1, 2, 3));
    }



}
