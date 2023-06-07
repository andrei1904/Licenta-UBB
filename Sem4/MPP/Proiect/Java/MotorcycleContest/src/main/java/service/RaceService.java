package service;

import domain.EngineCapacity;
import domain.Race;
import repository.RaceDbRepository;
import repository.interfaces.IRepository;

import java.util.List;

public class RaceService {
    private final RaceDbRepository repo;

    public RaceService(RaceDbRepository repo) {
        this.repo = repo;
    }

    public List<Race> getAllRaces() {
        return repo.findAll();
    }

    public List<Race> filterByEngineCapacity(EngineCapacity engineCapacity) {
        return repo.FilterByRequiredEngineCapacity(engineCapacity);
    }
}
