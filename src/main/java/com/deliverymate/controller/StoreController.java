package com.deliverymate.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("/filter")
    public String get_flter(){
        return "/filter";
    }

    @GetMapping("/store")
    public String get_store(){
        return "/store";
    }

}
