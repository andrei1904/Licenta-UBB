package repository.interfaces;

import domain.EngineCapacity;
import domain.Participant;
import domain.Race;

import java.util.List;

public interface IRaceRepository extends IRepository<Integer, Race> {

    List<Race> FilterByRequiredEngineCapacity(EngineCapacity engineCapacity);
}
