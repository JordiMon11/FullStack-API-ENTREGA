package com.example.conflicttracker.dto;

import com.example.conflicttracker.model.ConflictStatus;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO para respuestas GET de Conflict
 * Incluye toda la información del conflicto
 */
public class ConflictDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private ConflictStatus status;
    private String description;
    private Set<CountryDTO> countries;

    // Constructores
    public ConflictDTO() {}

    public ConflictDTO(Long id, String name, LocalDate startDate, ConflictStatus status, 
                       String description, Set<CountryDTO> countries) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.status = status;
        this.description = description;
        this.countries = countries;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<CountryDTO> getCountries() {
        return countries;
    }

    public void setCountries(Set<CountryDTO> countries) {
        this.countries = countries;
    }
}
