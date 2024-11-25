package com.example.strava_project_uni.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.strava_project_uni.dao.UserRepository;
import com.example.strava_project_uni.entity.User;

@Service
public class LoginService {
	
	private final UserRepository userRepository;
	
	private static Map<String, User> tokenMap = new HashMap<>();
	
	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Optional<String> login(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if (user.isPresent() && user.get().checkPassword(password)) {
			String token = generateToken();
			tokenMap.put(token, user.get());
			return Optional.of(token);
		}
		return Optional.empty();
	}
	
    private static synchronized String generateToken() {
        return Long.toHexString(System.currentTimeMillis());
    }
    
	public Optional<Boolean> logout(String token) {
    	if (tokenMap.containsKey(token)) {
            tokenMap.remove(token);
            return Optional.of(true);
        }
        return Optional.empty();
	}
    
	public User getUserByToken(String token) {
		return tokenMap.get(token);
	}
    
}
