package contest.persistence.interfaces;



import contest.domain.Participant;
import contest.domain.Team;

import java.util.List;

public interface IParticipantRepository extends IRepository<Integer, Participant> {

    List<Participant> filterByTeam(Team team);

    Participant lastParticipantAdded();
}
