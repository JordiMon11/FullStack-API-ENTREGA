package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.ConflictCreateDTO;
import com.example.conflicttracker.dto.ConflictDTO;
import com.example.conflicttracker.dto.ConflictUpdateDTO;
import com.example.conflicttracker.model.ConflictStatus;
import com.example.conflicttracker.service.ConflictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de conflictos
 * Todos los endpoints bajo /api/v1/conflicts
 */
@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    private final ConflictService conflictService;

    public ConflictController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    /**
     * GET /api/v1/conflicts
     * GET /api/v1/conflicts?status=ACTIVE
     * Retorna todos los conflictos, opcionalmente filtrados por estado
     */
    @GetMapping
    public ResponseEntity<List<ConflictDTO>> getAllConflicts(
            @RequestParam(required = false) ConflictStatus status) {
        
        List<ConflictDTO> conflicts = status != null 
            ? conflictService.findByStatus(status)
            : conflictService.findAll();
        
        return ResponseEntity.ok(conflicts);
    }

    /**
     * GET /api/v1/conflicts/{id}
     * Retorna los detalles de un conflicto específico
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConflictDTO> getConflictById(@PathVariable Long id) {
        ConflictDTO conflict = conflictService.findById(id);
        return ResponseEntity.ok(conflict);
    }

    /**
     * POST /api/v1/conflicts
     * Crea un nuevo conflicto
     */
    @PostMapping
    public ResponseEntity<ConflictDTO> createConflict(@RequestBody ConflictCreateDTO createDTO) {
        ConflictDTO created = conflictService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/v1/conflicts/{id}
     * Actualiza un conflicto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConflictDTO> updateConflict(
            @PathVariable Long id,
            @RequestBody ConflictUpdateDTO updateDTO) {
        
        ConflictDTO updated = conflictService.update(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/v1/conflicts/{id}
     * Elimina un conflicto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConflict(@PathVariable Long id) {
        conflictService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
