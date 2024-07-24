package com.deliverymate.controller;

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

    @GetMapping("/filter/{category}")
    public String get_filter(
            @PathVariable("category") String category,
            Model model
    ) {
        List<StoreDTO> store = mainService.get_filter(category);
        model.addAttribute("store", store);
        return "filter";
    }

    @GetMapping("/store/{storeNo}")
    public String get_store(
            @PathVariable("storeNo") Integer storeNo,
            Model model
    ) {
        StoreDTO store = mainService.get_store(storeNo);
        model.addAttribute("store", store);
        System.out.println(store);
        return "store";
    }


}