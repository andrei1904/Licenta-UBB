package repository.interfaces;

import domain.Team;

import java.util.List;

public interface ITeamRepository extends IRepository<Integer, Team> {

    Team filterByName(String name);

}
