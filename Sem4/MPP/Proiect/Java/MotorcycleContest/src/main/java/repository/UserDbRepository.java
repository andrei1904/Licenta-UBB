package repository;

import domain.User;
import domain.validators.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.interfaces.IUserRepository;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class UserDbRepository implements IUserRepository {
    private final JdbcUtils dbUtils;

    Validator<User> validator;

    private static final Logger logger= LogManager.getLogger();

    public UserDbRepository(Properties properties, Validator<User> validator) {
        logger.info("Initializing CarsDBRepository with properties: {} ", properties);
        this.dbUtils = new JdbcUtils(properties);
        this.validator = validator;
    }

    @Override
    public User FilterByUsername(String username) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        User user = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Users WHERE Username = (?)"
        )) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("UserId");
                    String resultUsername = resultSet.getString("Username");
                    String resulPassword = resultSet.getString("Password");

                    user = new User(resultUsername, resulPassword);
                    user.setId(id);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return user;
    }

    @Override
    public void save(User entity) {
        // no
    }

    @Override
    public List<User> findAll() {
        // no
        return null;
    }
}
