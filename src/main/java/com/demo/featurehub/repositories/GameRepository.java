package com.demo.featurehub.repositories;

import com.demo.featurehub.entities.Team;
import com.demo.featurehub.entities.Match;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Match> {

    public Match findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Match> findAllHomeGames(@NotNull Team team) {
        return find("home", team.getId()).list();
    }

    public List<Match> findAllAwayGames(@NotNull Team team) {
        return find("away", team.getId()).list();
    }

    public List<Match> findClubGames(@NotNull Team team) {
        return find("away = :away and home= :home",
                Parameters.with("away", team.getId()).and("home", team.getId())).list();
    }
}
