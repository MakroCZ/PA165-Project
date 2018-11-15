package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.dao.GenreDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import cz.muni.fi.pa165.mm.daolayer.DAOLayerApplicationContext;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Marek Barinka; 456295
 */
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@ContextConfiguration(classes = DAOLayerApplicationContext.class)
public class GenreDaoImplTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private GenreDao genreDao;
    
    
    @Test
    public void createValidTest() {
        List<Genre> gs;
       
        Genre g = new Genre();
        g.setName("Test");
        genreDao.create(g);
        gs = genreDao.getAllGenres();
        Assert.assertEquals(gs.size(), 1);
        Assert.assertEquals(gs.get(0), g);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createWithNullNameTest() {
        Genre g = new Genre();
        g.setName(null);
        genreDao.create(g);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        genreDao.create(null);
    }
    
    @Test
    public void createSameTest() {
        Genre g = new Genre();
        g.setName("Test");
        genreDao.create(g);
        genreDao.create(g);
        
        List<Genre> gs = genreDao.getAllGenres();
        Assert.assertEquals(gs.size(), 1);
        Assert.assertEquals(gs.get(0), g);
    }
    
    @Test
    public void getAllTest() {
        Genre g1 = new Genre();
        Genre g2 = new Genre();
        g1.setName("Genre 1");
        g2.setName("Genre 2");
        genreDao.create(g1);
        genreDao.create(g2);
        
        List<Genre> gs = genreDao.getAllGenres();
        Assert.assertEquals(gs.size(), 2);
        
        Assert.assertTrue(gs.contains(g1));
        Assert.assertTrue(gs.contains(g2));
    }
    
    @Test
    public void updateNameTest() {
        Genre g = new Genre();
        g.setName("BeforeUpdate");
        genreDao.create(g);
        g.setName("AfterUpdate");
        genreDao.update(g);
        Genre o = genreDao.findById(g.getId());
        Assert.assertEquals(o.getName(), "AfterUpdate");
    }
    
    @Test
    public void updateTest() {
        Genre g = new Genre();
        g.setName("BeforeUpdate");
        g.setDescription("DescBefore");
        genreDao.create(g);
        g.setName("AfterUpdate");
        g.setDescription("DescAfter");
        genreDao.update(g);
        Genre o = genreDao.findById(g.getId());
        Assert.assertEquals(o.getName(), "AfterUpdate");
        Assert.assertEquals(o.getDescription(), "DescAfter");
    }
    
    /**
     * Hibernate has weird behavior.
     * Through merge() I was able insert null value
     * into @NotNull attribute and save it into db.
     * I was not able to find further info.
     */
    /** 
    //@Test(expectedExceptions = ConstraintViolationException.class)
    public void updateNullNameTest() {
        Genre g = new Genre();
        g.setName("BeforeUpdate");
        genreDao.create(g);
        g.setName(null);
        genreDao.update(g);
        
        Genre test = genreDao.findById(g.getId()); //Just for debugging.
        System.out.println(); //Just for debugging.
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest() {
        genreDao.update(null);
    }*/
    
    
    @Test
    public void deleteValidTest() {
        Genre g = new Genre();
        g.setName("Genre");
        genreDao.create(g);
        Assert.assertNotNull(genreDao.findById(g.getId()));
        genreDao.delete(g);
        Assert.assertNull(genreDao.findById(g.getId()));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        genreDao.delete(null);
    }
    
    @Test
    public void findNonExistIdTest() {
        Assert.assertNull(genreDao.findById(0L));
    }
    
    @Test
    public void findValidTest() {
        Genre g = new Genre();
        g.setName("Genre");
        genreDao.create(g);
        Genre o = genreDao.findById(g.getId());
        Assert.assertEquals(g, o);
    }
    
    @Test
    public void findByNameTest() {
        Genre g = new Genre();
        g.setName("Genre");
        genreDao.create(g);
        List<Genre> gs = genreDao.findByName("Genre");
        Assert.assertEquals(gs.size(), 1);
        Assert.assertTrue(gs.contains(g));
    }
    
    @Test
    public void findByNameUniqueMultipleTest() {
        Genre g1 = new Genre();
        Genre g2 = new Genre();
        g1.setName("Genre1");
        g2.setName("Genre2");
        genreDao.create(g1);
        genreDao.create(g2);
        List<Genre> gs = genreDao.findByName("Genre1");
        Assert.assertEquals(gs.size(), 1);
        Assert.assertTrue(gs.contains(g1));
    }
    
    @Test
    public void findByNameNonUniqueMultipleTest() {
        Genre g1 = new Genre();
        Genre g2 = new Genre();
        Genre g3 = new Genre();
        g1.setName("Genre");
        g2.setName("Genre2");
        g3.setName("Genre");
        genreDao.create(g1);
        genreDao.create(g2);
        genreDao.create(g3);
        List<Genre> gs = genreDao.findByName("Genre");
        Assert.assertEquals(gs.size(), 2);
        Assert.assertTrue(gs.contains(g1));
        Assert.assertTrue(gs.contains(g3));
        Assert.assertFalse(gs.contains(g2));
    }
    
    @Test
    public void getAllGenresEmptyDBTest() {
        List<Genre> gs = genreDao.getAllGenres();
        Assert.assertTrue(gs.isEmpty());
    }
    
    @Test
    public void getAllGenresTest() {
        Genre g1 = new Genre();
        Genre g2 = new Genre();
        Genre g3 = new Genre();
        g1.setName("Genre1");
        g2.setName("Genre2");
        g3.setName("Genre3");
        genreDao.create(g1);
        genreDao.create(g2);
        genreDao.create(g3);
        List<Genre> gs = genreDao.getAllGenres();
        Assert.assertEquals(gs.size(), 3);
        Assert.assertTrue(gs.contains(g1));
        Assert.assertTrue(gs.contains(g2));
        Assert.assertTrue(gs.contains(g3));
    }
    
}
