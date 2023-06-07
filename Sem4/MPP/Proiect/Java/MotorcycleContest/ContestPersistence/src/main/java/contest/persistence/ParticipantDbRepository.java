package contest.persistence;


import contest.domain.EngineCapacity;
import contest.domain.Motorcycle;
import contest.domain.Participant;
import contest.domain.Team;
import contest.domain.validators.ParticipantValidator;
import contest.domain.validators.Validator;
import contest.persistence.interfaces.IParticipantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParticipantDbRepository implements IParticipantRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    Validator<Participant> validator;

    public ParticipantDbRepository(Properties properties, ParticipantValidator validator) {
        logger.info("Initializing CarsDBRepository with properties: {} ", properties);
        dbUtils = new JdbcUtils(properties);
        this.validator = validator;
    }


    @Override
    public List<Participant> filterByTeam(Team team) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Participant> participants = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Participants WHERE TeamId = (?)"
        )) {
            preparedStatement.setInt(1, team.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ParticipantId");
                    int motorcycleId = resultSet.getInt("MotorcycleId");
                    String name = resultSet.getString("Name");

                    Motorcycle motorcycle = null;

                    // find Motorcycle
                    try (PreparedStatement preparedStatementMotorcycle = con.prepareStatement(
                            "SELECT * FROM Motorcycles WHERE MotorcycleId = (?)"
                    )) {
                        preparedStatementMotorcycle.setInt(1, motorcycleId);

                        try (ResultSet resultSetMotorcycle = preparedStatementMotorcycle.executeQuery()) {
                            while (resultSetMotorcycle.next()) {
                                String motorcycleEngineCapacity = resultSetMotorcycle.getString("EngineCapacity");

                                motorcycle = new Motorcycle(EngineCapacity.valueOf(motorcycleEngineCapacity));
                                motorcycle.setId(motorcycleId);
                            }
                        }
                    }

                    Participant participant = new Participant(name, motorcycle, team);
                    participant.setId(id);
                    validator.validate(participant);

                    participants.add(participant);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return participants;
    }

    @Override
    public Participant lastParticipantAdded() {
        logger.traceEntry();

        Participant participant = null;

        Connection con = dbUtils.getConnection();

        try (PreparedStatement preparedStatement =  con.prepareStatement(
                "SELECT * FROM Participants ORDER BY ParticipantId DESC LIMIT 1"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ParticipantId");
                    String name = resultSet.getString("Name");
                    int mId = resultSet.getInt("MotorcycleId");
                    int tId = resultSet.getInt("TeamId");

                    Motorcycle motorcycle = null;
                    Team team = null;

                    // find Motorcycle
                    try (PreparedStatement preparedStatementMotorcycle = con.prepareStatement(
                            "SELECT * FROM Motorcycles WHERE MotorcycleId = (?)"
                    )) {
                        preparedStatementMotorcycle.setInt(1, mId);

                        try (ResultSet resultSetMotorcycle = preparedStatementMotorcycle.executeQuery()) {
                            while (resultSetMotorcycle.next()) {
                                String motorcycleEngineCapacity = resultSetMotorcycle.getString("EngineCapacity");

                                motorcycle = new Motorcycle(EngineCapacity.valueOf(motorcycleEngineCapacity));
                                motorcycle.setId(mId);
                            }
                        }
                    }

                    // find Team
                    try (PreparedStatement preparedStatementTeam = con.prepareStatement(
                            "SELECT * FROM Teams WHERE TeamId = (?)"
                    )) {
                        preparedStatementTeam.setInt(1, tId);

                        try (ResultSet resultSetTeam = preparedStatementTeam.executeQuery()) {
                            while (resultSetTeam.next()) {
                                String teamName = resultSetTeam.getString("TeamName");

                                team = new Team(teamName);
                                team.setId(tId);
                            }
                        }
                    }

                    participant = new Participant(name, motorcycle, team);
                    participant.setId(id);
                    validator.validate(participant);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }


        logger.traceExit();
        return participant;
    }

    @Override
    public void save(Participant entity) {
        logger.traceEntry("Saving task {}", entity);

        validator.validate(entity);
        Connection con = dbUtils.getConnection();


        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Participants (Name, MotorcycleId, TeamId) VALUES (?, ?, ?)"
        )){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getMotorcycle().getId());
            preparedStatement.setInt(3, entity.getTeam().getId());

            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);

        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }
        logger.traceExit();
    }

    @Override
    public List<Participant> findAll() {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Participant> participants = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Participants"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ParticipantId");
                    int motorcycleId = resultSet.getInt("MotorcycleId");
                    int teamId = resultSet.getInt("TeamId");
                    String name = resultSet.getString("Name");

                    Motorcycle motorcycle = null;
                    Team team = null;

                    // find Motorcycle
                    try (PreparedStatement preparedStatementMotorcycle = con.prepareStatement(
                            "SELECT * FROM Motorcycles WHERE MotorcycleId = (?)"
                    )) {
                        preparedStatementMotorcycle.setInt(1, motorcycleId);

                        try (ResultSet resultSetMotorcycle = preparedStatementMotorcycle.executeQuery()) {
                            while (resultSetMotorcycle.next()) {
                                String motorcycleEngineCapacity = resultSetMotorcycle.getString("EngineCapacity");

                                motorcycle = new Motorcycle(EngineCapacity.valueOf(motorcycleEngineCapacity));
                                motorcycle.setId(motorcycleId);
                            }
                        }
                    }

                    // find Team
                    try (PreparedStatement preparedStatementTeam = con.prepareStatement(
                            "SELECT * FROM Teams WHERE TeamId = (?)"
                    )) {
                        preparedStatementTeam.setInt(1, teamId);

                        try (ResultSet resultSetTeam = preparedStatementTeam.executeQuery()) {
                            while (resultSetTeam.next()) {
                                String teamName = resultSetTeam.getString("TeamName");

                                team = new Team(teamName);
                                team.setId(teamId);
                            }
                        }
                    }

                    Participant participant = new Participant(name, motorcycle, team);
                    participant.setId(id);
                    validator.validate(participant);

                    participants.add(participant);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return participants;
    }
}
