package contest.persistence;

import contest.domain.EngineCapacity;
import contest.domain.Race;
import contest.domain.validators.RaceValidator;
import contest.domain.validators.Validator;
import contest.persistence.interfaces.IRaceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Component
public class RaceDbRepository implements IRaceRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    Validator<Race> validator;

    public RaceDbRepository(Properties properties, RaceValidator raceValidator) {
        logger.info("Initializing CarsDBRepository with properties: {} ", properties);
        this.dbUtils = new JdbcUtils(properties);
        this.validator = raceValidator;
    }

    @Override
    public List<Race> filterByRequiredEngineCapacity(EngineCapacity engineCapacity) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Race> races = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Races WHERE RequiredEngineCapacity = ?"
        )) {
            preparedStatement.setString(1, engineCapacity.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("RaceId");
                    String resultEngineCapacity = resultSet.getString("RequiredEngineCapacity");
                    String name = resultSet.getString("Name");

                    Race race = new Race(name, EngineCapacity.valueOf(resultEngineCapacity));
                    race.setId(id);
                    validator.validate(race);

                    races.add(race);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return races;
    }

    @Override
    public Race filterByName(String name) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        Race race = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Races WHERE Name = ?"
        )) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("RaceId");
                    String resultEngineCapacity = resultSet.getString("RequiredEngineCapacity");

                    race = new Race(name, EngineCapacity.valueOf(resultEngineCapacity));
                    race.setId(id);
                    validator.validate(race);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return race;
    }

    @Override
    public Race filterById(int id) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        Race race = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Races WHERE RaceId = ?"
        )) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String resultEngineCapacity = resultSet.getString("RequiredEngineCapacity");

                    race = new Race(name, EngineCapacity.valueOf(resultEngineCapacity));
                    race.setId(id);
                    validator.validate(race);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return race;
    }

    @Override
    public void delete(String name) {
        logger.traceEntry("Deleting race {}", name);

        Connection con = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM Races WHERE Name = (?)"
        )){
            preparedStatement.setString(1, name);

            int result = preparedStatement.executeUpdate();
            logger.trace("Deleted {} instance", result);

        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }
        logger.traceExit();

    }

    @Override
    public void update(int id, Race race) {
        logger.traceEntry("Updating race with id {}", id);

        validator.validate(race);
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Races SET Name = ?, RequiredEngineCapacity = ? WHERE RaceId = ? "
        )){
            preparedStatement.setInt(3, id);
            preparedStatement.setString(1, race.getName());
            preparedStatement.setString(2, race.getRequiredEngineCapacity().toString());

            int result = preparedStatement.executeUpdate();
            logger.trace("Updated {} instances", result);

        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void save(Race entity) {
        logger.traceEntry("Saving task {}", entity);

        validator.validate(entity);
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Races (Name, RequiredEngineCapacity) VALUES (?, ?)"
        )){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getRequiredEngineCapacity().toString());

            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);

        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }
        logger.traceExit();
    }

    @Override
    public List<Race> findAll() {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Race> races = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Races"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("RaceId");
                    String resultEngineCapacity = resultSet.getString("RequiredEngineCapacity");
                    String name = resultSet.getString("Name");

                    Race race = new Race(name, EngineCapacity.valueOf(resultEngineCapacity));
                    race.setId(id);
                    validator.validate(race);

                    races.add(race);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return races;
    }
}
