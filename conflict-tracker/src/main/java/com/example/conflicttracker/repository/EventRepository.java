package com.example.conflicttracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.conflicttracker.model.Event;

public interface EventRepository extends JpaRepository<Event,Long>{}