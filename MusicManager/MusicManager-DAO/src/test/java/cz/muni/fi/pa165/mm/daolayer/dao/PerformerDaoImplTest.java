package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.dao.PerformerDao;
import cz.muni.fi.pa165.mm.daolayer.DAOLayerApplicationContext;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ContextConfiguration(classes = DAOLayerApplicationContext.class)
public class PerformerDaoImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private PerformerDao performerDao;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Performer performer1;
    private Performer performer2;

    @BeforeMethod
    public void init() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        performer1 = new Performer();
        performer1.setName("TestName1");
        em.persist(performer1);

        performer2 = new Performer();
        performer2.setName("TestName2");
        em.persist(performer2);

        em.getTransaction().commit();
        em.close();
    }

    @AfterMethod
    public void dispose() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(performer1));
        em.remove(em.merge(performer2));
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testCreatePerformer() {
        Performer performer = new Performer();
        performer.setName("TestName3");
        performerDao.create(performer);
        Assert.assertNotNull(performerDao.findById(performer.getId()));
        Assert.assertEquals(performerDao.findById(performer.getId()), performer);
        performerDao.remove(performer);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullPerformer() {
        performerDao.create(null);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testCreateNullName() {
        Performer performer = new Performer();
        performer.setName(null);
        performerDao.create(performer);
        performerDao.remove(performer);
    }

    @Test
    public void testFindByIdPerformer() {
        Assert.assertEquals(performerDao.findById(performer1.getId()).getName(), "TestName1");
    }

    @Test
    public void testFindByIdPerformerNull() {
        Assert.assertNull(performerDao.findById(1000L));
    }

    @Test
    public void testFindAllPerformer() {
        Assert.assertEquals(performerDao.findAll().size(), 2);
    }

    @Test
    public void testFindAllEmptyPerformer() {
        performerDao.remove(performer1);
        performerDao.remove(performer2);
        Assert.assertEquals(performerDao.findAll().size(), 0);
    }

    @Test
    public void testRemovePerformer() {
        performerDao.remove(performer2);
        Assert.assertNull(performerDao.findById(performer2.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullPerformer() {
        performerDao.remove(null);
    }

    @Test
    public void testUpdatePerformer() {
        Performer performerUpdate = performer1;
        performerUpdate.setName("TestNameUpdated");
        performerDao.update(performerUpdate);
        Assert.assertEquals(performerDao.findById(performerUpdate.getId()).getName(), "TestNameUpdated");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullPerformer() {
        performerDao.update(null);
    }

    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testUpdateNullName() {
        Performer performer = new Performer();
        performer.setName(null);
        performerDao.update(performer);
        performerDao.remove(performer);
    }
}
