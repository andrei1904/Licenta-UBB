package repository.interfaces;

import domain.EngineCapacity;
import domain.Motorcycle;

import java.util.List;

public interface IMotorcycleRepository extends IRepository<Integer, Motorcycle> {

    List<Motorcycle> FilterByEngineCapacity(EngineCapacity engineCapacity);

    Motorcycle lastMotorcycledAdded();
}
