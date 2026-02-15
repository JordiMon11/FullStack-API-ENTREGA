package com.example.conflicttracker.dto;

import java.time.LocalDate;

/**
 * DTO para crear un nuevo Event (POST)
 */
public class EventCreateDTO {
    private LocalDate eventDate;
    private String location;
    private String description;
    private Long conflictId;

    // Constructores
    public EventCreateDTO() {}

    public EventCreateDTO(LocalDate eventDate, String location, String description, Long conflictId) {
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.conflictId = conflictId;
    }

    // Getters y Setters
    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getConflictId() {
        return conflictId;
    }

    public void setConflictId(Long conflictId) {
        this.conflictId = conflictId;
    }
}
