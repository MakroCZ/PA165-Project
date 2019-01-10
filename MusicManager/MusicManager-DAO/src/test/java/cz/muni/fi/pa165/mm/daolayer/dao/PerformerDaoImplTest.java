package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.DAOLayerApplicationContext;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import java.time.LocalDate;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 * @author Václav Stehlík; 487580
 */
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@ContextConfiguration(classes = DAOLayerApplicationContext.class)
public class PerformerDaoImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private PerformerDao performerDao;

    @PersistenceContext
    private EntityManager em;

    private Performer performer1;
    private Performer performer2;

    @BeforeMethod
    public void init() {
        performer1 = new Performer();
        performer1.setName("TestName1");
        performer1.setCountry("TestCountry1");
        performer1.setStartDate(LocalDate.of(2012, Month.MARCH, 5));
        em.persist(performer1);

        performer2 = new Performer();
        performer2.setName("TestName2");
        performer2.setCountry("TestCountry2");
        performer2.setStartDate(LocalDate.of(2001, Month.JUNE, 18));
        em.persist(performer2);
    }

    @Test
    public void testCreatePerformer() {
        Performer performer = new Performer();
        performer.setName("TestName3");
        performer.setCountry("TestCountry3");
        performer.setStartDate(LocalDate.of(1987, Month.AUGUST, 23));
        performerDao.create(performer);
        Assert.assertNotNull(performerDao.findById(performer.getId()));
        Assert.assertEquals(performerDao.findById(performer.getId()), performer);
        performerDao.remove(performer);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
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

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void testDeleteNullPerformer() {
        performerDao.remove(null);
    }

    @Test
    public void testUpdatePerformer() {
        Performer performerUpdate = performer1;
        performerUpdate.setName("TestNameUpdated");
        em.detach(performer1);
        performerDao.update(performerUpdate);
        Assert.assertEquals(performerDao.findById(performerUpdate.getId()).getName(), "TestNameUpdated");
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
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
