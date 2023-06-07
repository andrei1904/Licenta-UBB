package app.persistence;

import app.model.User;
import app.persistence.interfaces.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;


public class UserRepository implements IUserRepository {
    static SessionFactory sessionFactory;

    public UserRepository(HibernateUtil hibernateUtil) {
        UserRepository.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public void add(User elem) {
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
    public void delete(User elem) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.createQuery("DELETE FROM User WHERE id = :id")
                        .setParameter("id", elem.getUsername())
                        .executeUpdate();
                transaction.commit();
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
    }

    @Override
    public void update(User elem, String id) {

    }

    @Override
    public Long size() {
        return null;
    }

    @Override
    public User findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                User user = session.createQuery("FROM User WHERE username = :username", User.class)
                        .setParameter("username", id)
                        .getSingleResult();

                transaction.commit();
                return user;

            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
            return null;
        }
    }



    @Override
    public Iterable<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<User> users = session.createQuery("FROM User", User.class).list();
                transaction.commit();
                return users;

            } catch (RuntimeException ex) {
                if (transaction != null)
                    transaction.rollback();
                return null;
            }
        }
    }
}
