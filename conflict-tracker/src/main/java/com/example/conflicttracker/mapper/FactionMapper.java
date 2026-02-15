package com.example.conflicttracker.mapper;

import com.example.conflicttracker.dto.*;
import com.example.conflicttracker.model.Faction;
import com.example.conflicttracker.model.Country;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidades Faction y DTOs
 */
@Component
public class FactionMapper {

    /**
     * Convierte una entidad Faction a FactionDTO
     */
    public FactionDTO toDTO(Faction faction) {
        if (faction == null) {
            return null;
        }

        Set<CountryDTO> supporterDTOs = faction.getSupporters() != null
            ? faction.getSupporters().stream()
                .map(this::countryToDTO)
                .collect(Collectors.toSet())
            : Set.of();

        Long conflictId = faction.getConflict() != null ? faction.getConflict().getId() : null;
        String conflictName = faction.getConflict() != null ? faction.getConflict().getName() : null;

        return new FactionDTO(
            faction.getId(),
            faction.getName(),
            conflictId,
            conflictName,
            supporterDTOs
        );
    }

    /**
     * Convierte un FactionCreateDTO a entidad Faction
     * El conflicto y supporters se deben asignar en el servicio
     */
    public Faction toEntity(FactionCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Faction faction = new Faction();
        faction.setName(dto.getName());
        return faction;
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
