package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.UserDao;
import cz.muni.fi.pa165.mm.daolayer.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marek Barinka; 456295
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void registerUser(User u, String passwd) {
        u.setPasswordHash(passwordEncoder.encode(passwd));
        userDao.create(u);
    }
    
    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public boolean authenticate(User u, String password) {
        return passwordEncoder.encode(password).equals(u.getPasswordHash());
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
    
}
