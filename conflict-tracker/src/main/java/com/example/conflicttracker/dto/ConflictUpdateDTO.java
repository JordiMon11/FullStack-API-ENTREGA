package com.example.conflicttracker.dto;

import com.example.conflicttracker.model.ConflictStatus;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO para actualizar un Conflict existente (PUT)
 */
public class ConflictUpdateDTO {
    private String name;
    private LocalDate startDate;
    private ConflictStatus status;
    private String description;
    private Set<Long> countryIds;

    // Constructores
    public ConflictUpdateDTO() {}

    public ConflictUpdateDTO(String name, LocalDate startDate, ConflictStatus status, 
                            String description, Set<Long> countryIds) {
        this.name = name;
        this.startDate = startDate;
        this.status = status;
        this.description = description;
        this.countryIds = countryIds;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ConflictStatus getStatus() {
        return status;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(Set<Long> countryIds) {
        this.countryIds = countryIds;
    }
}
