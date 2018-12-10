package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marek Barinka; 456295
 */
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public User findUserByUsername(String username) {
        return em.find(User.class, username);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    
}
