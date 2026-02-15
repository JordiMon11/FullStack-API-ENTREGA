package com.example.conflicttracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Event {
    @Id @GeneratedValue
    private Long id;
    private LocalDate eventDate;
    private String location;
    @Column(length=2000)
    private String description;

    @ManyToOne
    private Conflict conflict;

    public Long getId(){return id;}
    public LocalDate getEventDate(){return eventDate;}
    public void setEventDate(LocalDate d){this.eventDate=d;}
    public String getLocation(){return location;}
    public void setLocation(String l){this.location=l;}
    public String getDescription(){return description;}
    public void setDescription(String d){this.description=d;}
    public Conflict getConflict(){return conflict;}
    public void setConflict(Conflict c){this.conflict=c;}
}
