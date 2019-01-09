package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.dao.AlbumDao;
import cz.muni.fi.pa165.mm.daolayer.DAOLayerApplicationContext;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 * @author Lukáš Suchánek; 433564
 */
@ContextConfiguration(classes = DAOLayerApplicationContext.class)
public class AlbumDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AlbumDao albumDao;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Performer performer;
    private Song song;
    private Album album;
    private Album updateAlbum;
    private Genre genre;

    @BeforeMethod
    public void onlyOnce(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        performer = new Performer();
        performer.setCountry("CZ");
        performer.setName("Performer");
        performer.setStartDate(LocalDate.of(1990, Month.APRIL, 15));
        song = new Song();
        song.setName("Song");
        song.setDate(LocalDate.now());
        song.setLength(LocalTime.of(0,1,30));
        album = new Album();
        album.setName("Album");
        album.setDate(LocalDate.now());
        album.setPerformer(performer);
        updateAlbum = new Album();
        updateAlbum.setName("Album update");
        updateAlbum.setDate(LocalDate.now());
        updateAlbum.setPerformer(performer);
        genre = new Genre();
        genre.setName("Genre");
        genre.setDescription("Desc");
        song.setGenre(genre);
        song.setAlbum(album);
        album.addSong(song);
        em.persist(performer);
        em.persist(album);
        em.persist(updateAlbum);
        em.persist(genre);
        em.persist(song);

        em.getTransaction().commit();
        em.close();
    }
    
    @AfterMethod
    public void resetDB() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Song> ss = em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
        for (Song s : ss) {
            em.remove(s);
        }
        List<Album> as = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        for (Album a : as) {
            em.remove(a);
        }
        List<Performer> ps = em.createQuery("SELECT p FROM Performer p", Performer.class).getResultList();
        for (Performer p : ps) {
            em.remove(p);
        }

        List<Genre> gs = em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
        for (Genre g : gs) {
            em.remove(g);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void createTest(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album a = new Album();
        a.setName("Test album");
        a.setDate(LocalDate.now());
        a.setPerformer(performer);
        albumDao.create(a);
        performer.addAlbum(a);

        Album a2 = albumDao.retrieve(a.getId());

        Assert.assertEquals(a, a2);

//        em.getTransaction().rollback();
    }

    @Test(expectedExceptions=InvalidDataAccessApiUsageException.class)
    public void createNullTest(){
        albumDao.create(null);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createNullNameTest(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album a = new Album();
        a.setName(null);
        a.setDate(LocalDate.now());
        a.setPerformer(performer);
        performer.addAlbum(a);
        albumDao.create(a);

        em.getTransaction().rollback();
    }


    @Test(expectedExceptions=ConstraintViolationException.class)
    public void createNullDateTest(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album a = new Album();
        a.setName("Test");
        a.setDate(null);
        a.setPerformer(performer);
        performer.addAlbum(a);
        albumDao.create(a);

        em.getTransaction().rollback();
    }

    @Test
    public void updateTest(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album a2 = albumDao.retrieve(updateAlbum.getId());
        a2.setDate(LocalDate.of(2017, Month.AUGUST,15));
        a2.setName("Update test album");
        albumDao.update(a2);
        Album a3 = albumDao.retrieve(updateAlbum.getId());
        Assert.assertEquals(a2, a3);

        em.getTransaction().rollback();
    }

    @Test(expectedExceptions=InvalidDataAccessApiUsageException.class)
    public void updateNullTest(){
        albumDao.update(null);
    }


    @Test
    public void deleteTest(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album a = new Album();
        a.setName("Test Delete album");
        a.setDate(LocalDate.now());
        a.setPerformer(performer);
        performer.addAlbum(a);
        albumDao.create(a);

        Assert.assertEquals(a, albumDao.retrieve(a.getId()));
        albumDao.delete(a);
        Album a2 = albumDao.retrieve(a.getId());
        Assert.assertEquals(null, a2);

        em.getTransaction().rollback();
    }

    @Test(expectedExceptions=InvalidDataAccessApiUsageException.class)
    public void deleteNullTest(){
        albumDao.delete(null);
    }

    @Test
    public void retrieveNonExistingId(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Album a = albumDao.retrieve(1000L);
        Assert.assertEquals(a, null);

        em.getTransaction().rollback();
    }

    @Test
    public void retrieveAllTest(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Album> albumList = albumDao.retrieveAll();
        Assert.assertEquals(albumList.size(),2);
        Assert.assertEquals(albumList.get(0), album);
        Assert.assertEquals(albumList.get(1), updateAlbum);


        em.getTransaction().rollback();
    }


}
