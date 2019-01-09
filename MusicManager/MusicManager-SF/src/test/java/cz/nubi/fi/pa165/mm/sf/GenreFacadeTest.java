package cz.nubi.fi.pa165.mm.sf;

import cz.muni.fi.pa165.mm.api.dto.GenreCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;
import cz.muni.fi.pa165.mm.api.facade.GenreFacade;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.sf.facade.GenreFacadeImpl;
import cz.muni.fi.pa165.mm.sf.service.BeanMappingService;
import cz.muni.fi.pa165.mm.sf.service.GenreService;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import java.util.Arrays;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Marek Barinka; 456295
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class GenreFacadeTest {

    @Mock
    private GenreService genreService;
    
    @Mock
    private BeanMappingService beanMappingService;
    
    //@Autowired z nějakého důvodu nefunguje, proto vytváření instance
    @InjectMocks
    private GenreFacade genreFacade = new GenreFacadeImpl();
    
    
    @BeforeMethod
    public void initMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        
        Mockito.doReturn(g1).when(beanMappingService).mapTo(gDTO1, Genre.class);
        Mockito.doReturn(g2).when(beanMappingService).mapTo(gDTO2, Genre.class);
        
        Mockito.doReturn(gDTO1).when(beanMappingService).mapTo(g1, GenreDTO.class);
        Mockito.doReturn(gDTO2).when(beanMappingService).mapTo(g2, GenreDTO.class);
        
        Mockito.doReturn(Arrays.asList(g1, g2)).when(beanMappingService)
                .mapTo(Arrays.asList(gDTO1, gDTO2), Genre.class);
        
        Mockito.doReturn(Arrays.asList(gDTO1, gDTO2)).when(beanMappingService)
                .mapTo(Arrays.asList(g1, g2), GenreDTO.class);
        
        Mockito.doReturn(Arrays.asList(gDTO2)).when(beanMappingService)
                .mapTo(Arrays.asList(g2), GenreDTO.class);
    }
    
    private Genre g1;
    private Genre g2;
    private GenreDTO gDTO1;
    private GenreDTO gDTO2;
    
    @BeforeMethod
    public void init() {
        g1 = new Genre();
        g1.setId(1L);
        g1.setName("Genre1");
        g1.setDescription("Desc1");
        
        g2 = new Genre();
        g2.setId(2L);
        g2.setName("Genre2");
        g2.setDescription("Desc2");
        
        gDTO1 = new GenreDTO();
        gDTO1.setId(1L);
        gDTO1.setName("Genre1");
        gDTO1.setDescription("Desc1");
        
        gDTO2 = new GenreDTO();
        gDTO2.setId(2L);
        gDTO2.setName("Genre2");
        gDTO2.setDescription("Desc2");
    }
    
    @Test
    public void create() {
        GenreCreateDTO gc = new GenreCreateDTO();
        gc.setName("NewGenre");
        gc.setDescription("NewDesc");
        
        Genre g = new Genre();
        g.setId(3L);
        g.setName("NewGenre");
        g.setDescription("NewDesc");
        Mockito.doReturn(g).when(genreService).create(any(Genre.class));
        Long newId = genreFacade.createGenre(gc);
        Assert.assertEquals(newId, new Long(3L));
    }

    @Test
    public void update() {
        Mockito.doNothing().when(genreService).update(any(Genre.class));
        genreFacade.updateGenre(gDTO1);
        Mockito.verify(genreService, Mockito.times(1)).update(g1);
    }
    
    @Test
    public void delete() {
        Mockito.doNothing().when(genreService).delete(any(Genre.class));
        Mockito.doReturn(g1).when(genreService).findById(1L);
        genreFacade.deleteGenre(1L);
        Mockito.verify(genreService, Mockito.times(1)).delete(any(Genre.class));
    }
    
    @Test
    public void findAll() {
        Mockito.doReturn(Arrays.asList(g1, g2)).when(genreService).findAll();
        List<GenreDTO> gl = genreFacade.findAll();
        Assert.assertEquals(gl.size(), 2);
        Assert.assertEquals(gl.get(0), gDTO1);
        Assert.assertEquals(gl.get(1), gDTO2);
        
    }
    
    @Test
    public void getWithId() {
        Mockito.doReturn(g1).when(genreService).findById(1L);
        GenreDTO gDTO = genreFacade.getWithId(1L);
        Assert.assertEquals(gDTO, gDTO1);
    }
    
    @Test
    public void getWithName() {
        Mockito.doReturn(Arrays.asList(g2)).when(genreService).findByName("Genre2");
        List<GenreDTO> gDTO = genreFacade.getWithName("Genre2");
        Assert.assertEquals(gDTO.size(), 1);
        Assert.assertEquals(gDTO.get(0), gDTO2);
    }
}
