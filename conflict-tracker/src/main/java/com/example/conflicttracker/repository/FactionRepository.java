package com.example.conflicttracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.conflicttracker.model.Faction;

public interface FactionRepository extends JpaRepository<Faction,Long>{}