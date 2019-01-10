package cz.muni.fi.pa165.mm.sf.facade;

import cz.muni.fi.pa165.mm.api.dto.PerformerCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import cz.muni.fi.pa165.mm.api.facade.PerformerFacade;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.sf.facade.PerformerFacadeImpl;
import cz.muni.fi.pa165.mm.sf.service.AlbumService;
import cz.muni.fi.pa165.mm.sf.service.BeanMappingService;
import cz.muni.fi.pa165.mm.sf.service.PerformerService;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Václav Stehlík; 487580
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PerformerFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private PerformerService performerService;

    @Mock
    private AlbumService albumService;

    @Mock
    private BeanMappingService bms;

    @InjectMocks
    private PerformerFacade performerFacade = new PerformerFacadeImpl();

    private Performer performer1;
    private Performer performer2;
    private PerformerDTO performerDTO1;
    private PerformerDTO performerDTO2;
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
        performerDTO1 = new PerformerDTO();
        performerDTO2 = new PerformerDTO();
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

        performerDTO1.setId(1L);
        performerDTO1.setName("John Doe");
        performerDTO1.setCountry("UK");
        performerDTO1.setStartDate(LocalDate.of(1984, Month.DECEMBER, 21));

        performerDTO2.setId(2L);
        performerDTO2.setName("John Lark");
        performerDTO2.setCountry("US");
        performerDTO2.setStartDate(LocalDate.of(1981, Month.APRIL, 4));

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
        PerformerCreateDTO performerCreateDTO = new PerformerCreateDTO();
        performer1.setId(420L);
        when(bms.mapTo(performerCreateDTO, Performer.class)).thenReturn(performer1);
        long result = performerFacade.create(performerCreateDTO);
        Assert.assertEquals(Long.valueOf(result), Long.valueOf(420));
    }

    @Test
    public void testFindById() {
        when(performerService.findById(1L)).thenReturn(performer1);
        when(performerService.findById(2L)).thenReturn(performer2);
        when(bms.mapTo(performer1, PerformerDTO.class)).thenReturn(performerDTO1);
        when(bms.mapTo(performer2, PerformerDTO.class)).thenReturn(performerDTO2);

        Assert.assertEquals(performerDTO1, performerFacade.findById(1L));
        Assert.assertEquals(performerDTO2, performerFacade.findById(2L));
    }

    @Test
    public void testFindAll() {
        List<Performer> performers = Arrays.asList(performer1, performer2);
        List<PerformerDTO> performerDTOS = Arrays.asList(performerDTO1, performerDTO2);

        when(performerService.findAll()).thenReturn(performers);
        when(bms.mapTo(performers, PerformerDTO.class)).thenReturn(performerDTOS);

        List<PerformerDTO> result = performerFacade.findAll();

        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(performerDTO1, result.get(0));
        Assert.assertEquals(performerDTO2, result.get(1));
    }

    @Test
    public void testUpdate() {
        when(bms.mapTo(performerDTO1, Performer.class)).thenReturn(performer1);
        doNothing().when(performerService).update(any(Performer.class));
        performerFacade.update(performerDTO1);
        verify(performerService, times(1)).update(performer1);
    }

    @Test
    public void testRemove() {
        doNothing().when(performerService).remove(any(Performer.class));
        performerFacade.remove(1L);
        verify(performerService, times(1)).remove(performer1);
    }
}