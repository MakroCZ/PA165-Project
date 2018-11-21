package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.MMBackendApplicationContext;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

/**
 * This java class represents unit tests for the SongDaoImpl class
 * @author Yehor Safonov; 487596
 */

@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@ContextConfiguration(classes = MMBackendApplicationContext.class)
public class SongDaoImplTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private SongDao songDao;

    private Album albumLinkinkPark;
    private Genre genreRock;
    private Song song_InTheEnd;
    private Song song_SomewhereIBelong;
    private Song song_BurnItDown;
    private Song song_OneStepCloser;
    private Song song_Crawling;
    private Song song_Heavy;

    @BeforeClass
    public void setup(){
        EntityManager e= emf.createEntityManager();
        e.getTransaction().begin();

        /*
            Creating a genreRock and setting its parametrs
        */
        genreRock = new Genre();
        genreRock.setName("Rock");
        genreRock.setDescription("Rock music is a broad genreRock of popular music that originated as" +
                " \"rock and roll\" in the United States in the early 1950s, and developed into" +
                " a range of different styles in the 1960s and later, particularly in the United" +
                " Kingdom and in the United States");

        e.persist(genreRock);


         /*
             Creating an albumLinkinkPark and setting its parametrs
         */
        albumLinkinkPark = new Album();
        albumLinkinkPark.setDate(LocalDate.of(2015, Month.APRIL, 16));
        albumLinkinkPark.setName("Final masquerade");

        e.persist(albumLinkinkPark);


        /*
             Creating a set of different songs and setting their parametrs
        */
        song_InTheEnd = new Song();
        song_SomewhereIBelong = new Song();
        song_BurnItDown = new Song();

        song_InTheEnd.setName("In the end");
        song_InTheEnd.setDate(LocalDate.of(2014, Month.MAY, 10));
        song_InTheEnd.setAlbum(albumLinkinkPark);
        song_InTheEnd.setGenre(genreRock);
        song_InTheEnd.setLength(LocalTime.of(0,4,45));

        song_SomewhereIBelong.setName("Somewhere I belong");
        song_SomewhereIBelong.setDate(LocalDate.of(2015, Month.JANUARY, 12));
        song_SomewhereIBelong.setAlbum(albumLinkinkPark);
        song_SomewhereIBelong.setGenre(genreRock);
        song_SomewhereIBelong.setLength(LocalTime.of(0,3,45));

        song_BurnItDown.setName("Burn it down");
        song_BurnItDown.setDate(LocalDate.of(2014, Month.SEPTEMBER, 25));
        song_BurnItDown.setAlbum(albumLinkinkPark);
        song_BurnItDown.setGenre(genreRock);
        song_BurnItDown.setLength(LocalTime.of(0,2,56));

        song_OneStepCloser = new Song();
        song_OneStepCloser.setName("One step closer");
        song_OneStepCloser.setDate(LocalDate.of(2000, Month.APRIL, 19));
        song_OneStepCloser.setAlbum(albumLinkinkPark);
        song_OneStepCloser.setGenre(genreRock);
        song_OneStepCloser.setLength(LocalTime.of(0,2,55));


        song_Crawling = new Song();
        song_Crawling.setName("Crawling");
        song_Crawling.setDate(LocalDate.of(2003, Month.OCTOBER, 3));
        song_Crawling.setAlbum(albumLinkinkPark);
        song_Crawling.setGenre(genreRock);
        song_Crawling.setLength(LocalTime.of(0,1,25));


        song_Heavy = new Song();
        song_Heavy.setName("Heavy");
        song_Heavy.setDate(LocalDate.of(2017, Month.SEPTEMBER, 16));
        song_Heavy.setAlbum(albumLinkinkPark);
        song_Heavy.setGenre(genreRock);
        song_Heavy.setLength(LocalTime.of(0,4,45));

        e.persist(song_InTheEnd);
        e.persist(song_SomewhereIBelong);
        e.persist(song_BurnItDown);
        e.persist(song_OneStepCloser);
        e.persist(song_Crawling);
        e.persist(song_Heavy);

        e.getTransaction().commit();
        e.close();
    }

    @Test
    public void createSongsTest(){
        /*
             Creating a test song and setting its parametrs
        */

        Song testSong = new Song();

        testSong.setName("TestSong");
        testSong.setDate(LocalDate.of(2017, Month.MAY, 10));
        testSong.setAlbum(albumLinkinkPark);
        testSong.setGenre(genreRock);
        testSong.setLength(LocalTime.of(0,2,45));
        songDao.create(testSong);

        Assert.assertNotNull(songDao.findById(testSong.getId()));
        Assert.assertEquals(songDao.findById(testSong.getId()), testSong);
    }

    @Test
    public void findByIdTest(){
         /*
             Compare if the songs song_Crawling, song_OneStepCloser, song_Heavy with the required ids exists
         */
        Assert.assertEquals(songDao.findById(song_Crawling.getId()).getId(),song_Crawling.getId());
        Assert.assertEquals(songDao.findById(song_OneStepCloser.getId()).getId(),song_OneStepCloser.getId());
        Assert.assertEquals(songDao.findById(song_Heavy.getId()).getId(), song_Heavy.getId());
    }

    @Test
    public void deleteSongTest(){
        /*
             Try to delete song_Heavy and check if the song exists
        */
        songDao.delete(song_Heavy);
        Assert.assertNull(songDao.findById(song_Heavy.getId()));
    }

    @Test
    public void updateSongTest() {
        /*
             Try to update the song Castle of Glass information
        */
        song_BurnItDown.setName("Castle of Glass");
        song_BurnItDown.setDate(LocalDate.of(2012, Month.MARCH, 5));
        songDao.update(song_BurnItDown);

        /*
             Find the song and compare set parametrs
        */
        Assert.assertEquals(songDao.findById(song_BurnItDown.getId()).getId(),song_BurnItDown.getId());
        Assert.assertEquals(songDao.findById(song_BurnItDown.getId()).getName(),"Castle of Glass");
        Assert.assertEquals(songDao.findById(song_BurnItDown.getId()).getDate(),LocalDate.of(2012, Month.MARCH, 5));
    }

    @Test
    public void findAllTest() {
        /*
             Try to find all song and check list count
        */
        List<Song> songs = songDao.findAll();
        Assert.assertEquals(songs.size(),6);
    }

    @Test
    public void dependenciesTest() {
        /*
             Try to find the song number 2 and compare if dependencies
              to the Album and Genre are set correctly
        */
        Song testSong = songDao.findById(song_SomewhereIBelong.getId());
        Assert.assertEquals(testSong.getAlbum().getId(),albumLinkinkPark.getId());
        Assert.assertEquals(testSong.getGenre().getId(),genreRock.getId());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullSongTest() {
        /*
             Try to delete null song
        */
        songDao.delete(null);
    }

    @Test
    public void findNonExistIdTest() {
        /*
             Try to find not existing song
        */
        Assert.assertNull(songDao.findById(0L));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createSongWithNullNameTest() {
        /*
             Try to create song with null parametr
        */
        Song s = new Song();
        s.setName(null);
        songDao.create(s);
    }

//    @Test(expectedExceptions= DataAccessException.class)
//    public void idIsUnique(){
//        Song testSong = new Song();
//        testSong.setName("TestSong");
//        testSong.setDate(LocalDate.of(2017, Month.MAY, 10));
//        testSong.setAlbum(albumLinkinkPark);
//        testSong.setGenre(genreRock);
//        testSong.setLength(LocalTime.of(0,2,45));
//        songDao.create(testSong);
//
//        testSong = new Song();
//        testSong.setName("TestSong");
//        testSong.setDate(LocalDate.of(2018, Month.MAY, 1));
//        testSong.setAlbum(albumLinkinkPark);
//        testSong.setGenre(genreRock);
//        testSong.setLength(LocalTime.of(0,2,45));
//        songDao.create(testSong);
//    }

}
