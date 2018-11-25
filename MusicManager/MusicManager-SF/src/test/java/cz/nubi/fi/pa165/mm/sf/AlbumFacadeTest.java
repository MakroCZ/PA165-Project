package cz.nubi.fi.pa165.mm.sf;

import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * @author Lukas Suchanek; 433654
 */

@ContextConfiguration(classes= ServiceConfiguration.class)
public class AlbumFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

}
