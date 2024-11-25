package com.example.strava_project_uni.dto;

import java.time.LocalDate;
import java.util.List;

public class ChallengeDTO {
	private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double targetDistance; // Target distance in kilometers (update in Swagger if needed)
    private String description;
    private List<String> acceptedUserEmails; // List of user emails instead of full user details

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getTargetDistance() { return targetDistance; }
    public void setTargetDistance(Double targetDistance) { this.targetDistance = targetDistance; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getAcceptedUserEmails() { return acceptedUserEmails; }
    public void setAcceptedUserEmails(List<String> acceptedUserEmails) { this.acceptedUserEmails = acceptedUserEmails; }
}
