package com.example.strava_project_uni;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.strava_project_uni.dao.ChallengeRepository;
import com.example.strava_project_uni.dao.TrainingSessionRepository;
import com.example.strava_project_uni.dao.UserRepository;
import com.example.strava_project_uni.entity.Challenge;
import com.example.strava_project_uni.entity.TrainingSession;
import com.example.strava_project_uni.entity.User;

public class DataInitializer {
	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	
	@Bean
	CommandLineRunner initData(UserRepository userRepository, TrainingSessionRepository trainingSessionRepository, ChallengeRepository challengeRepository) {
        return args -> {
        	 if (trainingSessionRepository.count() > 0) {                
                 return;
             }			
 			
 			// Create some users
 			User asier = new User("asier@gmail.com", "1234", "Asier");
 			User giova = new User("giova@gmail.com", "1234", "Giova");
 			User mikel = new User("mikel@gmail.com", "1234", "Mikel");
 			// Save users
 			userRepository.saveAll(List.of(asier, giova, mikel));			
 			logger.info("Users saved!");
 			
 			// Create some categories
 			
 			TrainingSession ts1 = new TrainingSession(1L, "monday", "running and biking", LocalDateTime.of(2024, 2, 1, 12, 0) ,asier);
 			TrainingSession ts2 = new TrainingSession(2L, "tuesday", "biceps and triceps", LocalDateTime.of(2024, 2, 2, 12, 0) ,asier);
 			TrainingSession ts3 = new TrainingSession(3L, "thursday", "squats and press", LocalDateTime.of(2024, 2, 3, 12, 0) ,giova);
 			
 			// Save categories
 			trainingSessionRepository.saveAll(List.of(ts1, ts2, ts3));			
 			logger.info("Training Sessions saved!");


 			
 			// Articles of Electronics category
 			Challenge c1 = new Challenge("half marathon", "run half a marathon", LocalDateTime.of(2024, 1, 1,12,0), LocalDateTime.of(2024, 2, 1,12,0), true, asier);
 			Challenge c2 = new Challenge("full marathon", "run a full marathon", LocalDateTime.of(2024, 3, 1,12,0), LocalDateTime.of(2024, 4, 1,12,0), true, giova);
 		    Challenge c3 = new Challenge("triathlon", "run a triathlon", LocalDateTime.of(2024, 5, 1,12,0), LocalDateTime.of(2024, 6, 1,12,0), true, mikel);
 			
 			
             // Save articles
             challengeRepository.saveAll(List.of(c1, c2, c3));
             logger.info("Challenges saved!");						
 		};
        };
}
