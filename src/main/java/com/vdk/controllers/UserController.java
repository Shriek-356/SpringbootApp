package com.vdk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginView(Principal principal) {
        if(principal != null) {
            return "redirect:/"; //Chuyen huong ve trang chu neu da dang nhap roi
        }
        return "login";
    }
}
