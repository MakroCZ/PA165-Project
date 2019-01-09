package cz.nubi.fi.pa165.mm.sf;


import cz.muni.fi.pa165.mm.daolayer.dao.GenreDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.sf.service.GenreService;
import cz.muni.fi.pa165.mm.sf.service.SongService;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Marek Barinka; 456295
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class GenreServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private GenreDao genreDao;
    
    @Mock
    private SongService songService;

    @Autowired
    @InjectMocks
    private GenreService genreService;
    
    
    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Genre testGenre1;
    private Genre testGenre2;
    
    @BeforeMethod
    public void prepareTestGenre(){
    	testGenre1 = new Genre();
        testGenre1.setId(1L);
        testGenre1.setName("Testovací žánr 1");
        testGenre1.setDescription("desc");
        
        testGenre2 = new Genre();
        testGenre2.setId(2L);
        testGenre2.setName("Testovací žánr 2");
        testGenre2.setDescription("desc");
    }
    
    
    @Test
    public void createValid() {
        testGenre1.setId(1L);
        doNothing().when(genreDao).create(any(Genre.class));
        Genre g = genreService.create(testGenre1);
        Assert.assertEquals(testGenre1, g);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void createNull() {
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(genreDao).create(null);
        genreService.create(null);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void createInvalid() {
        testGenre1.setName(null);
        Mockito.doThrow(DataIntegrityViolationException.class).when(genreDao).create(testGenre1);
        genreService.create(testGenre1);
    }
    
    @Test
    public void deleteValid() {
        Mockito.doNothing().when(genreDao).delete(any(Genre.class));
        genreService.delete(testGenre1);
        Mockito.verify(genreDao, Mockito.times(1)).delete(any(Genre.class));
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void deleteNull() {
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(genreDao).delete(null);
        genreService.delete(null);
    }
    
    @Test
    public void deleteMissing() {
        Genre missing = new Genre();
        missing.setId(10L);
        missing.setName("missing");
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(genreDao).delete(missing);
    }
    
    @Test
    public void getAll() {
        Mockito.doReturn(Arrays.asList(testGenre1, testGenre2)).when(genreDao).findAll();
        List<Genre> g = genreService.findAll();
        Assert.assertEquals(g.size(), 2);
        Assert.assertEquals(g.get(0), testGenre1);
        Assert.assertEquals(g.get(1), testGenre2);
    }
    
    @Test
    public void findByValidId() {
        testGenre1.setId(1L);
        Mockito.doReturn(testGenre1).when(genreDao).findById(1L);
        Genre g = genreService.findById(1L);
        Assert.assertEquals(testGenre1, g);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void findByNullId() {
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(genreDao).findById(null);
        genreService.findById(null);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void findByMissingId() {
        Mockito.doThrow(DataRetrievalFailureException.class).when(genreDao).findById(2L);
        genreService.findById(2L);
    }
    
    @Test
    public void updateValid() {
        Mockito.doNothing().when(genreDao).update(any(Genre.class));
        genreService.update(testGenre1);
        Mockito.verify(genreDao, Mockito.times(1)).update(any(Genre.class));
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void updateNull() {
        Mockito.doThrow(InvalidDataAccessApiUsageException.class).when(genreDao).update(null);
        genreService.update(null);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void updateInvalid() {
        Genre invalid = new Genre();
        Mockito.doThrow(DataIntegrityViolationException.class).when(genreDao).update(invalid);
        genreService.update(invalid);
    }
    
    @Test
    public void findByValidName() {
        Mockito.doReturn(Arrays.asList(testGenre1)).when(genreDao).findByName("Testovací žánr 1");
        List<Genre> g = genreService.findByName("Testovací žánr 1");
        Assert.assertEquals(g.size(), 1);
        Assert.assertEquals(g.get(0), testGenre1);
    }
    
    @Test
    public void findByMissingName() {
        Mockito.doReturn(new ArrayList<>()).when(genreDao).findByName("Missing name");
        List<Genre> g = genreService.findByName("Missing name");
        Assert.assertEquals(g.size(), 0);
    }
    
    @Test
    public void getAllSongsWithSameGenreValid() {
        Song s1 = new Song();
        s1.setId(1L);
        s1.setName("Song 1");
        s1.setDate(LocalDate.of(2000, Month.APRIL, 10));
        s1.setLength(LocalTime.of(0, 3, 14));
        s1.setGenre(testGenre1);
        Song s2 = new Song();
        s2.setId(2L);
        s2.setName("Song 2");
        s2.setDate(LocalDate.of(2000, Month.APRIL, 10));
        s2.setLength(LocalTime.of(0, 3, 14));
        s2.setGenre(testGenre2);
        Song s3 = new Song();
        s3.setId(3L);
        s3.setName("Song 3");
        s3.setDate(LocalDate.of(2000, Month.APRIL, 10));
        s3.setLength(LocalTime.of(0, 3, 14));
        s3.setGenre(testGenre1);
        
        Mockito.doReturn(Arrays.asList(s1, s2, s3)).when(songService).findAll();
        List<Song> s = genreService.getAllSongsWithSameGenre(testGenre1);
        Assert.assertEquals(s.size(), 2);
        Assert.assertEquals(s.get(0), s1);
        Assert.assertEquals(s.get(1), s3);
    }
    
    @Test
    public void getAllSongsWithSameGenreNull() {
        List<Song> s = genreService.getAllSongsWithSameGenre(null);
        Assert.assertEquals(s.size(), 0);
    }
    
    @Test
    public void getAllSongsWithSameGenreInvalid() {
        List<Song> s = genreService.getAllSongsWithSameGenre(null);
        Assert.assertEquals(s.size(), 0);
    }
}
