package app.persistence.interfaces;

import java.util.List;

public interface IRepository<ID, T> {
    int size();

    void delete(ID id);

    void update(ID id, T entity);

    List<T> getAll();
}
