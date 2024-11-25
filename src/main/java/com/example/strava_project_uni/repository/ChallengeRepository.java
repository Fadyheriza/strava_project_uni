package com.example.strava_project_uni.repository;

import com.example.strava.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByActiveTrue();
    List<Challenge> findByCreatedById(Long createdById);
}