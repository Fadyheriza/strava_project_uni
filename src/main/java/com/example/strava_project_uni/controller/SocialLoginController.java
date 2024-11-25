package com.example.strava_project_uni.controller;


import com.example.strava_project_uni.entity.User;
import com.example.strava_project_uni.repository.UserRepository;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class SocialLoginController {

    private final UserRepository userRepository;

    public SocialLoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/success")
    public String handleLoginSuccess(OAuth2AuthenticationToken authentication) {
        String email = authentication.getPrincipal().getAttribute("email");
        String name = authentication.getPrincipal().getAttribute("name");

        // Check if user exists, else save
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            return userRepository.save(newUser);
        });

        return "Welcome, " + user.getName() + "! You are logged in.";
    }

    @GetMapping("/failure")
    public String handleLoginFailure() {
        return "Social login failed. Please try again.";
    }
}
