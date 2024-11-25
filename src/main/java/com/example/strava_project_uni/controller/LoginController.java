package com.example.strava_project_uni.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.strava_project_uni.dto.UserDTO;
import com.example.strava_project_uni.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Login Controller", description = "Login and logout operations")
public class LoginController {
	
    private LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
    
    @Operation(
    	summary = "Login to the system",
        description = "Allows a user to login by providing email and password. Returns a token if successful.",
        responses = {
        	@ApiResponse(responseCode = "200", description = "Login successful"),
        	@ApiResponse(responseCode = "401", description = "Invalid credentials, login failed"),
        }
    )
	
	@PostMapping("/login")
	public ResponseEntity<String> Login(
		@Parameter(name = "credentials", description = "User's credentials", required = true)    	
		@RequestBody UserDTO user) {    	
		Optional<String> token = loginService.login(user.getEmail(), user.getPassword());
		if (token.isPresent()) {
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.badRequest().body("Login failed");
		}
	}
	
    // Logout endpoint
    @Operation(
        summary = "Logout from the system",
        description = "Allows a user to logout by providing the authorization token.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Logout successful"),
            @ApiResponse(responseCode = "401", description = "Invalid token, logout failed"),
        }
    )    
	
	@PostMapping("/logout")    
	public ResponseEntity<Void> logout(
		@Parameter(name = "token", description = "Authorization token", required = true, example = "19248884055")
		@RequestBody String token) {    	
	    Optional<Boolean> result = loginService.logout(token);
	    	
	    if (result.isPresent() && result.get()) {
	        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	        } else {
	        	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	        }        
	    }
}
