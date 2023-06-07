package repository.interfaces;

import domain.Entry;
import domain.Race;

import java.util.List;

public interface IEntryRepository extends IRepository<Integer, Entry> {

    List<Entry> filterByRace(Race race);
}
