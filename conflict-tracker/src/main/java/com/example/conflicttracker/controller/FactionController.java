package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.FactionCreateDTO;
import com.example.conflicttracker.dto.FactionDTO;
import com.example.conflicttracker.service.FactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de facciones
 * Todos los endpoints bajo /api/v1/factions
 */
@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    /**
     * GET /api/v1/factions
     * Retorna todas las facciones
     */
    @GetMapping
    public ResponseEntity<List<FactionDTO>> getAllFactions() {
        List<FactionDTO> factions = factionService.findAll();
        return ResponseEntity.ok(factions);
    }

    /**
     * GET /api/v1/factions/{id}
     * Retorna los detalles de una facción específica
     */
    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFactionById(@PathVariable Long id) {
        FactionDTO faction = factionService.findById(id);
        return ResponseEntity.ok(faction);
    }

    /**
     * POST /api/v1/factions
     * Crea una nueva facción
     */
    @PostMapping
    public ResponseEntity<FactionDTO> createFaction(@RequestBody FactionCreateDTO createDTO) {
        FactionDTO created = factionService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/v1/factions/{id}
     * Actualiza una facción existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(
            @PathVariable Long id,
            @RequestBody FactionCreateDTO updateDTO) {
        
        FactionDTO updated = factionService.update(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/v1/factions/{id}
     * Elimina una facción
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaction(@PathVariable Long id) {
        factionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
