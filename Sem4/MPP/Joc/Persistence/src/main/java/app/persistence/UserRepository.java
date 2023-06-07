package app.persistence;

import app.model.User;
import app.persistence.interfaces.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepository implements IUserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void update(String s, User entity) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getOneByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                User user = session.createQuery("FROM User WHERE username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult();

                transaction.commit();
                return user;

            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
            return null;
        }
    }
}
