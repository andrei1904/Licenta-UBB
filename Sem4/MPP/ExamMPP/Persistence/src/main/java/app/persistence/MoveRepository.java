package app.persistence;

import app.model.Move;
import app.model.User;
import app.model.UserGameDto;
import app.model.UserWordDto;
import app.persistence.interfaces.IMoveRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MoveRepository implements IMoveRepository {
    static SessionFactory sessionFactory;

    public void initialize() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("catch");
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public MoveRepository(HibernateUtil hibernateUtil) {
        MoveRepository.sessionFactory = hibernateUtil.getSessionFactory();
    }

    public MoveRepository() {
        initialize();
    }

    @Override
    public void add(Move elem) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(elem);
                transaction.commit();
            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Move elem) {

    }

    @Override
    public void update(Move elem, Integer id) {

    }

    @Override
    public Long size() {
        return null;
    }

    @Override
    public Move findById(Integer id) {
        return null;
    }

    @Override
    public Iterable<Move> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Move> moves = session.createQuery("FROM Move", Move.class).list();
                transaction.commit();
                return moves;

            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
                return null;
            }
        }
    }

    @Override
    public UserWordDto[] getChosenWordsForGame(Integer gameId) {
        List<Move> moves = (List<Move>) findAll();
        List<UserWordDto> res = new ArrayList<>();

        for (Move move : moves) {
            if (move.getGameId().equals(gameId) && move.getRound() == 0) {
                res.add(new UserWordDto(move.getUser().getUsername(), move.getChosenWord()));
            }
        }

        return res.toArray(new UserWordDto[0]);
    }

    @Override
    public UserGameDto[] getDetailsUserGame(String username, Integer gameId) {
        List<Move> moves = (List<Move>) findAll();
        List<UserGameDto> res = new ArrayList<>();

        for (Move move : moves) {
            if (move.getGameId().equals(gameId) && move.getUser().getUsername().equals(username)) {
                res.add(new UserGameDto(username, move.getLetter(), move.getPoints()));
            }
        }

        return res.toArray(new UserGameDto[0]);
    }

    @Override
    public List<Move> findForGame(int game) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Move> moves = session.createQuery("FROM Move WHERE gameId = :game", Move.class)
                        .setParameter("game", game).list();
                transaction.commit();
                return moves;

            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
                return null;
            }
        }
    }
}
