package com.example.strava_project_uni.service;

import com.example.strava_project_uni.entity.TrainingSession;
import com.example.strava_project_uni.repository.TrainingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingSessionService {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    public TrainingSession createTrainingSession(TrainingSession session, OAuth2User user) {
        String email = user.getAttribute("email");
        session.setDescription(session.getDescription() + " (Created by: " + email + ")");
        return trainingSessionRepository.save(session);
    }

    public List<TrainingSession> getTrainingSessionsByUserId(Long userId) {
        return trainingSessionRepository.findByUserId(userId);
    }
}
