package com.example.conflicttracker.dto;

import java.util.Set;

/**
 * DTO para crear una nueva Faction (POST)
 */
public class FactionCreateDTO {
    private String name;
    private Long conflictId;
    private Set<Long> supporterIds; // IDs de países que apoyan esta facción

    // Constructores
    public FactionCreateDTO() {}

    public FactionCreateDTO(String name, Long conflictId, Set<Long> supporterIds) {
        this.name = name;
        this.conflictId = conflictId;
        this.supporterIds = supporterIds;
    }

    // Getters y Setters
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

    public Set<Long> getSupporterIds() {
        return supporterIds;
    }

    public void setSupporterIds(Set<Long> supporterIds) {
        this.supporterIds = supporterIds;
    }
}
