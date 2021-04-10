package com.demo.featurehub.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Club {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String arena;
    private String name;
    private int numberOfTrophy;

    @ManyToMany
    private List<League> league;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfTrophy() {
        return numberOfTrophy;
    }

    public void setNumberOfTrophy(int numberOfTrophy) {
        this.numberOfTrophy = numberOfTrophy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
