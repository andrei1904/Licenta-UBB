package repository.interfaces;

import java.util.List;

public interface IRepository<ID, T> {

    void save(T entity);

    List<T> findAll();
}
