package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.mm.api.dto.UserDTO;
import java.util.Collection;

/**
 *
 * @author Marek Barinka; 456295
 */
public interface UserFacade {

    /**
     * Finds user with username
     * @param username
     * @return UserDTO with specified username, null if there is no user with specified username in database
     */
    UserDTO findUserByUsername(String username);

    /**
     * Finds all registered users
     * @return List of all registered users
     */
    Collection<UserDTO> findAllUsers();

    /**
     * Try to authenticate a user. Return true only if the hashed password matches the records.
     * @param u User to authenticate
     * @return true if authetication was succesfull
     */
    boolean authenticate(UserAuthenticateDTO u);
}
