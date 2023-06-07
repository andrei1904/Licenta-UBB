package contest.services;

import contest.domain.*;

public interface IContestServices {
    void login(User user, IContestObserver client) throws ContestException;
    void logout(User user, IContestObserver client) throws ContestException;
    RaceDTO[] getRaces() throws ContestException;
    String[] getTeamsNames() throws ContestException;
    ParticipantDTO[] getTeamMembers(String team) throws ContestException;

    Race[] getRacesByEngineCapacity(EngineCapacity engineCapacity) throws ContestException;

    void addParticipantEntry(String name, String team, String engineCapacity, String raceName)
            throws ContestException;
}
