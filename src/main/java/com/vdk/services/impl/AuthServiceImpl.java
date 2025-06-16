package com.vdk.services.impl;

import com.vdk.pojo.User;
import com.vdk.repositories.UserRepository;
import com.vdk.services.AuthService;
import com.vdk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user!=null){
            if(!passwordEncoder.matches(password,user.getPassword())){
                throw new RuntimeException("Mật khẩu không đúng");
            }
            return true;
        }
        else{
            throw new RuntimeException("Khong tim thay user");
        }
    }
}
