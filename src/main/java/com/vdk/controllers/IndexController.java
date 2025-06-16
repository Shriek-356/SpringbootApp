package com.vdk.controllers;
import com.vdk.pojo.User;
import com.vdk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        /*if (principal != null){
            User u = userService.getUserByUsername(principal.getName());
            model.addAttribute("currentUser", u);
        }*/
        return "index";
    }
}
