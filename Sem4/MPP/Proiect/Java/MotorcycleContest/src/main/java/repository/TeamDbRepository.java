package repository;

import domain.Team;
import domain.validators.TeamValidator;
import domain.validators.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.interfaces.ITeamRepository;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TeamDbRepository implements ITeamRepository {
    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();
    Validator<Team> validator;

    public TeamDbRepository(Properties properties, TeamValidator validator) {
        logger.info("Initializing CarsDBRepository with properties: {} ", properties);
        dbUtils = new JdbcUtils(properties);
        this.validator = validator;
    }

    @Override
    public Team filterByName(String name) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        Team team = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Teams WHERE TeamName = (?)"
        )) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("TeamId");
                    String resultName = resultSet.getString("TeamName");

                    team = new Team(resultName);
                    team.setId(id);
                    validator.validate(team);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return team;
    }

    @Override
    public void save(Team entity) {
        // no
    }

    @Override
    public List<Team> findAll() {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Team> teams = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Teams"
        )) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("TeamId");
                    String resultName = resultSet.getString("TeamName");

                    Team team = new Team(resultName);
                    team.setId(id);
                    validator.validate(team);

                    teams.add(team);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return teams;
    }
}
