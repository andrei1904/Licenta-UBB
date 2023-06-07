package contest.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private final Properties jdbcProperties;

    private static final Logger logger = LogManager.getLogger();

    public JdbcUtils(Properties properties) {
        jdbcProperties = properties;
    }

    private Connection instance = null;

    private Connection getNewConnection() {
        logger.traceEntry();

        String driver = jdbcProperties.getProperty("motorcycleContest.jdbc.driver");
        String url = jdbcProperties.getProperty("motorcycleContest.jdbc.url");
        logger.info("Trying to connect to database ... {}",url);

        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error getting connection" + ex);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            System.out.println("Error loading driver" + e);
        }
        logger.traceExit(con);

        return con;
    }

    public Connection getConnection() {
        logger.traceEntry();

        try {
            if (instance == null || instance.isClosed()) {
                instance = getNewConnection();
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("DB Error");
        }

        logger.traceExit(instance);
        return instance;
    }
}
