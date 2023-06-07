package repository;


public class RepositoryException extends RuntimeException {
    public RepositoryException(String msg) {
        super(msg);
    }

    public RepositoryException(Exception ex) {
        super(ex);
    }
}
