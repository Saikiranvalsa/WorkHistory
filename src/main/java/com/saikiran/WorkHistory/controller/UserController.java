package com.saikiran.WorkHistory.controller;

import com.saikiran.WorkHistory.dto.LoginDto;
import com.saikiran.WorkHistory.dto.SignupDto;
import com.saikiran.WorkHistory.model.User;
import com.saikiran.WorkHistory.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String  generateCsrf(){
        return "Token validated successfully";
    }
    @PostMapping("/signup")
    public User signup(@RequestBody SignupDto signupDto){
        return userService.signup(signupDto);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        return userService.verify(loginDto);
    }
}
