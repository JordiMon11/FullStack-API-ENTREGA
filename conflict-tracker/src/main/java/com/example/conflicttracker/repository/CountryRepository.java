package com.example.conflicttracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.conflicttracker.model.Country;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Long>{
    Optional<Country> findByCode(String code);
}