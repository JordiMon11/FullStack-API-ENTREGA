package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.FactionCreateDTO;
import com.example.conflicttracker.dto.FactionDTO;
import com.example.conflicttracker.mapper.FactionMapper;
import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.model.Country;
import com.example.conflicttracker.model.Faction;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.CountryRepository;
import com.example.conflicttracker.repository.FactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FactionService {

    private final FactionRepository factionRepository;
    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;
    private final FactionMapper factionMapper;

    public FactionService(FactionRepository factionRepository,
                         ConflictRepository conflictRepository,
                         CountryRepository countryRepository,
                         FactionMapper factionMapper) {
        this.factionRepository = factionRepository;
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
        this.factionMapper = factionMapper;
    }

    /**
     * Obtiene todas las facciones
     */
    public List<FactionDTO> findAll() {
        return factionRepository.findAll().stream()
            .map(factionMapper::toDTO)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene una facción por ID
     */
    public FactionDTO findById(Long id) {
        Faction faction = factionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id));
        return factionMapper.toDTO(faction);
    }

    /**
     * Crea una nueva facción
     */
    public FactionDTO create(FactionCreateDTO createDTO) {
        // Convertir DTO a entidad
        Faction faction = factionMapper.toEntity(createDTO);
        
        // Asignar conflicto
        if (createDTO.getConflictId() != null) {
            Conflict conflict = conflictRepository.findById(createDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + createDTO.getConflictId()));
            faction.setConflict(conflict);
        }
        
        // Asignar países que apoyan esta facción
        if (createDTO.getSupporterIds() != null && !createDTO.getSupporterIds().isEmpty()) {
            Set<Country> supporters = createDTO.getSupporterIds().stream()
                .map(id -> countryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Country not found with id: " + id)))
                .collect(Collectors.toSet());
            faction.setSupporters(supporters);
        }
        
        // Guardar y devolver DTO
        Faction saved = factionRepository.save(faction);
        return factionMapper.toDTO(saved);
    }

    /**
     * Actualiza una facción existente
     */
    public FactionDTO update(Long id, FactionCreateDTO updateDTO) {
        Faction faction = factionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id));
        
        // Actualizar nombre
        faction.setName(updateDTO.getName());
        
        // Actualizar conflicto
        if (updateDTO.getConflictId() != null) {
            Conflict conflict = conflictRepository.findById(updateDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + updateDTO.getConflictId()));
            faction.setConflict(conflict);
        }
        
        // Actualizar supporters
        if (updateDTO.getSupporterIds() != null) {
            Set<Country> supporters = updateDTO.getSupporterIds().stream()
                .map(supporterId -> countryRepository.findById(supporterId)
                    .orElseThrow(() -> new RuntimeException("Country not found with id: " + supporterId)))
                .collect(Collectors.toSet());
            faction.setSupporters(supporters);
        }
        
        Faction updated = factionRepository.save(faction);
        return factionMapper.toDTO(updated);
    }

    /**
     * Elimina una facción
     */
    public void delete(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new RuntimeException("Faction not found with id: " + id);
        }
        factionRepository.deleteById(id);
    }
}
