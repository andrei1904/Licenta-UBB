package contest.server;

import contest.domain.*;
import contest.domain.validators.ValidationException;
import contest.persistence.*;
import contest.services.ContestException;
import contest.services.IContestObserver;
import contest.services.IContestServices;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContestServicesImpl implements IContestServices {
    private final UserDbRepository userRepo;
    private final RaceDbRepository raceRepo;
    private final EntryDbRepository entryRepo;
    private final TeamDbRepository teamRepo;
    private final ParticipantDbRepository participantRepo;
    private final MotorcycleDbRepository motorcycleRepo;

    private final Map<Integer, IContestObserver> loggedClients;

    public ContestServicesImpl(UserDbRepository userRepo, RaceDbRepository raceRepo,
                               EntryDbRepository entryRepo, TeamDbRepository teamRepo,
                               ParticipantDbRepository participantRepo,
                               MotorcycleDbRepository motorcycleRepo) {
        this.userRepo = userRepo;
        this.raceRepo = raceRepo;
        this.entryRepo = entryRepo;
        this.teamRepo = teamRepo;
        this.participantRepo = participantRepo;
        this.motorcycleRepo = motorcycleRepo;
        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void login(User user, IContestObserver client) throws ContestException {
        User res = userRepo.FilterByUsername(user.getUsername());

        if (res != null && res.getPassword().equals(user.getPassword())) {
            if (loggedClients.get(res.getId()) != null) {
                throw new ContestException("User already logged in!");
            }

            loggedClients.put(res.getId(), client);
        } else {
            throw new ContestException("Authentification failed!");
        }
    }

    @Override
    public synchronized void logout(User user, IContestObserver client) throws ContestException {
        User res = userRepo.FilterByUsername(user.getUsername());

        if (res == null) {
            throw new ContestException("User " + user.getId() + " is not logged in!");
        }

        IContestObserver localClients = loggedClients.remove(res.getId());

        if (localClients == null) {
            throw new ContestException("User " + user.getId() + " is not logged in!");
        }
    }

    public int participantsAtRace(Race race) {
        return entryRepo.filterByRace(race).size();
    }

    @Override
    public synchronized RaceDTO[] getRaces() throws ContestException {
        List<Race> races = raceRepo.findAll();
        List<RaceDTO> res = new ArrayList<>();

        for (Race race : races) {
            RaceDTO raceDTO = new RaceDTO(race.getName(), race.getRequiredEngineCapacity(),
                    participantsAtRace(race));
            res.add(raceDTO);
        }

        System.out.println("Get Races size: " + res.size());
        return res.toArray(new RaceDTO[0]);
    }

    @Override
    public synchronized String[] getTeamsNames() throws ContestException {
        List<String> res = new ArrayList<>();

        for (Team team : teamRepo.findAll()) {
            res.add(team.getName());
        }

        return res.toArray(new String[0]);
    }

    @Override
    public synchronized ParticipantDTO[] getTeamMembers(String teamName) throws ContestException {
        List<ParticipantDTO> res = new ArrayList<>();

        Team team = teamRepo.filterByName(teamName);

        for (Participant participant : participantRepo.filterByTeam(team)) {
            res.add(new ParticipantDTO(participant.getName(),
                    participant.getMotorcycle().getEngineCapacity()));
        }

        return res.toArray(new ParticipantDTO[0]);
    }

    @Override
    public synchronized Race[] getRacesByEngineCapacity(EngineCapacity engineCapacity) throws ContestException {
        return raceRepo.filterByRequiredEngineCapacity(engineCapacity).toArray(new Race[0]);
    }

    private Motorcycle addMotorcycle(EngineCapacity engineCapacity) {
        Motorcycle motorcycle = new Motorcycle(engineCapacity);
        motorcycleRepo.save(motorcycle);
        motorcycle = motorcycleRepo.lastMotorcycledAdded();
        return motorcycle;
    }

    private int getRaceId(String name) {
        return raceRepo.filterByName(name).getId();
    }


    @Override
    public synchronized void addParticipantEntry(String name, String teamName, String engineCapacity,
                                                 String raceName) throws ContestException {
        try {
            Team team = teamRepo.filterByName(teamName);

            if (team == null) {
                throw new ContestException("Add Participant Error - team is null");
            }

            Motorcycle motorcycle = addMotorcycle(EngineCapacity.valueOf(engineCapacity));

            if (motorcycle == null) {
                throw new ContestException("Add Participant Error - motorcycle is null");
            }

            participantRepo.save(new Participant(name, motorcycle, team));
            int participantId = participantRepo.lastParticipantAdded().getId();

            int raceId = getRaceId(raceName);

            entryRepo.save(new Entry(raceId, participantId));

            notifyNewParticipant(raceName);

        } catch (ValidationException ex) {
            throw new ContestException(ex.getMessage());
        }
    }

    private void notifyNewParticipant(String raceName) {
        int defaultThreadsNo = 5;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);

        for (IContestObserver client : loggedClients.values()) {
            if (client != null) {
                executor.execute(() -> {
                    try {
                        client.participantEntryAdded(raceName);
                    } catch (ContestException | RemoteException e) {
                        System.err.println("Error notifying clients " + e);
                    }
                });
            }
        }
        executor.shutdown();
    }
}
