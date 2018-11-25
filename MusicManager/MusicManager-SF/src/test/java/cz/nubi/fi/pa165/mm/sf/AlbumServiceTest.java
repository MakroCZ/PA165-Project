package cz.nubi.fi.pa165.mm.sf;

import cz.muni.fi.pa165.mm.daolayer.dao.AlbumDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;

@ContextConfiguration(classes= ServiceConfiguration.class)
public class AlbumServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    AlbumDao albumDao;

    @Autowired
    @InjectMocks
    private AlbumService albumService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    private Album album;
    private Performer performer;
    private Song song;
    private Genre genre;

    @BeforeMethod
    void prepareTestAlbum(){
        album = new Album();
        album.setName("Name");
        album.setDate(LocalDate.now());

    }
}
