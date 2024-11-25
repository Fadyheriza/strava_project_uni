package com.example.strava_project_uni.controller;

import com.example.strava.entity.Challenge;
import com.example.strava.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/create")
    public Challenge createChallenge(@RequestBody Challenge challenge, @AuthenticationPrincipal OAuth2User user) {
        return challengeService.createChallenge(challenge, user);
    }

    @GetMapping("/active")
    public List<Challenge> getActiveChallenges() {
        return challengeService.getActiveChallenges();
    }

    @PostMapping("/accept")
    public String acceptChallenge(@RequestBody Challenge challenge, @AuthenticationPrincipal OAuth2User user) {
        String email = user.getAttribute("email");
        return "Challenge accepted by user: " + email;
    }
}
