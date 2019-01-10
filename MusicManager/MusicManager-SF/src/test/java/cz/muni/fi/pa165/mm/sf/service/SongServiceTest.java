package cz.muni.fi.pa165.mm.sf.service;


import cz.muni.fi.pa165.mm.daolayer.dao.SongDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.sf.service.AlbumService;
import cz.muni.fi.pa165.mm.sf.service.GenreService;
import cz.muni.fi.pa165.mm.sf.service.SongService;
import cz.muni.fi.pa165.mm.sf.service.SongServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * This java class represents unit tests for the SongService layer
 * @author Yehor Safonov; 487596
 */

public class SongServiceTest {

    @Mock
    private SongDao songDao;

    @InjectMocks
    private SongService songService = new SongServiceImpl();

    private Song song_InTheEnd;
    private Song song_SomewhereIBelong;
    private Song song_BurnItDown;
    private Album albumLinkinkPark;
    private Genre genreRock;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeMethod() {
        /*
            Creating a genreRock and setting its parametrs
        */
        genreRock = new Genre();
        genreRock.setName("Rock");
        genreRock.setId(5L);
        genreRock.setDescription("Rock music is a broad genreRock of popular music that originated as" +
                " \"rock and roll\" in the United States in the early 1950s, and developed into" +
                " a range of different styles in the 1960s and later, particularly in the United" +
                " Kingdom and in the United States");

         /*
             Creating an albumLinkinkPark and setting its parametrs
         */
        albumLinkinkPark = new Album();
        albumLinkinkPark.setId(4L);
        albumLinkinkPark.setReleaseDate(LocalDate.of(2015, Month.APRIL, 16));
        albumLinkinkPark.setName("Final masquerade");

        /*
             Creating a set of different songs and setting their parametrs
        */
        song_InTheEnd = new Song();
        song_SomewhereIBelong = new Song();
        song_BurnItDown = new Song();

        song_InTheEnd.setId(1L);
        song_InTheEnd.setName("In the end");
        song_InTheEnd.setReleaseDate(LocalDate.of(2014, Month.MAY, 10));
        song_InTheEnd.setAlbum(albumLinkinkPark);
        song_InTheEnd.setGenre(genreRock);
        song_InTheEnd.setSongLength(LocalTime.of(0,4,45));

        song_SomewhereIBelong.setId(2L);
        song_SomewhereIBelong.setName("Somewhere I belong");
        song_SomewhereIBelong.setReleaseDate(LocalDate.of(2015, Month.JANUARY, 12));
        song_SomewhereIBelong.setAlbum(albumLinkinkPark);
        song_SomewhereIBelong.setGenre(genreRock);
        song_SomewhereIBelong.setSongLength(LocalTime.of(0,3,45));

        song_BurnItDown.setId(3L);
        song_BurnItDown.setName("Burn it down");
        song_BurnItDown.setReleaseDate(LocalDate.of(2014, Month.SEPTEMBER, 25));
        song_BurnItDown.setAlbum(albumLinkinkPark);
        song_BurnItDown.setGenre(genreRock);
        song_BurnItDown.setSongLength(LocalTime.of(0,2,56));
    }

    @Test
    public void testDelete() {
        /*
             Try to delete song_InTheEnd and verfy if corresponding action is done
        */
        songService.delete(song_InTheEnd);
        Mockito.verify(songDao, Mockito.times(1)).delete(song_InTheEnd);
    }


    @Test
    public void testCreate() {
         /*
             Creating a test song and setting its parametrs
        */
        Song testSong = new Song();
        testSong.setId(6L);
        testSong.setName("In the end test");
        testSong.setReleaseDate(LocalDate.of(2017, Month.MAY, 10));
        testSong.setAlbum(albumLinkinkPark);
        testSong.setGenre(genreRock);
        testSong.setSongLength(LocalTime.of(0,2,45));

         /*
             Try to create testSong and verfy if corresponding action is done
        */
        songService.create(testSong);
        Mockito.verify(songDao).create(testSong);
    }

    @Test
    public void testUpdate() {
        /*
             Try to update the song Castle of Glass information
        */
        song_InTheEnd.setName("New name");
        song_InTheEnd.setReleaseDate(LocalDate.of(2012, Month.MARCH, 5));

         /*
             Try to update song_InTheEnd and verfy if corresponding action is done
        */
        songService.update(song_InTheEnd);
        Mockito.verify(songDao).update(song_InTheEnd);
    }


    @Test
    public void testFindAll() {
         /*
             Try to find all Songs witch are presented
        */
        List<Song> allSongs = new ArrayList<>();
        allSongs.add(song_InTheEnd);
        allSongs.add(song_SomewhereIBelong);
        allSongs.add(song_BurnItDown);

        Mockito.when(songDao.findAll()).thenReturn(allSongs);
        List<Song> foundSongs = songService.findAll();

        Assert.assertEquals(allSongs.size(),foundSongs.size());
    }

    @Test
    public void findById() {
        /*
             Try to find all Songs with specific IDs
        */
        Mockito.when(songDao.findById(song_InTheEnd.getId())).thenReturn(song_InTheEnd);
        Mockito.when(songDao.findById(song_SomewhereIBelong.getId())).thenReturn(song_SomewhereIBelong);
        Mockito.when(songDao.findById(song_BurnItDown.getId())).thenReturn(song_BurnItDown);

        Song song_is_InTheEnd = songService.findById(song_InTheEnd.getId());
        Song song_is_SomewhereIBelong = songService.findById(song_SomewhereIBelong.getId());
        Song song_is_BurnItDown = songService.findById(song_BurnItDown.getId());

        /*
             Verify if songs are the same
        */
        Assert.assertEquals(song_is_InTheEnd, song_InTheEnd);
        Assert.assertEquals(song_is_SomewhereIBelong, song_SomewhereIBelong);
        Assert.assertEquals(song_is_BurnItDown, song_BurnItDown);
    }

    
    @Test(expectedExceptions = NullPointerException.class)
    public void testFindAllNullSongsFromSamePerformer() {
        /*
             Try to find all songs if performer is argument is null
        */
        songService.findAllSongsFromSamePerformer(null);
    }

    
    @Test
    public void testFindAllSongsFromSamePerformerIfEmptySongs() {
        /*
            Tests if returns the right array during calling the business function
        */
        Performer performer = new Performer();
        performer.setName("Daniel Benningn");
        performer.addAlbum(albumLinkinkPark);
        List<Song> songs = new ArrayList<>();
        Mockito.when(songDao.findAll()).thenReturn(songs);
        List<Song> songsOfThePerformer = songService.findAllSongsFromSamePerformer(song_InTheEnd);
        Assert.assertEquals(songsOfThePerformer.size(), albumLinkinkPark.getSongs().size());
    }

    @Test(expectedExceptions = DataAccessException.class)
    void testUpdateNull(){
        /*
            Tests if appears DataAccessException in case of updating null song
        */
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(songDao).update(null);
        songService.update(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    void testCreateNull(){
          /*
            Tests if appears DataAccessException in case of create null song
        */
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(songDao).create(null);
        songService.create(null);
    }

    @Test
    public void testFindAllSongsFromSamePerformer() {
        /*
             Create new album
        */
        Album albumMetallica = new Album();
        albumMetallica.setId(5L);
        albumMetallica.setReleaseDate(LocalDate.of(2016, Month.APRIL, 16));
        albumMetallica.setName("Final masquerade");

        /*
            Create a new song that has different performer
        */
        Song song_papercut =  new Song();
        song_papercut.setId(3L);
        song_papercut.setName("Papercut");
        song_papercut.setReleaseDate(LocalDate.of(2014, Month.SEPTEMBER, 25));
        song_papercut.setAlbum(albumMetallica);
        song_papercut.setGenre(genreRock);
        song_papercut.setSongLength(LocalTime.of(0,2,56));

        /*
             Create new performer
        */
        Performer performer = new Performer();
        performer.setName("Chester Bennington");
        
        albumMetallica.setPerformer(performer);

        /*
             Create a list of all songs
        */
        List<Song> allSongs = new ArrayList<>();
        allSongs.add(song_InTheEnd);
        allSongs.add(song_SomewhereIBelong);
        allSongs.add(song_BurnItDown);
        allSongs.add(song_papercut);

        List<Song> songsOfThePerformer =  new ArrayList<>();
        Mockito.when(songDao.findAll()).thenReturn(allSongs);
        songsOfThePerformer = songService.findAllSongsFromSamePerformer(song_InTheEnd);
        Assert.assertNotEquals(songsOfThePerformer, allSongs);
        songsOfThePerformer = songService.findAllSongsFromSamePerformer(song_SomewhereIBelong);
        Assert.assertNotEquals(songsOfThePerformer, allSongs);
    }
}
