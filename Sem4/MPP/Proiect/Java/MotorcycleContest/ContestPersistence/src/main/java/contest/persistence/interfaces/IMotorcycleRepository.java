package contest.persistence.interfaces;

import contest.domain.EngineCapacity;
import contest.domain.Motorcycle;

import java.util.List;

public interface IMotorcycleRepository extends IRepository<Integer, Motorcycle> {

    List<Motorcycle> FilterByEngineCapacity(EngineCapacity engineCapacity);

    Motorcycle lastMotorcycledAdded();
}
