package repo.file;

import domain.Entity;
import domain.validators.Validator;
import repo.memory.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    String fileName;


    public AbstractFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
    }

    private void loadData() {
        Path path = Paths.get(fileName);
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(linie -> {
                E entity = extractEntity(Arrays.asList(linie.split(";")));
                super.save(entity);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract E extractEntity(List<String> attributes);

    protected abstract String createEntityAsString(E entity);

    protected void writeToFile(E entity) {
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName, true))) {
            bW.write(createEntityAsString(entity));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void deleteAllFromFile() {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<E> save(E entity) {
        Optional<E> e = super.save(entity);
        if (e.isEmpty()) { // daca s a adaugat
            writeToFile(entity);
            return Optional.empty();
        }
        return e; // daca nu s a adaugat
    }

    @Override
    public Optional<E> delete(ID id) {
        Optional<E> e = super.delete(id);
        if (e.isPresent()) { // s a sters
            deleteAllFromFile();
            for (E entity : super.findAll()) {
                writeToFile(entity);
            }
        }
        return e;
    }

    @Override
    public Optional<E> update(E entity) {
        Optional<E> e= super.update(entity);

        if (e.isPresent()) // nu exista
            return e;
        else {
            deleteAllFromFile();
            for (E entity1 : super.findAll()) {
                writeToFile(entity1);
            }
        }
        return Optional.empty();
    }
}
