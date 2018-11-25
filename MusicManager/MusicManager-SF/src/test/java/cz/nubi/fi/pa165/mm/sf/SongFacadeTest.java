package cz.nubi.fi.pa165.mm.sf;

import cz.muni.fi.pa165.mm.api.facade.SongFacade;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * This java class represents unit tests for the SongFacate layer
 * @author Yehor Safonov; 487596
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class SongFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SongFacade songFacade;

    @Test
    public  void  testCreateSong(){}

    @Test
    public  void  testDeleteSong(){}

    @Test
    public  void testGetAllSongs(){}

    @Test
    public void testGetSongWithID(){}
}
