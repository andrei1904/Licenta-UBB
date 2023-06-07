package app.persistence.interfaces;

public interface IRepository<Tid, T> {

    void add(T elem);

    void delete(T elem);

    void update (T elem, Tid id);

    Long size();

    T findById (Tid id);

    Iterable<T> findAll();
}
