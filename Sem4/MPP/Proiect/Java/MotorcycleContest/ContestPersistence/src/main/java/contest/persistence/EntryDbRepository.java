package contest.persistence;

import contest.domain.Entry;
import contest.domain.Race;
import contest.domain.validators.EntryValidator;
import contest.domain.validators.Validator;
import contest.persistence.interfaces.IEntryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EntryDbRepository implements IEntryRepository {
    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();
    Validator<Entry> validator;

    public EntryDbRepository(Properties properties, EntryValidator validator) {
        logger.info("Initializing CarsDBRepository with properties: {} ", properties);
        dbUtils = new JdbcUtils(properties);
        this.validator = validator;
    }

    @Override
    public List<Entry> filterByRace(Race race) {
        logger.traceEntry();

        Connection con = dbUtils.getConnection();
        List<Entry> entries = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Entry WHERE RaceId = (?)"
        )) {
            preparedStatement.setInt(1, race.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("RaceId");
                    int participantId = resultSet.getInt("ParticipantId");

                    Entry entry = new Entry(race.getId(), participantId);
                    entry.setId(id);
                    validator.validate(entry);

                    entries.add(entry);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }

        logger.traceExit();
        return entries;
    }

    @Override
    public void save(Entry entity) {
        logger.traceEntry("Saving task {}", entity);

        validator.validate(entity);
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Entry (RaceId, ParticipantId) VALUES (?, ?)"
        )){
            preparedStatement.setInt(1, entity.getRaceId());
            preparedStatement.setInt(2, entity.getParticipantId());

            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);

        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("DB Error " + ex);
        }
        logger.traceExit();
    }

    @Override
    public List<Entry> findAll() {
        return null;
    }
}
