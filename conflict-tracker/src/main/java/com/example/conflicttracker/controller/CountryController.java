package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.ConflictDTO;
import com.example.conflicttracker.service.ConflictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para consultas relacionadas con países
 */
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final ConflictService conflictService;

    public CountryController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    /**
     * GET /api/v1/countries/{code}/conflicts
     * Retorna todos los conflictos en los que está involucrado un país
     */
    @GetMapping("/{code}/conflicts")
    public ResponseEntity<List<ConflictDTO>> getConflictsByCountryCode(@PathVariable String code) {
        List<ConflictDTO> conflicts = conflictService.findByCountryCode(code);
        return ResponseEntity.ok(conflicts);
    }
}
