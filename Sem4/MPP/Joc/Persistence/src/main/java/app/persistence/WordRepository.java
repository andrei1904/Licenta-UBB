package app.persistence;

import app.model.Characteristic;
import app.model.Word;
import app.persistence.interfaces.IWordRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WordRepository implements IWordRepository {
    private final SessionFactory sessionFactory;

    public WordRepository(HibernateUtil hibernateUtil) {
        sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void update(String s, Word entity) {

    }

    @Override
    public List<Word> getAll() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Word> words = session.createQuery(
                        "FROM Word", Word.class).list();
                transaction.commit();

//                transaction = session.beginTransaction();
//                List<Characteristic> characteristics = session.createQuery(
//                        "FROM Characteristic", Characteristic.class).list();
//                transaction.commit();

                return words;
            }
            catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }

            return null;
        }
    }
}
