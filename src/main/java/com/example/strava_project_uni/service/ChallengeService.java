package com.example.strava_project_uni.service;

import com.example.strava_project_uni.entity.Challenge;
import com.example.strava_project_uni.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge createChallenge(Challenge challenge, OAuth2User user) {
        // Optionally log or process the user data
        String email = user.getAttribute("email");
        challenge.setDescription(challenge.getDescription() + " (Created by: " + email + ")");
        return challengeRepository.save(challenge);
    }

    public List<Challenge> getActiveChallenges() {
        return challengeRepository.findByActiveTrue();
    }
}
