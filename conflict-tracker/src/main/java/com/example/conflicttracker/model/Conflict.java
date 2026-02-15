package com.example.conflicttracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Conflict {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private LocalDate startDate;
    @Enumerated(EnumType.STRING)
    private ConflictStatus status;
    @Column(length = 2000)
    private String description;

    @ManyToMany
    private Set<Country> countries;

    // getters/setters
    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String n){this.name=n;}
    public LocalDate getStartDate(){return startDate;}
    public void setStartDate(LocalDate d){this.startDate=d;}
    public ConflictStatus getStatus(){return status;}
    public void setStatus(ConflictStatus s){this.status=s;}
    public String getDescription(){return description;}
    public void setDescription(String d){this.description=d;}
    public Set<Country> getCountries(){return countries;}
    public void setCountries(Set<Country> c){this.countries=c;}
}
