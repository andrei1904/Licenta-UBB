package contest.persistence.interfaces;


import contest.domain.User;

public interface IUserRepository extends IRepository<Integer, User> {
    User FilterByUsername(String username);

}
