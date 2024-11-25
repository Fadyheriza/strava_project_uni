package com.example.strava_project_uni.repository;


import com.example.strava.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    List<TrainingSession> findByUserId(Long userId);
}