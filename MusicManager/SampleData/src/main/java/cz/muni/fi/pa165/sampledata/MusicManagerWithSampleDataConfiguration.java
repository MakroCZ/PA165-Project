package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Sample data configuration based on seminar solution
 * @author Marek
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackages = {"cz.muni.fi.pa165.sampledata"})
public class MusicManagerWithSampleDataConfiguration {
    
    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        sampleDataLoadingFacade.loadData();
    }
}
