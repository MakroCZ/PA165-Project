package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.*;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class AlbumServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private AlbumDao albumDao;

    @Mock
    private PerformerService performerService;

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
        album = new Album();
        performer = new Performer();
        song = new Song();
        genre =new Genre();
        album.setName("Name");
        album.setReleaseDate(LocalDate.now());
        album.setPerformer(performer);
        genre.setName("genre");
        genre.setDescription("desc");
        song.setName("song");
        song.setSongLength(LocalTime.of(0,5,0));
        song.setReleaseDate(LocalDate.now());
        song.setAlbum(album);
        song.setGenre(genre);
        performer.addAlbum(album);
        performer.setStartDate(LocalDate.now());
        performer.setCountry("CZ");
        performer.setName("performer");
    }
    @AfterMethod
    void clean(){
        Mockito.reset(albumDao);
        Mockito.reset(performerService);
    }

    @Test
    void testFind(){
        album.setId(1L);
        Mockito.doReturn(album).when(albumDao).findById(1L);
        Album a = albumDao.findById(1L);
        Assert.assertEquals(a, album);
        Album album1 = albumService.find(1L);
        Assert.assertEquals(album, album1);
    }
    @Test
    void testFindNonExisting(){
        when(albumDao.findById(1L)).thenReturn(null);
        Assert.assertEquals(albumService.find(1L), null);
    }


    @Test
    void testFindAll(){
        album.setId(1L);
        List<Album> all = new ArrayList<>();
        all.add(album);
        when(albumDao.findAll()).thenReturn(all);
        albumDao.findAll();
        verify(albumDao).findAll();
    }

    @Test
    void testCreate(){
        album.setId(1L);
        Mockito.doNothing().when(albumDao).create(any());
        Album a = albumService.create(album);
        Assert.assertEquals(album, a);
    }
    @Test(expectedExceptions = DataAccessException.class)
    void testCreateNull(){
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(albumDao).create(null);
        albumService.create(null);
    }

    @Test
    void testUpdate(){
        album.setId(1L);
        album.setName("NoName");
        album.setReleaseDate(LocalDate.now());
        doNothing().when(albumDao).update(any(Album.class));
        albumService.update(album);
        verify(albumDao).update(album);
    }

    @Test(expectedExceptions = DataAccessException.class)
    void testUpdateNull(){
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(albumDao).update(null);
        albumService.update(null);
    }

    @Test
    void testFindAlbumsFromCountry(){
        album.setId(1L);
        album.setPerformer(performer);
        Album a = new Album();
        a.setId(2L);
        a.setReleaseDate(LocalDate.now());
        a.setPerformer(performer);
        a.setName("album");
        performer.addAlbum(a);

        List<Performer> performers = new ArrayList<>();
        performers.add(performer);
        doReturn(performers).when(performerService).findAll();
        List<Album> albums = albumService.findAlbumsFromCountry("CZ");
        Assert.assertEquals(albums.size(), 2);
    }

    @Test
    void testNoPerformerInCountry(){
        album.setId(1L);
        album.setPerformer(performer);
        Album a = new Album();
        a.setId(2L);
        a.setReleaseDate(LocalDate.now());
        a.setPerformer(performer);
        a.setName("album");
        performer.addAlbum(a);

        List<Performer> performers = new ArrayList<>();
        performers.add(performer);
        doReturn(performers).when(performerService).findAll();
        List<Album> albums = albumService.findAlbumsFromCountry("HG");
        Assert.assertEquals(albums.size(), 0);
    }

    @Test
    void noPerformersAtAll(){
        List<Performer> performers = new ArrayList<>();
        doReturn(performers).when(performerService).findAll();
        List<Album> albums = albumService.findAlbumsFromCountry("HG");
        Assert.assertEquals(albums.size(), 0);
    }


    @Test
    void deleteTest(){
        doNothing().when(albumDao).delete(album);
        albumService.delete(album);
        verify(albumDao).delete(album);
    }

    @Test(expectedExceptions = DataAccessException.class)
    void testDeleteNull(){
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(albumDao).delete(null);
        albumService.delete(null);
    }
}
