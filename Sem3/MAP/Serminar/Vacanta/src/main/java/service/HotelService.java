package service;

import domain.Hotel;
import repo.Repository;

import java.util.List;

public class HotelService {
    private final Repository<Double, Hotel> repo;

    public HotelService(Repository<Double, Hotel> repo) {
        this.repo = repo;
    }

    public List<Hotel> getAll() {
        return repo.findAll();
    }
}
