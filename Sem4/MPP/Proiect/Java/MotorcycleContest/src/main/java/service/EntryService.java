package service;

import domain.Entry;
import domain.Race;
import repository.EntryDbRepository;

public class EntryService {
    private EntryDbRepository repo;

    public EntryService(EntryDbRepository repo) {
        this.repo = repo;
    }

    public int participantsAtRace(Race race) {
        return repo.filterByRace(race).size();
    }

    public void addEntry(Entry entry) {
        repo.save(entry);
    }
}
