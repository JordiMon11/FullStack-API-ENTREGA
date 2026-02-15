package com.example.conflicttracker.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Faction {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Conflict conflict;

    @ManyToMany
    private Set<Country> supporters;

    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String n){this.name=n;}
    public Conflict getConflict(){return conflict;}
    public void setConflict(Conflict c){this.conflict=c;}
    public Set<Country> getSupporters(){return supporters;}
    public void setSupporters(Set<Country> s){this.supporters=s;}
}
