package repo.memory;

import domain.Entity;
import domain.validators.Validator;
import repo.RepoException;
import repo.Repository;

import java.util.*;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    private final Validator<E> validator;
    protected Map<ID, E> entities;


    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }


    @Override
    public Optional<E> findOne(ID id) {
        if (id == null) {
            throw new RepoException("Id is null!\n");
        }
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<E> findAll() {
        return (List<E>) entities.values();
    }

    @Override
    public Optional<E> save(E entity) {
        if (entity == null) {
            throw new RepoException("Entity is null!\n");
        }

        validator.validate(entity);

        if (entities.get(entity.getId()) != null) { // daca exista
            return Optional.of(entity);
        } else { // daca nu exista
            entities.put(entity.getId(), entity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> delete(ID id) {
        if (id == null) {
            throw new RepoException("Id is null!\n");
        }

        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<E> update(E entity) {
        if (entity == null) {
            throw new RepoException("Entity is null!\n");
        }

        validator.validate(entity);

        if (entities.get(entity.getId()) != null) {
            entities.put(entity.getId(), entity);
            return Optional.empty();
        }

        return Optional.of(entity);
    }

    @Override
    public int size() {
        return entities.size();
    }
}
