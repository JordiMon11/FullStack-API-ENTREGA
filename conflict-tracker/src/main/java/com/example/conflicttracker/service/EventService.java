package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.EventCreateDTO;
import com.example.conflicttracker.dto.EventDTO;
import com.example.conflicttracker.mapper.EventMapper;
import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.model.Event;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final ConflictRepository conflictRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository,
                       ConflictRepository conflictRepository,
                       EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.conflictRepository = conflictRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * Obtiene todos los eventos
     */
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream()
            .map(eventMapper::toDTO)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene un evento por ID
     */
    public EventDTO findById(Long id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return eventMapper.toDTO(event);
    }

    /**
     * Crea un nuevo evento
     */
    public EventDTO create(EventCreateDTO createDTO) {
        // Convertir DTO a entidad
        Event event = eventMapper.toEntity(createDTO);
        
        // Asignar conflicto
        if (createDTO.getConflictId() != null) {
            Conflict conflict = conflictRepository.findById(createDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + createDTO.getConflictId()));
            event.setConflict(conflict);
        }
        
        // Guardar y devolver DTO
        Event saved = eventRepository.save(event);
        return eventMapper.toDTO(saved);
    }

    /**
     * Actualiza un evento existente
     */
    public EventDTO update(Long id, EventCreateDTO updateDTO) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        
        // Actualizar campos
        eventMapper.updateEntityFromDTO(event, updateDTO);
        
        // Actualizar conflicto si es necesario
        if (updateDTO.getConflictId() != null) {
            Conflict conflict = conflictRepository.findById(updateDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + updateDTO.getConflictId()));
            event.setConflict(conflict);
        }
        
        Event updated = eventRepository.save(event);
        return eventMapper.toDTO(updated);
    }

    /**
     * Elimina un evento
     */
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
}
