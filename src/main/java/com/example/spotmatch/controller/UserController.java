package com.example.spotmatch.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // Endpoint to handle the redirection after login (specified as the defaultSuccessUrl in SecurityConfig)
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            // Add attributes to the model to be displayed in the view
            model.addAttribute("name", principal.getAttribute("display_name"));  // Adjust attribute keys based on what Spotify returns
            model.addAttribute("email", principal.getAttribute("email"));
        }
        return "home";  // This should correspond to a template named 'home.html' in your 'src/main/resources/templates' directory
    }

    // A simple root endpoint just to have a landing page if needed
    @GetMapping("/")
    public String index() {
        return "index";  // This should correspond to a template named 'index.html'
    }

    // Optional: a method for logging out, if you want to provide a manual trigger for logout via a URL
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";  // Redirects to the index page after logout
    }
}
