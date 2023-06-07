package service;

import domain.Motorcycle;
import domain.Participant;
import domain.Team;
import repository.ParticipantDbRepository;

import java.util.List;

public class ParticipantService {
    private ParticipantDbRepository repo;

    public ParticipantService(ParticipantDbRepository repo) {
        this.repo = repo;
    }

    public List<Participant> getAllParticipants() {
        return repo.findAll();
    }

    public List<Participant> filterByTeam(Team team) {
        return repo.filterByTeam(team);
    }

    public Participant addParticipant(String name, Team team, Motorcycle motorcycle) {
        Participant participant = new Participant(name, motorcycle, team);
        repo.save(participant);
        participant = repo.lastParticipantAdded();
        return participant;
    }
}
