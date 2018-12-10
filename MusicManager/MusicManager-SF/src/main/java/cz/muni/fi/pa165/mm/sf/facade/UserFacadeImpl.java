package cz.muni.fi.pa165.mm.sf.facade;

import cz.muni.fi.pa165.mm.api.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.mm.api.dto.UserDTO;
import cz.muni.fi.pa165.mm.api.facade.UserFacade;
import cz.muni.fi.pa165.mm.sf.service.BeanMappingService;
import cz.muni.fi.pa165.mm.sf.service.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Marek Barinka; 456295
 */
public class UserFacadeImpl implements UserFacade {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public UserDTO findUserByEmail(String username) {
        return beanMappingService.mapTo(userService.findUserByUsername(username), UserDTO.class);
    }

    @Override
    public Collection<UserDTO> findAllUsers() {
        return beanMappingService.mapTo(userService.findAllUsers(), UserDTO.class);
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        return userService.authenticate(
                userService.findUserByUsername(u.getUsername()), u.getPassword());
    }
    
}
