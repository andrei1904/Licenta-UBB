package repository;

import domain.EngineCapacity;
import domain.Race;
import domain.validators.RaceValidator;
import domain.validators.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.interfaces.IRaceRepository;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RaceDbRepository implements IRaceRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    Validator<Race> validator;

    public RaceDbRepository(Properties properties, RaceValidator validator) {
        logger.info("Initializing CarsDBRepository with properties: {} ", properties);
        this.dbUtils = new JdbcUtils(properties);
        this.validator = validator;
    }

    @Override
    public List<Race> FilterByRequiredEngineCapacity(EngineCapacity engineCapacity) {
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
    public void save(Race entity) {

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
