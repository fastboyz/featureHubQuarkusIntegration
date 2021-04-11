package com.demo.featurehub.repositories;

import com.demo.featurehub.entities.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class ClubRepository implements PanacheRepository<Team> {

    public List<Team> findByCity(String cityName) {
        return find("city", cityName).list();
    }

    public Team findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Team> findByNumberOfTrophy(int trophyNumber) {
        return find("numberOfTrophy", trophyNumber).list();
    }
}
