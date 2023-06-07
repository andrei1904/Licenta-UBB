package contest.persistence.interfaces;


import contest.domain.Entry;
import contest.domain.Race;

import java.util.List;

public interface IEntryRepository extends IRepository<Integer, Entry> {

    List<Entry> filterByRace(Race race);
}
