package service;

import domain.EngineCapacity;
import domain.Motorcycle;
import repository.MotorcycleDbRepository;

public class MotorcycleService {
    private MotorcycleDbRepository repo;

    public MotorcycleService(MotorcycleDbRepository repo) {
        this.repo = repo;
    }

    public Motorcycle addMotorcycle(EngineCapacity engineCapacity) {
        Motorcycle motorcycle = new Motorcycle(engineCapacity);
        repo.save(motorcycle);
        motorcycle = repo.lastMotorcycledAdded();
        return motorcycle;
    }
}
