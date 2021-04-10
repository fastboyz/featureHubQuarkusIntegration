package com.demo.featurehub.repositories;

import com.demo.featurehub.entities.Club;
import com.demo.featurehub.entities.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {

    public Game findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Game> findAllHomeGames(@NotNull Club club) {
        return find("home", club.getId()).list();
    }

    public List<Game> findAllAwayGames(@NotNull Club club) {
        return find("away", club.getId()).list();
    }

    public List<Game> findClubGames(@NotNull Club club) {
        return find("away = :away and home= :home",
                Parameters.with("away", club.getId()).and("home", club.getId())).list();
    }
}
