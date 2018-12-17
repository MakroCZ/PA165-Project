package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.User;
import java.util.List;

/**
 *
 * @author Marek Barinka; 456295
 */
public interface UserService {
    
    void registerUser(User u, String passwd);
    
    /**
     * Get all users
     * @return List of all users
     */
    List<User> findAllUsers();

    /**
     * Authenticates user
     * @param u User to authenticate with password
     * @param password Password to authenticate with user
     * @return true if user and password matched record in database
     */
    boolean authenticate(User u, String password);

    /**
     * Finds user with username
     * @param username Username to find
     * @return User with username or null if there is no user with specified username
     */
    User findUserByUsername(String username);
}
