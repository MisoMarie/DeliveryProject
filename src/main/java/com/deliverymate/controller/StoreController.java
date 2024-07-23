package com.deliverymate.controller;


import com.deliverymate.domain.StoreDTO;
import com.deliverymate.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StoreController {
    @Autowired
    FilterService filterService;

    @GetMapping("/filter")
    public String get_filter(
            @RequestParam(value = "storeName", required = false) String storeName,
            @RequestParam(value = "deliveryTime", required = false) String deliveryTime,
            @RequestParam(value = "deliveryTip", required = false) String deliveryTip,
            @RequestParam(value = "leastPrice", required = false) String leastPrice,
            Model model
    ){
        List<StoreDTO> stores = filterService.get_store(storeName, deliveryTime , deliveryTip , leastPrice);
        model.addAttribute("stores", stores);
        return "/filter";
    }

    @GetMapping("/store")
    public String get_store(){
        return "/store";
    }

}
