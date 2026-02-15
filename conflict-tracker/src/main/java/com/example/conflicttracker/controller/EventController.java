package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.EventCreateDTO;
import com.example.conflicttracker.dto.EventDTO;
import com.example.conflicttracker.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de eventos
 * Todos los endpoints bajo /api/v1/events
 */
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * GET /api/v1/events
     * Retorna todos los eventos
     */
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.findAll();
        return ResponseEntity.ok(events);
    }

    /**
     * GET /api/v1/events/{id}
     * Retorna los detalles de un evento específico
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        EventDTO event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    /**
     * POST /api/v1/events
     * Crea un nuevo evento
     */
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventCreateDTO createDTO) {
        EventDTO created = eventService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/v1/events/{id}
     * Actualiza un evento existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(
            @PathVariable Long id,
            @RequestBody EventCreateDTO updateDTO) {
        
        EventDTO updated = eventService.update(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/v1/events/{id}
     * Elimina un evento
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
