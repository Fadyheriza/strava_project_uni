package com.example.strava_project_uni.service;


import com.example.strava_project_uni.dao.UserRepository;
import com.example.strava_project_uni.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}

    public User save(User user) {
        return userRepository.save(user);
    }
}