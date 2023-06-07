package service;

import domain.Flight;
import repo.Repository;

import java.util.List;

public class FlightService {
    private final Repository<Long, Flight> repo;


    public FlightService(Repository<Long, Flight> repo) {
        this.repo = repo;
    }

    public List<Flight> getAll() {
        return repo.findAll();
    }
}
