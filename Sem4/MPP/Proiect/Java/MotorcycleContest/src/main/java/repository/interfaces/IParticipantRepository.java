package repository.interfaces;

import domain.Motorcycle;
import domain.Participant;
import domain.Team;

import java.util.List;

public interface IParticipantRepository extends IRepository<Integer, Participant> {

    List<Participant> filterByTeam(Team team);

    Participant lastParticipantAdded();
}
