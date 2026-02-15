package com.example.conflicttracker.dto;

import java.util.Set;

/**
 * DTO para respuestas GET de Faction
 */
public class FactionDTO {
    private Long id;
    private String name;
    private Long conflictId;
    private String conflictName;
    private Set<CountryDTO> supporters;

    // Constructores
    public FactionDTO() {}

    public FactionDTO(Long id, String name, Long conflictId, String conflictName, Set<CountryDTO> supporters) {
        this.id = id;
        this.name = name;
        this.conflictId = conflictId;
        this.conflictName = conflictName;
        this.supporters = supporters;
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

    public Long getConflictId() {
        return conflictId;
    }

    public void setConflictId(Long conflictId) {
        this.conflictId = conflictId;
    }

    public String getConflictName() {
        return conflictName;
    }

    public void setConflictName(String conflictName) {
        this.conflictName = conflictName;
    }

    public Set<CountryDTO> getSupporters() {
        return supporters;
    }

    public void setSupporters(Set<CountryDTO> supporters) {
        this.supporters = supporters;
    }
}
