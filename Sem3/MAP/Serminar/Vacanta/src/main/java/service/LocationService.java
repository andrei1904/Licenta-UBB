package service;

import domain.Location;
import repo.Repository;

import java.util.List;

public class LocationService {
    private final Repository<Double, Location> repo;

    public LocationService(Repository<Double, Location> repo) {
        this.repo = repo;
    }

    public List<Location> getAll() {
        return repo.findAll();
    }
}
