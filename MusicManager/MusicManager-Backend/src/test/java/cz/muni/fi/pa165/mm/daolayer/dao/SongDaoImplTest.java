package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.MMBackendApplicationContext;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.musicmanager.backend.service.GenreServiceImpl;
import cz.muni.fi.pa165.musicmanager.backend.service.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

/**
 * This java class represents unit tests for the SongServiceImpl class
 * @author Yehor Safonov; 487596
 */

@ContextConfiguration(classes = MMBackendApplicationContext.class)
public class SongDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SongServiceImpl songServiceImpl;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Album album;
    private Genre genre;
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Song song5;
    private Song song6;

    @BeforeClass
    public void setup(){
        EntityManager e= emf.createEntityManager();
        e.getTransaction().begin();

        /*
            Creating a genre and setting its parametrs
        */
        genre = new Genre();
        genre.setName("Rock");
        genre.setDescription("Rock music is a broad genre of popular music that originated as" +
                " \"rock and roll\" in the United States in the early 1950s, and developed into" +
                " a range of different styles in the 1960s and later, particularly in the United" +
                " Kingdom and in the United States");

        e.persist(genre);


         /*
             Creating an album and setting its parametrs
         */
        album = new Album();
        album.setDate(LocalDate.of(2015, Month.APRIL, 16));
        album.setName("Final masquerade");

        e.persist(album);


        /*
             Creating a set of different songs and setting their parametrs
        */
        song1 = new Song();
        song2 = new Song();
        song3 = new Song();

        song1.setName("In the end");
        song1.setDate(LocalDate.of(2014, Month.MAY, 10));
        song1.setAlbum(album);
        song1.setGenre(genre);
        song1.setLength(LocalTime.of(0,4,45));

        song2.setName("Somewhere I belong");
        song2.setDate(LocalDate.of(2015, Month.JANUARY, 12));
        song2.setAlbum(album);
        song2.setGenre(genre);
        song2.setLength(LocalTime.of(0,3,45));

        song3.setName("Burn it down");
        song3.setDate(LocalDate.of(2014, Month.SEPTEMBER, 25));
        song3.setAlbum(album);
        song3.setGenre(genre);
        song3.setLength(LocalTime.of(0,2,56));

        e.persist(song1);
        e.persist(song2);
        e.persist(song3);

        e.getTransaction().commit();
        e.close();
    }

    @Test
    public void createSongsTest(){
        /*
             Creating three songs and setting its parametrs
        */

        song4 = new Song();
        song4.setName("One step closer");
        song4.setDate(LocalDate.of(2000, Month.APRIL, 19));
        song4.setAlbum(album);
        song4.setGenre(genre);
        song4.setLength(LocalTime.of(0,2,55));


        song5 = new Song();
        song5.setName("Crawling");
        song5.setDate(LocalDate.of(2003, Month.OCTOBER, 3));
        song5.setAlbum(album);
        song5.setGenre(genre);
        song5.setLength(LocalTime.of(0,1,25));


        song6 = new Song();
        song6.setName("Heavy");
        song6.setDate(LocalDate.of(2017, Month.SEPTEMBER, 16));
        song6.setAlbum(album);
        song6.setGenre(genre);
        song6.setLength(LocalTime.of(0,4,45));


        songServiceImpl.create(song4);
        songServiceImpl.create(song5);
        songServiceImpl.create(song6);
    }

    @Test
    public void findByIdTest(){
         /*
             Compare if the song number 5 with the required id exists
         */
        Assert.assertEquals(songServiceImpl.findById(song5.getId()).getId(),song5.getId());
    }

    @Test
    public void deleteSongTest(){
        /*
             Try to delete the song number 6 and check if the song exists
        */
        songServiceImpl.delete(song6);
        Assert.assertEquals(songServiceImpl.findById(song6.getId()),null);
    }

    @Test
    public void updateSongTest() {
        /*
             Try to update the song information
        */
        song3.setName("Castle of Glass");
        song3.setDate(LocalDate.of(2012, Month.MARCH, 5));
        songServiceImpl.update(song3);

        /*
             Find the song and compare set parametrs
        */
        Assert.assertEquals(songServiceImpl.findById(song3.getId()).getId(),song3.getId());
        Assert.assertEquals(songServiceImpl.findById(song3.getId()).getName(),"Castle of Glass");
        Assert.assertEquals(songServiceImpl.findById(song3.getId()).getDate(),LocalDate.of(2012, Month.MARCH, 5));
    }

    @Test
    public void findAllTest() {
        /*
             Try to find all song and check list count
        */
        List<Song> songs = songServiceImpl.findAll();
        Assert.assertEquals(songs.size(),5);
    }

    @Test
    public void dependenciesTest() {
        /*
             Try to find the song number 2 and compare if dependencies
              to the Album and Genre are set correctly
        */
        Song testSong = songServiceImpl.findById(song2.getId());
        Assert.assertEquals(testSong.getAlbum().getId(),album.getId());
        Assert.assertEquals(testSong.getGenre().getId(),genre.getId());
    }

}
