package com.demo.featurehub.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Team extends PanacheEntity {
    private String shortName;

    private String club;

    private int nbPoints;

    @ManyToOne
    private Stadium stadium;

    public String getClub() {
        return club;
    }

    public void setClub(String name) {
        this.club = name;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int numberOfTrophy) {
        this.nbPoints = numberOfTrophy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
