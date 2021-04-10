package com.demo.featurehub.repositories;

import com.demo.featurehub.entities.Club;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class ClubRepository implements PanacheRepository<Club> {

    public List<Club> findByCity(String cityName) {
        return find("city", cityName).list();
    }

    public Club findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Club> findByNumberOfTrophy(int trophyNumber) {
        return find("numberOfTrophy", trophyNumber).list();
    }
}
