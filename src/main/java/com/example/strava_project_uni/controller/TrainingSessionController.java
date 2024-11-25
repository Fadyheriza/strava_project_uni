package com.example.strava_project_uni.controller;

import com.example.strava_project_uni.entity.TrainingSession;
import com.example.strava_project_uni.service.TrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @PostMapping("/create")
    public TrainingSession createTrainingSession(@RequestBody TrainingSession session, @AuthenticationPrincipal OAuth2User user) {
        return trainingSessionService.createTrainingSession(session, user);
    }

    @GetMapping("/user")
    public String getLoggedInUserDetails(@AuthenticationPrincipal OAuth2User user) {
        return "Logged-in user: " + user.getAttribute("email");
    }

    @GetMapping("/user/{userId}")
    public List<TrainingSession> getTrainingSessionsByUser(@PathVariable Long userId) {
        return trainingSessionService.getTrainingSessionsByUserId(userId);
    }
}
