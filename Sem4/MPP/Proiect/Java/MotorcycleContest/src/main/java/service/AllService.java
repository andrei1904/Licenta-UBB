package service;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class AllService {
    private UserService userService;
    private RaceService raceService;
    private ParticipantService participantService;
    private EntryService entryService;
    private TeamService teamService;
    private MotorcycleService motorcycleService;

    public AllService(UserService userService, RaceService raceService, ParticipantService participantService, EntryService entryService, TeamService teamService, MotorcycleService motorcycleService) {
        this.userService = userService;
        this.raceService = raceService;
        this.participantService = participantService;
        this.entryService = entryService;
        this.teamService = teamService;
        this.motorcycleService = motorcycleService;
    }

    public User verifyUser (String username, String password) {
        User user = userService.getUser(username);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<Race> getAllRaces() {
        return raceService.getAllRaces();
    }

    public List<RaceDTO> getDTORaces() {
        List<Race> races = raceService.getAllRaces();
        List<RaceDTO> res = new ArrayList<>();

        for (Race race : races) {
            RaceDTO raceDTO = new RaceDTO(race.getName(), race.getRequiredEngineCapacity(),
                    entryService.participantsAtRace(race));
            res.add(raceDTO);
        }

        return res;
    }

    public List<String> getTeamsNames() {
        List<String> res = new ArrayList<>();

        for (Team team : teamService.getAllTeams()) {
            res.add(team.getName());
        }

        return res;
    }

    public List<ParticipantDTO> getDTOParticipants(String teamName) {
        List<ParticipantDTO> res = new ArrayList<>();


        Team team = teamService.filterByName(teamName);

        for (Participant participant : participantService.filterByTeam(team)) {
            res.add(new ParticipantDTO(participant.getName(),
                    participant.getMotorcycle().getEngineCapacity()));
        }

        return res;
    }

    public Participant addParticipant(String name, String teamName, String engineCapacity) {
        Team team = teamService.filterByName(teamName);

        Motorcycle motorcycle = motorcycleService.addMotorcycle(EngineCapacity.valueOf(engineCapacity));

        return participantService.addParticipant(name, team, motorcycle);
    }

    public List<Race> raceByEngineCapacity(EngineCapacity engineCapacity) {
        return raceService.filterByEngineCapacity(engineCapacity);
    }

    public void addEntry(Participant participant, Race race) {
        entryService.addEntry(new Entry(
                race.getId(), participant.getId()
        ));
    }
}
