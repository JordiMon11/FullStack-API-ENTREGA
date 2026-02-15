package com.example.conflicttracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.conflicttracker.model.*;
import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict,Long>{
    List<Conflict> findByStatus(ConflictStatus status);
}