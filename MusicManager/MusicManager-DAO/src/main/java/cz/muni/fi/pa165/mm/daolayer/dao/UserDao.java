package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.entity.User;
import java.util.List;

/**
 *
 * @author Marek Barinka; 456295
 */
public interface UserDao {
    
    public User findUserByUsername(String username);
    public List<User> findAll();
}
