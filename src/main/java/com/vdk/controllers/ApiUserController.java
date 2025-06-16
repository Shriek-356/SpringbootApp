package com.vdk.controllers;

import com.vdk.pojo.User;
import com.vdk.services.AuthService;
import com.vdk.services.UserService;
import com.vdk.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    AuthService authService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;


    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> params) {
        try {
            if (authService.authenticate(params.get("username"), params.get("password"))) {
                User u = userService.getUserByUsername(params.get("username"));
                return ResponseEntity.ok().body(jwtUtil.generateToken(u.getUsername(), List.of(u.getRole())));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");

    }
    //@PreAuthorize("hasRole('Patient')")
    @GetMapping("/hello")
    public ResponseEntity<?> login(Principal principal) {
        try {
            User u = userService.getUserByUsername(principal.getName());
            return ResponseEntity.ok().body("Hello: "+u.getUsername() + u.getRole());
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
        }

    }*/

}
