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
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

/**
 * Created by lsuchanek on 18.11.2018.
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class AlbumFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private AlbumDao albumDao;

//    @Autowired
//    @InjectMocks
//    private AlbumService albumService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    private Album testAlbum;
    private Song song;
    private Genre genre;
    private Performer performer;

    @BeforeMethod
    public void prepareTestAlbum(){
        song = new Song();
        song.setDate(LocalDate.now());
        song.setLength(LocalTime.of(0,3,33));
        song.setName("Song");

        genre = new Genre();
        genre.setDescription("description");
        genre.setName("genre");

        performer = new Performer();
        performer.setCountry("CZ");
        performer.setName("performer");
        performer.setStartDate(LocalDate.of(1980, Month.APRIL, 15));

        testAlbum = new Album();
        testAlbum.setDate(LocalDate.now());
        testAlbum.setName("ABC");

        song.setGenre(genre);
        song.setAlbum(testAlbum);

        testAlbum.setPerformer(performer);
        testAlbum.addSong(song);

        performer.addAlbum(testAlbum);
    }


}
