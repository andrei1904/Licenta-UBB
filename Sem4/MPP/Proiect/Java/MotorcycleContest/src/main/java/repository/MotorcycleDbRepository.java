package repository;

import domain.EngineCapacity;
import domain.Motorcycle;
import domain.validators.MotorcycleValidator;
import domain.validators.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.interfaces.IMotorcycleRepository;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MotorcycleDbRepository implements IMotorcycleRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();
    Validator<Motorcycle> validator;

    public MotorcycleDbRepository(Properties properties, MotorcycleValidator validator) {
        logger.info("Initializing CarsDBRepository with properties: {} ", properties);
        dbUtils = new JdbcUtils(properties);
        this.validator = validator;
    }

    @Override
    public List<Motorcycle> FilterByEngineCapacity(EngineCapacity engineCapacity) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Motorcycle> motorcycles = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Motorcycles WHERE EngineCapacity = ?"
        )) {
            preparedStatement.setString(1, engineCapacity.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("MotorcycleId");

                    String resultEngineCapacity = resultSet.getString("EngineCapacity");
                    Motorcycle motorcycle = new Motorcycle(
                            EngineCapacity.valueOf(resultEngineCapacity));
                    motorcycle.setId(id);
                    validator.validate(motorcycle);

                    motorcycles.add(motorcycle);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return motorcycles;
    }

    @Override
    public Motorcycle lastMotorcycledAdded() {
        logger.traceEntry();

        Motorcycle motorcycle = null;

        Connection con = dbUtils.getConnection();

        try (PreparedStatement preparedStatement =  con.prepareStatement(
                "SELECT * FROM Motorcycles ORDER BY MotorcycleId DESC LIMIT 1"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("MotorcycleId");

                    String resultEngineCapacity = resultSet.getString("EngineCapacity");
                    motorcycle = new Motorcycle(
                            EngineCapacity.valueOf(resultEngineCapacity));
                    motorcycle.setId(id);
                    validator.validate(motorcycle);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }


        logger.traceExit();
        return motorcycle;
    }

    @Override
    public void save(Motorcycle entity) {
        logger.traceEntry("Saving task {}", entity);

        validator.validate(entity);
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Motorcycles (EngineCapacity) VALUES (?)"
        )){
            preparedStatement.setString(1, entity.getEngineCapacity().toString());

            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);

        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }
        logger.traceExit();
    }

    @Override
    public List<Motorcycle> findAll() {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Motorcycle> motorcycles = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Motorcycles"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("MotorcycleId");
                    EngineCapacity engineCapacity = EngineCapacity.valueOf(resultSet.getString("EngineCapacity"));

                    Motorcycle motorcycle = new Motorcycle(engineCapacity);
                    motorcycle.setId(id);

                    motorcycles.add(motorcycle);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return motorcycles;
    }


}
