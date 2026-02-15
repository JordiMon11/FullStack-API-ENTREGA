package com.example.conflicttracker.mapper;

import com.example.conflicttracker.dto.*;
import com.example.conflicttracker.model.Event;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades Event y DTOs
 */
@Component
public class EventMapper {

    /**
     * Convierte una entidad Event a EventDTO
     */
    public EventDTO toDTO(Event event) {
        if (event == null) {
            return null;
        }

        Long conflictId = event.getConflict() != null ? event.getConflict().getId() : null;
        String conflictName = event.getConflict() != null ? event.getConflict().getName() : null;

        return new EventDTO(
            event.getId(),
            event.getEventDate(),
            event.getLocation(),
            event.getDescription(),
            conflictId,
            conflictName
        );
    }

    /**
     * Convierte un EventCreateDTO a entidad Event
     * El conflicto se debe asignar en el servicio
     */
    public Event toEntity(EventCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Event event = new Event();
        event.setEventDate(dto.getEventDate());
        event.setLocation(dto.getLocation());
        event.setDescription(dto.getDescription());
        return event;
    }

    /**
     * Actualiza una entidad Event existente con datos de EventCreateDTO
     */
    public void updateEntityFromDTO(Event event, EventCreateDTO dto) {
        if (event == null || dto == null) {
            return;
        }

        event.setEventDate(dto.getEventDate());
        event.setLocation(dto.getLocation());
        event.setDescription(dto.getDescription());
        // El conflicto se actualiza en el servicio si es necesario
    }
}
