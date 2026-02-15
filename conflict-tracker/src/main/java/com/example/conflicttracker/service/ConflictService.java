package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.*;
import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.model.ConflictStatus;
import com.example.conflicttracker.model.Country;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConflictService {
    
    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;
    private final ConflictMapper conflictMapper;

    public ConflictService(ConflictRepository conflictRepository, 
                          CountryRepository countryRepository,
                          ConflictMapper conflictMapper) {
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
        this.conflictMapper = conflictMapper;
    }

    /**
     * Obtiene todos los conflictos
     */
    public List<ConflictDTO> findAll() {
        return conflictRepository.findAll().stream()
            .map(conflictMapper::toDTO)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene un conflicto por ID
     */
    public ConflictDTO findById(Long id) {
        Conflict conflict = conflictRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + id));
        return conflictMapper.toDTO(conflict);
    }

    /**
     * Crea un nuevo conflicto
     */
    public ConflictDTO create(ConflictCreateDTO createDTO) {
        // Convertir DTO a entidad
        Conflict conflict = conflictMapper.toEntity(createDTO);
        
        // Asignar países
        if (createDTO.getCountryIds() != null && !createDTO.getCountryIds().isEmpty()) {
            Set<Country> countries = createDTO.getCountryIds().stream()
                .map(id -> countryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Country not found with id: " + id)))
                .collect(Collectors.toSet());
            conflict.setCountries(countries);
        }
        
        // Guardar y devolver DTO
        Conflict saved = conflictRepository.save(conflict);
        return conflictMapper.toDTO(saved);
    }

    /**
     * Actualiza un conflicto existente
     */
    public ConflictDTO update(Long id, ConflictUpdateDTO updateDTO) {
        Conflict conflict = conflictRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + id));
        
        // Actualizar campos básicos
        conflictMapper.updateEntityFromDTO(conflict, updateDTO);
        
        // Actualizar países
        if (updateDTO.getCountryIds() != null) {
            Set<Country> countries = updateDTO.getCountryIds().stream()
                .map(countryId -> countryRepository.findById(countryId)
                    .orElseThrow(() -> new RuntimeException("Country not found with id: " + countryId)))
                .collect(Collectors.toSet());
            conflict.setCountries(countries);
        }
        
        Conflict updated = conflictRepository.save(conflict);
        return conflictMapper.toDTO(updated);
    }

    /**
     * Elimina un conflicto
     */
    public void delete(Long id) {
        if (!conflictRepository.existsById(id)) {
            throw new RuntimeException("Conflict not found with id: " + id);
        }
        conflictRepository.deleteById(id);
    }

    /**
     * Busca conflictos por estado
     */
    public List<ConflictDTO> findByStatus(ConflictStatus status) {
        return conflictRepository.findByStatus(status).stream()
            .map(conflictMapper::toDTO)
            .collect(Collectors.toList());
    }

    /**
     * Busca conflictos por código de país
     */
    public List<ConflictDTO> findByCountryCode(String code) {
        Country country = countryRepository.findByCode(code)
            .orElseThrow(() -> new RuntimeException("Country not found with code: " + code));
        
        return conflictRepository.findAll().stream()
            .filter(conflict -> conflict.getCountries() != null && 
                               conflict.getCountries().contains(country))
            .map(conflictMapper::toDTO)
            .collect(Collectors.toList());
    }
}
