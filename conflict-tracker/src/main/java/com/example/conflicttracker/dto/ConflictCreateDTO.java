package com.example.conflicttracker.dto;

import com.example.conflicttracker.model.ConflictStatus;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO para crear un nuevo Conflict (POST)
 * No incluye ID (se genera automáticamente)
 */
public class ConflictCreateDTO {
    private String name;
    private LocalDate startDate;
    private ConflictStatus status;
    private String description;
    private Set<Long> countryIds; // IDs de los países involucrados

    // Constructores
    public ConflictCreateDTO() {}

    public ConflictCreateDTO(String name, LocalDate startDate, ConflictStatus status, 
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
