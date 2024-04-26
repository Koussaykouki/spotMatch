package com.example.spotmatch.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/home")
    @ResponseBody
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        return "Welcome, " + principal.getAttribute("name") + "!";
    }
}
