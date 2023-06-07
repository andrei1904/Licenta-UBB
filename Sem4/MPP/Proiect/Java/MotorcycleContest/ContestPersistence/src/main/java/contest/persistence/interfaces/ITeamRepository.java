package contest.persistence.interfaces;


import contest.domain.Team;

public interface ITeamRepository extends IRepository<Integer, Team> {

    Team filterByName(String name);

}
