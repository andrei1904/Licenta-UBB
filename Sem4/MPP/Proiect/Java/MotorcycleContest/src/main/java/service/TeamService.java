package service;

import domain.Team;
import repository.TeamDbRepository;

import java.util.List;

public class TeamService {
    private TeamDbRepository repo;

    public TeamService(TeamDbRepository repo) {
        this.repo = repo;
    }

    public List<Team> getAllTeams() {
        return repo.findAll();
    }

    public Team filterByName(String name) {
        return repo.filterByName(name);
    }
}
