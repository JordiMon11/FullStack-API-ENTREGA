package com.example.conflicttracker.model;

import jakarta.persistence.*;

@Entity
public class Country {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String code;

    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String n){this.name=n;}
    public String getCode(){return code;}
    public void setCode(String c){this.code=c;}
}
