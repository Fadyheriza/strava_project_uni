package com.example.strava_project_uni.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TrainingSession> trainingSessions;

	public User() {
	}
	
    public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public boolean checkPassword(String password) {
        return this.password.equals(password);
	}
	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(Set<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }
}
