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




}
