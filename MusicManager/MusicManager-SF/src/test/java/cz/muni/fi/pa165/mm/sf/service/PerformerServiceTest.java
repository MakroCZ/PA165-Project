package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.PerformerDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.sf.service.PerformerService;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Václav Stehlík; 487580
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PerformerServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    @InjectMocks
    private PerformerService performerService;

    @Mock
    private PerformerDao performerDao;

    private Performer performer1;
    private Performer performer2;
    private Album album1;
    private Album album2;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void init() {
        performer1 = new Performer();
        performer2 = new Performer();
        album1 = new Album();
        album2 = new Album();

        performer1.setId(1L);
        performer1.setName("John Doe");
        performer1.setCountry("UK");
        performer1.setStartDate(LocalDate.of(1984, Month.DECEMBER, 21));
        performer1.addAlbum(album1);

        performer2.setId(2L);
        performer2.setName("John Lark");
        performer2.setCountry("US");
        performer2.setStartDate(LocalDate.of(1981, Month.APRIL, 4));

        album1.setId(1L);
        album1.setName("Unit testing now or never.");
        album1.setReleaseDate(LocalDate.of(2018, Month.NOVEMBER, 26));
        album1.setPerformer(performer1);

        album2.setId(2L);
        album2.setName("Too late to start with unit testing.");
        album2.setReleaseDate(LocalDate.of(2018, Month.NOVEMBER, 27));
    }

    @Test
    public void testCreate() {
        Mockito.doNothing().when(performerDao).create(performer1);
        performerDao.create(performer1);
        verify(performerDao, times(1)).create(performer1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testCreateNull() {
        Mockito.doThrow(NullPointerException.class).when(performerDao).create(null);
        performerService.create(null);
    }

    @Test
    public void testFindById() {
        when(performerDao.findById(1L)).thenReturn(performer1);
        Performer testPerformer = performerService.findById(1L);
        Assert.assertEquals(performer1, testPerformer);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFindByIdNull() {
        Mockito.doThrow(NullPointerException.class).when(performerDao).findById(100L);
        performerService.findById(100L);
    }

    @Test
    public void testFindAll() {
        when(performerDao.findAll()).thenReturn(Collections.emptyList());
        List<Performer> performers = performerService.findAll();
        Assert.assertTrue(performers.isEmpty());
        when(performerDao.findAll()).thenReturn(Arrays.asList(performer1, performer2));
        performers = performerService.findAll();
        Assert.assertEquals(performers.size(), 2);
        Assert.assertTrue(performers.contains(performer1));
        Assert.assertTrue(performers.contains(performer2));
    }

    @Test
    public void testUpdate() {
        Performer updatedPerformer = performer1;
        updatedPerformer.setCountry("CZ");
        Mockito.doNothing().when(performerDao).update(performer1);
        performerDao.update(updatedPerformer);
        verify(performerDao, times(1)).update(updatedPerformer);
    }

    @Test
    public void testRemove() {
        Mockito.doNothing().when(performerDao).remove(performer1);
        performerService.remove(performer1);
        verify(performerDao, times(1)).remove(performer1);
    }
}
