
package co.unicauca.openmarket.domain.services;

import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IUserRepository;
import co.unicauca.openmarket.server.access.UserRepositoryArrays;

/**
 *
 * @author brayan
 */
public class UserService {
    
    IUserRepository repo;

    public UserService(IUserRepository repo) {
        this.repo = repo;
    }
    public synchronized User login(User user) {
        return repo.login(user);
    }
}
