package com.example.conflicttracker.mapper;

import com.example.conflicttracker.dto.*;
import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.model.Country;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidades Conflict y DTOs
 */
@Component
public class ConflictMapper {

    /**
     * Convierte una entidad Conflict a ConflictDTO
     */
    public ConflictDTO toDTO(Conflict conflict) {
        if (conflict == null) {
            return null;
        }

        Set<CountryDTO> countryDTOs = conflict.getCountries() != null 
            ? conflict.getCountries().stream()
                .map(this::countryToDTO)
                .collect(Collectors.toSet())
            : Set.of();

        return new ConflictDTO(
            conflict.getId(),
            conflict.getName(),
            conflict.getStartDate(),
            conflict.getStatus(),
            conflict.getDescription(),
            countryDTOs
        );
    }

    /**
     * Convierte un ConflictCreateDTO a entidad Conflict
     * Los países se deben asignar posteriormente en el servicio
     */
    public Conflict toEntity(ConflictCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Conflict conflict = new Conflict();
        conflict.setName(dto.getName());
        conflict.setStartDate(dto.getStartDate());
        conflict.setStatus(dto.getStatus());
        conflict.setDescription(dto.getDescription());
        return conflict;
    }

    /**
     * Actualiza una entidad Conflict existente con datos de ConflictUpdateDTO
     */
    public void updateEntityFromDTO(Conflict conflict, ConflictUpdateDTO dto) {
        if (conflict == null || dto == null) {
            return;
        }

        conflict.setName(dto.getName());
        conflict.setStartDate(dto.getStartDate());
        conflict.setStatus(dto.getStatus());
        conflict.setDescription(dto.getDescription());
        // Los países se actualizan en el servicio
    }

    /**
     * Convierte Country a CountryDTO
     */
    private CountryDTO countryToDTO(Country country) {
        if (country == null) {
            return null;
        }
        return new CountryDTO(country.getId(), country.getName(), country.getCode());
    }
}
