package cz.nubi.fi.pa165.mm.sf;
import cz.muni.fi.pa165.mm.daolayer.dao.SongDao;
import cz.muni.fi.pa165.mm.sf.service.GenreService;
import cz.muni.fi.pa165.mm.sf.service.SongService;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Yehor Safonov; 487596
 */

@ContextConfiguration(classes= ServiceConfiguration.class)
public class SongServiceTest extends AbstractTransactionalTestNGSpringContextTests  {
    @Mock
    private SongDao songDao;

    @Mock
    private AlbumService albumService;

    @Mock
    private GenreService genreService;

    @Autowired
    @InjectMocks
    private SongService songService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void prepareTestSong(){}

    @Test
    public  void  testDelete(){}

    @Test
    public void testCreate(){}

    @Test
    public void testUpdate(){}

    @Test
    public void testCreateNull(){}

    @Test
    public void testFindAll(){}

    @Test
    public void findById(){}

    @Test
    public void testFindAllSongsFromSamePerformer(){}

    @Test
    public void testFindAllNullSongsFromSamePerformer(){}


}
