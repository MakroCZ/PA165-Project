package cz.nubi.fi.pa165.mm.sf;

import cz.muni.fi.pa165.mm.daolayer.dao.AlbumDao;
import cz.muni.fi.pa165.mm.daolayer.dao.GenreDao;
import cz.muni.fi.pa165.mm.daolayer.dao.PerformerDao;
import cz.muni.fi.pa165.mm.daolayer.dao.SongDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.sf.service.AlbumService;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class AlbumServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private AlbumDao albumDao;
    @Mock
    private SongDao songDao;
    @Mock
    private PerformerDao performerDao;
    @Mock
    private GenreDao genreDao;
    @Autowired
    @InjectMocks
    private AlbumService albumService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    private Album album = new Album();
    private Performer performer = new Performer();
    private Song song = new Song();
    private Genre genre = new Genre();

    @BeforeMethod
    void prepareTestAlbum(){
        album.setName("Name");
        album.setDate(LocalDate.now());
        album.setPerformer(performer);
        genre.setName("genre");
        genre.setDescription("desc");
        song.setName("song");
        song.setLength(LocalTime.of(0,5,0));
        song.setDate(LocalDate.now());
        song.setAlbum(album);
        song.setGenre(genre);
        performer.addAlbum(album);
        performer.setStartDate(LocalDate.now());
        performer.setCountry("CZ");
        performer.setName("performer");
    }

    @Test
    void testRetrieve(){
        album.setId(1L);
        when(albumDao.retrieve(1L)).thenReturn(album);
        Album album1 = albumService.retrieve(1L);
        Assert.assertEquals(album, album1);
    }
    @Test
    void testRetrieveNonExisting(){
        when(albumDao.retrieve(1L)).thenReturn(album);
    }

    @Test
    void testRetrieveAll(){
        album.setId(1L);
        when(albumDao.retrieveAll()).thenReturn(Collections.emptyList());
        List<Album> albums = albumService.retrieveAll();
        Assert.assertTrue(albums.isEmpty());
        when(albumDao.retrieveAll()).thenReturn(Collections.singletonList(album));
        Assert.assertEquals(albums.size(), 1);
        Assert.assertEquals(albums.get(0), album);
    }

    @Test
    void testCreate(){
        album.setId(1L);
        Mockito.doNothing().when(albumDao);
        Album a = albumService.create(album);
        Assert.assertEquals(album, a);
    }

    @Test(expectedExceptions = NullPointerException.class)
    void createNull(){
        Mockito.doThrow(NullPointerException.class).when(albumDao);
        albumService.create(null);
    }

}
