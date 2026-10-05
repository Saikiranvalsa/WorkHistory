package com.saikiran.WorkHistory.controller;

import com.saikiran.WorkHistory.dto.ProfileDto;
import com.saikiran.WorkHistory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ProfileController {
    @Autowired
    private UserService userService;
    @GetMapping("/profile")
    public ProfileDto getProfileInfo(Principal principal){
        return userService.getProfileInfo(principal);
    }
}
