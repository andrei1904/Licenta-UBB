package contest.persistence.interfaces;

import contest.domain.EngineCapacity;
import contest.domain.Race;

import java.util.List;

public interface IRaceRepository extends IRepository<Integer, Race> {

    List<Race> filterByRequiredEngineCapacity(EngineCapacity engineCapacity);

    Race filterByName(String name);

    Race filterById(int id);

    void delete(String name);

    void update(int id, Race race);
}
